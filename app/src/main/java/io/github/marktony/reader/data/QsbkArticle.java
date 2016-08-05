package io.github.marktony.reader.data;


/**
 * Created by Lizhaotailang on 2016/8/4.
 */

public class QsbkArticle {

    private String user_login;

    private long create_at;

    private String content;

    public QsbkArticle(String user_login, long create_at, String content) {
        this.user_login = user_login;
        this.create_at = create_at;
        this.content = content;
    }

    public long getCreate_at() {
        return create_at;
    }

    public String getContent() {
        return content;
    }

    public String getUser_login() {
        return user_login;
    }

}
