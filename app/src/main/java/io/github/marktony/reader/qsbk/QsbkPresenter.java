package io.github.marktony.reader.qsbk;

import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;

import java.util.ArrayList;

import io.github.marktony.reader.data.OnStringListener;
import io.github.marktony.reader.data.Qiushibaike;
import io.github.marktony.reader.data.StringModelImpl;

import static android.content.Context.CLIPBOARD_SERVICE;

/**
 * Created by Lizhaotailang on 2016/8/4.
 */

public class QsbkPresenter implements QsbkContract.Presenter, OnStringListener {

    private QsbkContract.View view;
    private Context context;
    private ArrayList<Qiushibaike.Item> list =new ArrayList<>();
    private StringModelImpl model;

    public QsbkPresenter(Context context, QsbkContract.View view){
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
        model.load("http://m2.qiushibaike.com/article/list/text?page=" + (list.size() / 30 + 1), this);
    }

    @Override
    public void loadMore() {
        model.load("http://m2.qiushibaike.com/article/list/text?page=" + (list.size() / 30 + 1), this);
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
    public void onSuccess(String result) {
        Gson gson = new Gson();
        Qiushibaike q = gson.fromJson(result, Qiushibaike.class);
        for (Qiushibaike.Item item : q.getItems()) {
            list.add(item);
        }
        view.showResult(list);
        view.stopLoading();
    }

    @Override
    public void onError(VolleyError error) {
        view.showLoadError();
        view.stopLoading();
    }

}
