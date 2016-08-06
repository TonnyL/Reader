package io.github.marktony.reader.joke;

import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import io.github.marktony.reader.app.VolleySingleton;
import io.github.marktony.reader.data.JiandanArticle;

import static android.content.Context.CLIPBOARD_SERVICE;

/**
 * Created by Lizhaotailang on 2016/8/5.
 */

public class JiandanPresenter implements JiandanContract.Presenter {

    private JiandanContract.View view;
    private ArrayList<JiandanArticle> jiandanArticles = new ArrayList<>();
    private Context context;

    public JiandanPresenter(Context context, JiandanContract.View view){
        this.view = view;
        this.context = context;
    }

    public void loadArticle(Boolean forceRefresh) {
        view.loading();
        if (forceRefresh){
            jiandanArticles.clear();
        }
        getData((jiandanArticles.size() / 25) + 1);
    }

    @Override
    public void loadMore() {
        getData((jiandanArticles.size() / 25) + 1);
    }

    @Override
    public void loadDone() {
        view.loaded();
    }

    @Override
    public void shareTo(int position) {
        try {
            Intent i = new Intent().setAction(Intent.ACTION_SEND).setType("text/plain");
            String text = jiandanArticles.get(position).getComment_content();
            i.putExtra(Intent.EXTRA_TEXT, text);
            context.startActivity(Intent.createChooser(i,"分享至"));
        } catch (ActivityNotFoundException ex){
            Toast.makeText(context, "没有可以分享的App", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void copyToClipboard(int position) {
        ClipboardManager manager = (ClipboardManager) context.getSystemService(CLIPBOARD_SERVICE);
        ClipData clipData = ClipData.newPlainText("text", jiandanArticles.get(position).getComment_content());
        manager.setPrimaryClip(clipData);
        Toast.makeText(context, "内容已复制到剪贴板", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void start() {

    }

    @Override
    public void finish() {
        if (VolleySingleton.getVolleySingleton(context).getRequestQueue() != null){
            VolleySingleton.getVolleySingleton(context).getRequestQueue().cancelAll("JD");
        }
    }

    public void getData(int offset) {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
                "http://jandan.net/?oxwlxojflwblxbsapi=jandan.get_duan_comments&page=?oxwlxojflwblxbsapi=jandan.get_duan_comments&page=" + offset,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {

                        try {
                            if (jsonObject.getString("status").equals("ok")){
                                JSONArray array = jsonObject.getJSONArray("comments");
                                for (int i = 0; i < array.length(); i++){
                                    JSONObject object = array.getJSONObject(i);
                                    jiandanArticles.add(new JiandanArticle(
                                            object.getString("comment_author"),
                                            object.getString("comment_date"),
                                            object.getString("comment_content")
                                    ));
                                }
                            }

                            view.showArticle(jiandanArticles);

                            loadDone();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });

        request.setTag("JD");
        VolleySingleton.getVolleySingleton(context).addToRequestQueue(request);
    }

}
