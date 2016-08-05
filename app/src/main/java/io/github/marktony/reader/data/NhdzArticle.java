package io.github.marktony.reader.data;

/**
 * Created by Lizhaotailang on 2016/8/5.
 */

public class NhdzArticle {

    private long display_time;

    private String content;

    private String user_name;

    public NhdzArticle(String user_name, String content, long display_time){
        this.user_name = user_name;
        this.content = content;
        this.display_time = display_time;
    }

    public long getDisplay_time() {
        return display_time;
    }

    public String getContent() {
        return content;
    }

    public String getUser_name() {
        return user_name;
    }
}
