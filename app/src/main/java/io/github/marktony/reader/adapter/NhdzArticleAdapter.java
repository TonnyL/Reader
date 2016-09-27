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
import io.github.marktony.reader.data.Neihanduanzi;
import io.github.marktony.reader.interfaze.OnRecyclerViewClickListener;
import io.github.marktony.reader.interfaze.OnRecyclerViewLongClickListener;
import io.github.marktony.reader.util.DateTimeHelper;

/**
 * Created by Lizhaotailang on 2016/8/5.
 */

public class NhdzArticleAdapter extends RecyclerView.Adapter<NhdzArticleAdapter.NhdzViewHolder> {

    private final Context context;
    private LayoutInflater inflater;
    private final List<Neihanduanzi.Data> list;

    private OnRecyclerViewClickListener listener;
    private OnRecyclerViewLongClickListener longClickListener;

    public NhdzArticleAdapter(Context context, ArrayList<Neihanduanzi.Data> list) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.list = list;
    }

    @Override
    public NhdzViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NhdzViewHolder(inflater.inflate(R.layout.joke_article_layout, parent, false), listener, longClickListener);
    }

    @Override
    public void onBindViewHolder(NhdzArticleAdapter.NhdzViewHolder holder, int position) {
        Neihanduanzi.Data article = list.get(position);
        if(article.getGroup().getUser() == null) {
            holder.tvAuthor.setText("匿名用户");
        } else {
            holder.tvAuthor.setText(article.getGroup().getUser().getName());
        }
        holder.tvTime.setText(DateTimeHelper.getInterval(new Date((long) article.getDisplay_time() * 1000)));
        holder.tvContent.setText(article.getGroup().getText());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setOnItemClickListener(OnRecyclerViewClickListener listener){
        this.listener = listener;
    }

    public void setOnItemLongClickListener(OnRecyclerViewLongClickListener longClickListener){
        this.longClickListener = longClickListener;
    }

    public class NhdzViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        TextView tvAuthor;
        TextView tvTime;
        TextView tvContent;
        OnRecyclerViewClickListener listener;
        OnRecyclerViewLongClickListener longClickListener;

        public NhdzViewHolder(
                View itemView,
                OnRecyclerViewClickListener listener,
                OnRecyclerViewLongClickListener longClickListener) {

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
