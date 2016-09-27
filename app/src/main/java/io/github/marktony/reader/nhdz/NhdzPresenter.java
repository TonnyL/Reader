package io.github.marktony.reader.nhdz;

import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;

import java.util.ArrayList;

import io.github.marktony.reader.data.Neihanduanzi;
import io.github.marktony.reader.data.OnStringListener;
import io.github.marktony.reader.data.StringModelImpl;

import static android.content.Context.CLIPBOARD_SERVICE;

/**
 * Created by Lizhaotailang on 2016/8/5.
 */

public class NhdzPresenter implements NhdzContract.Presenter, OnStringListener {

    private NhdzContract.View view;
    private Context context;
    private ArrayList<Neihanduanzi.Data> list = new ArrayList<>();
    private StringModelImpl model;

    private int offset = 1;

    public NhdzPresenter(Context context, NhdzContract.View view){
        this.view = view;
        this.context = context;
        this.view.setPresenter(this);
        model = new StringModelImpl(context);
    }

    @Override
    public void loadArticle(Boolean forceRefresh) {
        view.startLoading();
        if (forceRefresh){
            list.clear();
        }
        model.load("http://ic.snssdk.com/neihan/stream/mix/v1/?mpic=1&essence=" + offset
                        + "&content_type=-102&message_cursor=-1&bd_Stringitude=113.369569&bd_latitude=23.149678&bd_city=广州市&am_Stringitude=113.367846&am_latitude=23.149878&am_city=广州市&am_loc_time=1465213692154&count=30&min_time=1465213700&screen_width=720&iid=4512422578&device_id=17215021497&ac=wifi&channel=NHSQH5AN&aid=7&app_name=joke_essay&version_code=431&device_platform=android&ssmix=a&device_type=6s+Plus&os_api=19&os_version=4.4.2&uuid=864394108025091&openudid=80FA5B208E050000&manifest_version_code=431", this);
    }

    @Override
    public void loadMore() {
        model.load("http://ic.snssdk.com/neihan/stream/mix/v1/?mpic=1&essence=" + (offset++)
                + "&content_type=-102&message_cursor=-1&bd_Stringitude=113.369569&bd_latitude=23.149678&bd_city=广州市&am_Stringitude=113.367846&am_latitude=23.149878&am_city=广州市&am_loc_time=1465213692154&count=30&min_time=1465213700&screen_width=720&iid=4512422578&device_id=17215021497&ac=wifi&channel=NHSQH5AN&aid=7&app_name=joke_essay&version_code=431&device_platform=android&ssmix=a&device_type=6s+Plus&os_api=19&os_version=4.4.2&uuid=864394108025091&openudid=80FA5B208E050000&manifest_version_code=431", this);
    }

    @Override
    public void shareTo(int position) {
        try {
            Intent i = new Intent().setAction(Intent.ACTION_SEND).setType("text/plain");
            String text = list.get(position).getGroup().getContent();
            i.putExtra(Intent.EXTRA_TEXT, text);
            context.startActivity(Intent.createChooser(i,"分享至"));
        } catch (ActivityNotFoundException ex){
            Toast.makeText(context, "没有可以分享的App", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void copyToClipboard(int position) {
        ClipboardManager manager = (ClipboardManager) context.getSystemService(CLIPBOARD_SERVICE);
        ClipData clipData = ClipData.newPlainText("text", list.get(position).getGroup().getContent());
        manager.setPrimaryClip(clipData);
        Toast.makeText(context, "内容已复制到剪贴板", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void start() {

    }

    @Override
    public void onSuccess(String result) {
        Gson gson = new Gson();
        Neihanduanzi n = gson.fromJson(result, Neihanduanzi.class);
        for (Neihanduanzi.Data data : n.getData().getData()) {
            if (data.getAd() == null) {
                list.add(data);
            }
        }
        view.showResult(list);
        view.stopLoading();
    }

    @Override
    public void onError(VolleyError error) {
        view.showLoadError();
    }

}
