package io.github.marktony.reader.joke;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import io.github.marktony.reader.R;
import io.github.marktony.reader.adapter.JiandanArticleAdapter;
import io.github.marktony.reader.adapter.NhdzArticleAdapter;
import io.github.marktony.reader.data.NhdzArticle;

/**
 * Created by Lizhaotailang on 2016/8/5.
 */

public class NhdzFragment extends Fragment implements NhdzContract.View {

    private NhdzContract.Presenter presenter;
    private NhdzArticleAdapter adapter;
    private RecyclerView rvArticles;
    private SwipeRefreshLayout refreshLayout;

    public NhdzFragment(){

    }

    public static NhdzFragment newInstance (int page) {
        return new NhdzFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new NhdzPresenter(getActivity(), this);
        setPresenter(presenter);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.joke_list_fragment, container, false);
        rvArticles = (RecyclerView) view.findViewById(R.id.qsbk_rv);
        rvArticles.setLayoutManager(new LinearLayoutManager(getActivity()));
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
    public void showArticle(ArrayList<NhdzArticle> articleList) {
        if (adapter == null){
            adapter = new NhdzArticleAdapter(getActivity(), articleList);
            rvArticles.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void setPresenter(NhdzContract.Presenter presenter) {
        presenter.loadArticle(false);
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
