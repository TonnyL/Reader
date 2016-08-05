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
import io.github.marktony.reader.data.NhdzArticle;
import io.github.marktony.reader.util.DateTimeHelper;

/**
 * Created by Lizhaotailang on 2016/8/5.
 */

public class NhdzArticleAdapter extends RecyclerView.Adapter<NhdzArticleAdapter.NhdzViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    private List<NhdzArticle> list;

    public NhdzArticleAdapter(Context context, ArrayList<NhdzArticle> list) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.list = list;
    }

    @Override
    public NhdzViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NhdzViewHolder(inflater.inflate(R.layout.joke_article_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(NhdzArticleAdapter.NhdzViewHolder holder, int position) {
        NhdzArticle article = list.get(position);
        holder.tvAuthor.setText(article.getUser_name());
        holder.tvTime.setText(DateTimeHelper.getInterval(new Date(article.getDisplay_time() * 1000)));
        holder.tvContent.setText(article.getContent());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class NhdzViewHolder extends RecyclerView.ViewHolder {

        TextView tvAuthor;
        TextView tvTime;
        TextView tvContent;

        public NhdzViewHolder(View itemView) {
            super(itemView);
            tvAuthor = (TextView) itemView.findViewById(R.id.author);
            tvTime = (TextView) itemView.findViewById(R.id.time);
            tvContent = (TextView) itemView.findViewById(R.id.content);
        }
    }

}
