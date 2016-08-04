package io.github.marktony.reader.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Lizhaotailang on 2016/8/4.
 */

public class QsbkArticleAdapter extends RecyclerView.Adapter<QsbkArticleAdapter.QsbkArticleViewHolder> {


    @Override
    public QsbkArticleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(QsbkArticleViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class QsbkArticleViewHolder extends RecyclerView.ViewHolder {

        public QsbkArticleViewHolder(View itemView) {
            super(itemView);
        }
    }
}
