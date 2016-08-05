package io.github.marktony.reader.data;

/**
 * Created by Lizhaotailang on 2016/8/5.
 */

public class JiandanArticle {

    private String comment_author;

    private String comment_date;

    private String comment_content;

    public JiandanArticle(String comment_author, String comment_date, String comment_content) {
        this.comment_author = comment_author;
        this.comment_date = comment_date;
        this.comment_content = comment_content;
    }

    public String getComment_author() {
        return comment_author;
    }

    public String getComment_content() {
        return comment_content;
    }

    public String getComment_date() {
        return comment_date;
    }
}
