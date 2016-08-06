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

import io.github.marktony.reader.data.QsbkArticle;
import io.github.marktony.reader.model.JokeDataRequest;

/**
 * Created by Lizhaotailang on 2016/8/4.
 */

public class QsbkPresenter implements QsbkContract.Presenter,JokeDataRequest {

    private QsbkContract.View view;
    private Context context;
    private ArrayList<QsbkArticle> list =new ArrayList<>();

    private RequestQueue queue;

    public QsbkPresenter(Context context, QsbkContract.View view){
        this.view = view;
        this.context = context.getApplicationContext();
        queue = Volley.newRequestQueue(context);
    }

    @Override
    public void loadArticle(Boolean forceRefresh) {
        if (forceRefresh){
            list.clear();
        }
        getData(0);

    }

    @Override
    public QsbkArticle getElement(int position) {
        return list.get(position);
    }

    @Override
    public void start() {

    }

    @Override
    public void finish() {
        if (queue != null){
            queue.cancelAll("QSBK");
        }
    }

    @Override
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
        queue.add(request);
    }

}
