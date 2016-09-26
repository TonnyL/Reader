package io.github.marktony.reader.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.github.marktony.reader.R;
import io.github.marktony.reader.data.Qiushibaike;
import io.github.marktony.reader.interfaze.OnRecyclerViewClickListener;
import io.github.marktony.reader.interfaze.OnRecyclerViewLongClickListener;
import io.github.marktony.reader.util.DateTimeHelper;

/**
 * Created by Lizhaotailang on 2016/8/4.
 */

public class QsbkArticleAdapter extends RecyclerView.Adapter<QsbkArticleAdapter.QsbkArticleViewHolder> {

    private final Context context;
    private LayoutInflater inflater;
    private final List<Qiushibaike.Item> list;
    private OnRecyclerViewClickListener listener;
    private OnRecyclerViewLongClickListener longClickListener;

    public QsbkArticleAdapter (Context context, ArrayList<Qiushibaike.Item> list){
        this.context = context;
        this.list = list;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public QsbkArticleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new QsbkArticleViewHolder(inflater.inflate(R.layout.joke_article_layout,parent,false), listener, longClickListener);
    }

    @Override
    public void onBindViewHolder(QsbkArticleViewHolder holder, int position) {
        Qiushibaike.Item qsbkArticle = list.get(position);
        if (qsbkArticle.getUser() == null) {
            holder.tvAuthor.setText("匿名用户");
        } else {
            holder.tvAuthor.setText(qsbkArticle.getUser().getLogin());
        }
        holder.tvTime.setText(DateTimeHelper.getInterval(new Date(qsbkArticle.getCreated_at() * 1000)));
        holder.tvContent.setText(qsbkArticle.getContent());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setOnItemClickListener(OnRecyclerViewClickListener listener) {
        this.listener = listener;
    }

    public void setOnItemLongClickListener(OnRecyclerViewLongClickListener longClickListener){
        this.longClickListener = longClickListener;
    }

    public class QsbkArticleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        TextView tvAuthor;
        TextView tvTime;
        TextView tvContent;
        OnRecyclerViewClickListener listener;
        OnRecyclerViewLongClickListener longClickListener;

        public QsbkArticleViewHolder(View itemView, OnRecyclerViewClickListener listener, OnRecyclerViewLongClickListener longClickListener) {
            super(itemView);

            tvAuthor = (TextView) itemView.findViewById(R.id.author);
            tvTime = (TextView) itemView.findViewById(R.id.time);
            tvContent = (TextView) itemView.findViewById(R.id.content);
            this.listener = listener;
            itemView.setOnClickListener(this);
            this.longClickListener = longClickListener;
            itemView.setOnLongClickListener(this);

        }

        @Override
        public void onClick(View view) {
            if (listener != null){
                listener.OnClick(view, getLayoutPosition());
            }
        }

        @Override
        public boolean onLongClick(View view) {
            if (longClickListener != null){
                longClickListener.OnLongClick(view, getLayoutPosition());
            }
            return true;
        }
    }
}
