package io.github.marktony.reader.joke;

import android.content.Context;
import android.util.Log;

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

import io.github.marktony.reader.data.NhdzArticle;
import io.github.marktony.reader.model.JokeDataRequest;

/**
 * Created by Lizhaotailang on 2016/8/5.
 */

public class NhdzPresenter implements NhdzContract.Presenter,JokeDataRequest {

    private NhdzContract.View view;
    private Context context;
    private ArrayList<NhdzArticle> list = new ArrayList<>();

    private RequestQueue queue;

    private int offset = 1;

    public NhdzPresenter(Context context, NhdzContract.View view){
        this.view = view;
        this.context = context.getApplicationContext();
        queue = Volley.newRequestQueue(context);
    }

    @Override
    public void loadArticle(Boolean forceRefresh) {
        if (forceRefresh){
            list.clear();
        }
        getData(1);
    }

    @Override
    public void loadMore() {
        getData(offset++);
    }

    @Override
    public void start() {

    }

    @Override
    public void finish() {
        if (queue != null){
            queue.cancelAll("NHDZ");
        }
    }

    @Override
    public void getData(int offset) {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
                "http://ic.snssdk.com/neihan/stream/mix/v1/?mpic=1&essence=" + offset
                        + "&content_type=-102&message_cursor=-1&bd_Stringitude=113.369569&bd_latitude=23.149678&bd_city=广州市&am_Stringitude=113.367846&am_latitude=23.149878&am_city=广州市&am_loc_time=1465213692154&count=30&min_time=1465213700&screen_width=720&iid=4512422578&device_id=17215021497&ac=wifi&channel=NHSQH5AN&aid=7&app_name=joke_essay&version_code=431&device_platform=android&ssmix=a&device_type=6s+Plus&os_api=19&os_version=4.4.2&uuid=864394108025091&openudid=80FA5B208E050000&manifest_version_code=431",
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {

                        try {
                            if (jsonObject.getString("message").equals("success")){
                                JSONArray array = jsonObject.getJSONObject("data").getJSONArray("data");
                                for (int i = 0; i < array.length(); i++){
                                    JSONObject object = array.getJSONObject(i);
                                    if (!object.isNull("group")){
                                        list.add(new NhdzArticle(
                                                object.getJSONObject("group").getJSONObject("user").getString("name"),
                                                object.getJSONObject("group").getString("content"),
                                                object.getLong("display_time")
                                        ));
                                    }
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

        request.setTag("NHDZ");
        queue.add(request);
    }
}
