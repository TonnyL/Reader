package io.github.marktony.reader.joke;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import io.github.marktony.reader.data.JiandanArticle;
import io.github.marktony.reader.model.JokeDataRequest;

/**
 * Created by Lizhaotailang on 2016/8/5.
 */

public class JiandanPresenter implements JiandanContract.Presenter, JokeDataRequest {

    private JiandanContract.View view;
    private Context context;
    private RequestQueue queue;
    private ArrayList<JiandanArticle> jiandanArticles = new ArrayList<>();

    public JiandanPresenter(Context context, JiandanContract.View view){
        this.view = view;
        this.context = context.getApplicationContext();
        queue = Volley.newRequestQueue(context);
    }

    public void loadArticle(Boolean forceRefresh) {
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
    public void start() {

    }

    @Override
    public void finish() {
        if (queue != null){
            queue.cancelAll("JD");
        }
    }

    @Override
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
        queue.add(request);
    }
}
