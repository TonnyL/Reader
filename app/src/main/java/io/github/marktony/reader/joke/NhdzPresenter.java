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
import io.github.marktony.reader.data.NhdzArticle;

import static android.content.Context.CLIPBOARD_SERVICE;

/**
 * Created by Lizhaotailang on 2016/8/5.
 */

public class NhdzPresenter implements NhdzContract.Presenter {

    private NhdzContract.View view;
    private Context context;
    private ArrayList<NhdzArticle> list = new ArrayList<>();

    private int offset = 1;

    public NhdzPresenter(Context context, NhdzContract.View view){
        this.view = view;
        this.context = context;
    }

    @Override
    public void loadArticle(Boolean forceRefresh) {
        view.loading();
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
            VolleySingleton.getVolleySingleton(context).getRequestQueue().cancelAll("NHDZ");
        }
    }

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

        request.setTag("NHDZ");
        VolleySingleton.getVolleySingleton(context).addToRequestQueue(request);
    }

}
