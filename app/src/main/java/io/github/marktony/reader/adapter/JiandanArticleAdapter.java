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
import io.github.marktony.reader.data.JiandanArticle;

/**
 * Created by Lizhaotailang on 2016/8/5.
 */

public class JiandanArticleAdapter extends RecyclerView.Adapter<JiandanArticleAdapter.JiandanViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    private List<JiandanArticle> list = new ArrayList<>();

    public JiandanArticleAdapter(Context context, ArrayList<JiandanArticle> list) {
        this.context = context;
        this.list = list;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public JiandanViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new JiandanViewHolder(inflater.inflate(R.layout.joke_article_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(JiandanViewHolder holder, int position) {
        JiandanArticle article = list.get(position);
        holder.tvAuthor.setText(article.getComment_author());
        holder.tvTime.setText(article.getComment_date());
        holder.tvContent.setText(article.getComment_content());
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class JiandanViewHolder extends RecyclerView.ViewHolder {

        TextView tvAuthor;
        TextView tvTime;
        TextView tvContent;

        public JiandanViewHolder(View itemView) {
            super(itemView);

            tvAuthor = (TextView) itemView.findViewById(R.id.author);
            tvTime = (TextView) itemView.findViewById(R.id.time);
            tvContent = (TextView) itemView.findViewById(R.id.content);
        }
    }
}
