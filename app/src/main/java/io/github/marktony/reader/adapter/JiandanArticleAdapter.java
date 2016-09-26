package io.github.marktony.reader.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import io.github.marktony.reader.R;
import io.github.marktony.reader.data.Jiandan;
import io.github.marktony.reader.interfaze.OnRecyclerViewClickListener;
import io.github.marktony.reader.interfaze.OnRecyclerViewLongClickListener;

/**
 * Created by Lizhaotailang on 2016/8/5.
 */

public class JiandanArticleAdapter extends RecyclerView.Adapter<JiandanArticleAdapter.JiandanViewHolder> {

    private final Context context;
    private LayoutInflater inflater;
    private final List<Jiandan.Comment> list;

    private OnRecyclerViewClickListener listener;
    private OnRecyclerViewLongClickListener longClickListener;

    public JiandanArticleAdapter(Context context, ArrayList<Jiandan.Comment> list) {
        this.context = context;
        this.list = list;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public JiandanViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new JiandanViewHolder(inflater.inflate(R.layout.joke_article_layout,parent,false), listener, longClickListener);
    }

    @Override
    public void onBindViewHolder(JiandanViewHolder holder, int position) {
        Jiandan.Comment article = list.get(position);
        holder.tvAuthor.setText(article.getComment_author());
        holder.tvTime.setText(article.getComment_date());
        holder.tvContent.setText(article.getComment_content());
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setItemClickListener(OnRecyclerViewClickListener listener) {
        this.listener = listener;
    }

    public void setItemLongClickListener(OnRecyclerViewLongClickListener longClickListener) {
        this.longClickListener = longClickListener;
    }

    public class JiandanViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener {

        TextView tvAuthor;
        TextView tvTime;
        TextView tvContent;

        OnRecyclerViewClickListener listener;
        OnRecyclerViewLongClickListener longClickListener;

        public JiandanViewHolder(View itemView, OnRecyclerViewClickListener listener, OnRecyclerViewLongClickListener longClickListener) {
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
