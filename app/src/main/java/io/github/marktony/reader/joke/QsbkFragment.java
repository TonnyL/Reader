package io.github.marktony.reader.joke;

import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import io.github.marktony.reader.R;
import io.github.marktony.reader.adapter.QsbkArticleAdapter;
import io.github.marktony.reader.data.QsbkArticle;
import io.github.marktony.reader.interfaze.OnRecyclerViewClickListener;
import io.github.marktony.reader.interfaze.OnRecyclerViewLongClickListener;

import static android.content.Context.CLIPBOARD_SERVICE;

/**
 * Created by Lizhaotailang on 2016/8/4.
 */

public class QsbkFragment extends Fragment implements QsbkContract.View {

    private QsbkContract.Presenter presenter;
    private QsbkArticleAdapter adapter;
    private RecyclerView rvQsbkArticles;
    private SwipeRefreshLayout refreshLayout;

    public QsbkFragment() {
        // requires empty constructor
    }

    public static QsbkFragment newInstance(int page) {
        return new QsbkFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new QsbkPresenter(getActivity(), this);
        setPresenter(presenter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.joke_list_fragment, container, false);

        rvQsbkArticles = (RecyclerView) view.findViewById(R.id.qsbk_rv);
        rvQsbkArticles.setLayoutManager(new LinearLayoutManager(getActivity()));
        refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.loadArticle(true);
                adapter.notifyDataSetChanged();
                if (refreshLayout.isRefreshing()){
                    refreshLayout.setRefreshing(false);
                }
            }
        });

        return view;
    }

    @Override
    public void setPresenter(QsbkContract.Presenter presenter) {
        presenter.loadArticle(false);
    }

    @Override
    public void showArticle(ArrayList<QsbkArticle> articleList) {
        adapter = new QsbkArticleAdapter(getActivity(), articleList);
        rvQsbkArticles.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnRecyclerViewClickListener() {
            @Override
            public void OnClick(View v, int position) {
                try {
                    Intent i = new Intent().setAction(Intent.ACTION_SEND).setType("text/plain");
                    String text = presenter.getElement(position).getContent();
                    i.putExtra(Intent.EXTRA_TEXT, text);
                    startActivity(Intent.createChooser(i,"分享至"));
                } catch (ActivityNotFoundException ex){
                    Toast.makeText(getActivity(), "没有可以分享的App", Toast.LENGTH_SHORT).show();
                }
            }
        });

        adapter.setOnItemLongClickListener(new OnRecyclerViewLongClickListener() {
            @Override
            public void OnLongClick(View view, int position) {
                ClipboardManager manager = (ClipboardManager) getActivity().getSystemService(CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("text", presenter.getElement(position).getContent());
                manager.setPrimaryClip(clipData);
                Toast.makeText(getActivity(), "内容已复制到剪贴板", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.start();
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.finish();
    }

}
