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
import io.github.marktony.reader.data.QsbkArticle;
import io.github.marktony.reader.util.DateTimeHelper;

/**
 * Created by Lizhaotailang on 2016/8/4.
 */

public class QsbkArticleAdapter extends RecyclerView.Adapter<QsbkArticleAdapter.QsbkArticleViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    private List<QsbkArticle> list = new ArrayList<QsbkArticle>();

    public QsbkArticleAdapter (Context context, ArrayList<QsbkArticle> list){
        this.context = context;
        this.list = list;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public QsbkArticleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new QsbkArticleViewHolder(inflater.inflate(R.layout.joke_article_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(QsbkArticleViewHolder holder, int position) {
        QsbkArticle qsbkArticle = list.get(position);
        holder.tvAuthor.setText(qsbkArticle.getUser_login());
        holder.tvTime.setText(DateTimeHelper.getInterval(new Date(qsbkArticle.getCreate_at() * 1000)));
        holder.tvContent.setText(qsbkArticle.getContent());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class QsbkArticleViewHolder extends RecyclerView.ViewHolder {

        TextView tvAuthor;
        TextView tvTime;
        TextView tvContent;

        public QsbkArticleViewHolder(View itemView) {
            super(itemView);

            tvAuthor = (TextView) itemView.findViewById(R.id.author);
            tvTime = (TextView) itemView.findViewById(R.id.time);
            tvContent = (TextView) itemView.findViewById(R.id.content);

        }
    }
}
