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
import io.github.marktony.reader.adapter.JiandanArticleAdapter;
import io.github.marktony.reader.data.JiandanArticle;
import io.github.marktony.reader.interfaze.OnRecyclerViewClickListener;
import io.github.marktony.reader.interfaze.OnRecyclerViewLongClickListener;

import static android.content.Context.CLIPBOARD_SERVICE;

/**
 * Created by Lizhaotailang on 2016/8/5.
 */

public class JiandanFragment extends Fragment implements JiandanContract.View{

    private JiandanContract.Presenter presenter;
    private JiandanArticleAdapter adapter;
    private RecyclerView rvArticles;
    private SwipeRefreshLayout refreshLayout;

    public JiandanFragment() {

    }

    public static JiandanFragment newInstance(int page) {
        return new JiandanFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new JiandanPresenter(getActivity(),this);
        setPresenter(presenter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.joke_list_fragment,container,false);

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

        rvArticles.setOnScrollListener(new RecyclerView.OnScrollListener() {

            boolean isSlidingToLast = false;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {

                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                // 当不滚动时
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    // 获取最后一个完全显示的itemposition
                    int lastVisibleItem = manager.findLastCompletelyVisibleItemPosition();
                    int totalItemCount = manager.getItemCount();

                    // 判断是否滚动到底部并且是向下滑动
                    if (lastVisibleItem == (totalItemCount - 1) && isSlidingToLast) {
                        presenter.loadMore();
                    }
                }

                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                isSlidingToLast = dy > 0;
            }
        });

        return view;
    }

    @Override
    public void showArticle(ArrayList<JiandanArticle> articleList) {

        if (adapter == null){
            adapter = new JiandanArticleAdapter(getActivity(), articleList);
            rvArticles.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }

        adapter.setItemClickListener(new OnRecyclerViewClickListener() {
            @Override
            public void OnClick(View v, int position) {
                try {
                    Intent i = new Intent().setAction(Intent.ACTION_SEND).setType("text/plain");
                    String text = presenter.getElement(position).getComment_content();
                    i.putExtra(Intent.EXTRA_TEXT, text);
                    startActivity(Intent.createChooser(i,"分享至"));
                } catch (ActivityNotFoundException ex){
                    Toast.makeText(getActivity(), "没有可以分享的App", Toast.LENGTH_SHORT).show();
                }

            }

        });

        adapter.setItemLongClickListener(new OnRecyclerViewLongClickListener() {
            @Override
            public void OnLongClick(View view, int position) {
                ClipboardManager manager = (ClipboardManager) getActivity().getSystemService(CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("text", presenter.getElement(position).getComment_content());
                manager.setPrimaryClip(clipData);
                Toast.makeText(getActivity(), "内容已复制到剪贴板", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void setPresenter(JiandanContract.Presenter presenter) {
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
