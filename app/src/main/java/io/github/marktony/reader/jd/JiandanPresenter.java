package io.github.marktony.reader.jd;

import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;

import java.util.ArrayList;

import io.github.marktony.reader.data.Jiandan;
import io.github.marktony.reader.data.OnStringListener;
import io.github.marktony.reader.data.StringModelImpl;

import static android.content.Context.CLIPBOARD_SERVICE;

/**
 * Created by Lizhaotailang on 2016/8/5.
 */

public class JiandanPresenter implements JiandanContract.Presenter, OnStringListener {

    private JiandanContract.View view;
    private ArrayList<Jiandan.Comment> jiandanArticles = new ArrayList<>();
    private Context context;
    private StringModelImpl model;

    public JiandanPresenter(Context context, JiandanContract.View view){
        this.view = view;
        this.context = context;
        this.view.setPresenter(this);
        model = new StringModelImpl(context);
    }

    public void requestArticles(Boolean forceRefresh) {
        view.startLoading();
        if (forceRefresh){
            jiandanArticles.clear();
        }
        model.load("http://jandan.net/?oxwlxojflwblxbsapi=jandan.get_duan_comments&page=" + ((jiandanArticles.size() / 25) + 1), this);

    }

    @Override
    public void loadMore() {
        model.load("http://jandan.net/?oxwlxojflwblxbsapi=jandan.get_duan_comments&page=" + ((jiandanArticles.size() / 25) + 1), this);
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
    public void onSuccess(String result) {
        Gson gson = new Gson();
        Jiandan jiandan = gson.fromJson(result, Jiandan.class);
        for (Jiandan.Comment item : jiandan.getComments()) {
            jiandanArticles.add(item);
        }
        view.showResult(jiandanArticles);
        view.stopLoading();
    }

    @Override
    public void onError(VolleyError error) {
        view.showLoadError();
        view.stopLoading();
    }

}
