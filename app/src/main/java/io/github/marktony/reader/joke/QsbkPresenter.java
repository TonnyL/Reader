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
import io.github.marktony.reader.data.OnStringListener;
import io.github.marktony.reader.data.QsbkArticle;

import static android.content.Context.CLIPBOARD_SERVICE;

/**
 * Created by Lizhaotailang on 2016/8/4.
 */

public class QsbkPresenter implements QsbkContract.Presenter, OnStringListener {

    private QsbkContract.View view;
    private Context context;
    private ArrayList<QsbkArticle> list =new ArrayList<>();

    public QsbkPresenter(Context context, QsbkContract.View view){
        this.view = view;
        this.context = context;
    }

    @Override
    public void loadArticle(Boolean forceRefresh) {
        view.loading();
        if (forceRefresh){
            list.clear();
        }
        // getData(0);
    }

    @Override
    public void loadMore() {

    }

    @Override
    public void loadDone() {
        view.loaded();
    }

    @Override
    public void shareTo(int position) {
        try {
            Intent i = new Intent().setAction(Intent.ACTION_SEND).setType("text/plain");
            String text = list.get(position).getContent();
            i.putExtra(Intent.EXTRA_TEXT, text);
            context.startActivity(Intent.createChooser(i,"分享至"));
        } catch (ActivityNotFoundException ex){
            Toast.makeText(context, "没有可以分享的App", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void copyToClipboard(int position) {
        ClipboardManager manager = (ClipboardManager) context.getSystemService(CLIPBOARD_SERVICE);
        ClipData clipData = ClipData.newPlainText("text", list.get(position).getContent());
        manager.setPrimaryClip(clipData);
        Toast.makeText(context, "内容已复制到剪贴板", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void start() {

    }

    @Override
    public void finish() {
        if (VolleySingleton.getVolleySingleton(context).getRequestQueue() != null){
            VolleySingleton.getVolleySingleton(context).getRequestQueue().cancelAll("QSBK");
        }
    }

    /*@Override
    public void getData(int offset) {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, "http://m2.qiushibaike.com/article/list/text", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {

                try {
                    if (jsonObject.getInt("err") == 0){
                        JSONArray array = jsonObject.getJSONArray("items");
                        for (int i = 0; i < array.length(); i++){
                            JSONObject object = array.getJSONObject(i);

                            String login;
                            if (object.isNull("user")){
                                login = "匿名用户";
                            } else {
                                login = object.getJSONObject("user").getString("login");
                            }

                            QsbkArticle article = new QsbkArticle(
                                    login,
                                    object.getLong("created_at"),
                                    object.getString("content"));
                            list.add(article);
                        }
                    }

                    view.showArticle(list);
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

        request.setTag("QSBK");
        VolleySingleton.getVolleySingleton(context).addToRequestQueue(request);
    }*/

    @Override
    public void onSuccess(String result) {

    }

    @Override
    public void onError(VolleyError error) {

    }
}
