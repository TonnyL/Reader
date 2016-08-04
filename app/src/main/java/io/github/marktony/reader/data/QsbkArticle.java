package io.github.marktony.reader.data;

import java.util.List;

/**
 * Created by Lizhaotailang on 2016/8/4.
 */

public class QsbkArticle {

    public String format;

    public String image;

    public int published_at;

    public String tag;

    public static class user {

        public int avatar_updated_at;

        public int uid;

        public int last_visited_at;

        public int created_at;

        public String state;

        public String last_device;

        public String role;

        public String login;

        public int id;

        public String icon;

    }

    public static class image_size{

        public List<Integer> s;

        public List<Integer> m;

    }

    public int id;

    public static class votes {

        public int down;

        public int up;

    }

    public int created_at;

    public String content;

    public String state;

    public int comments_count;

    public boolean allow_comment;

    public int share_count;

    public String type;

}
