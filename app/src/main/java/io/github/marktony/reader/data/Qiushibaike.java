package io.github.marktony.reader.data;

import java.util.ArrayList;

/**
 * Created by Lizhaotailang on 2016/9/26.
 */

public class Qiushibaike {

    private int count;
    private int total;
    private int page;
    private int err;
    private ArrayList<Item> items;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getErr() {
        return err;
    }

    public void setErr(int err) {
        this.err = err;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public class Item {

        private String format;
        private String image;
        private long published_at;
        private String tag;
        private String image_size;
        private long id;
        private long created_at;
        private String content;
        private String state;
        private int comments_count;
        private boolean allow_comment;
        private int share_count;
        private String type;
        private User user;
        private Votes votes;


        public String getFormat() {
            return format;
        }

        public void setFormat(String format) {
            this.format = format;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public long getPublished_at() {
            return published_at;
        }

        public void setPublished_at(long published_at) {
            this.published_at = published_at;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public String getImage_size() {
            return image_size;
        }

        public void setImage_size(String image_size) {
            this.image_size = image_size;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public long getCreated_at() {
            return created_at;
        }

        public void setCreated_at(long created_at) {
            this.created_at = created_at;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public int getComments_count() {
            return comments_count;
        }

        public void setComments_count(int comments_count) {
            this.comments_count = comments_count;
        }

        public boolean isAllow_comment() {
            return allow_comment;
        }

        public void setAllow_comment(boolean allow_comment) {
            this.allow_comment = allow_comment;
        }

        public int getShare_count() {
            return share_count;
        }

        public void setShare_count(int share_count) {
            this.share_count = share_count;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

        public Votes getVotes() {
            return votes;
        }

        public void setVotes(Votes votes) {
            this.votes = votes;
        }
    }

    public class User {

        private long avatar_updated_at;
        private int uid;
        private long last_visited_at;
        private long created_at;
        private String state;
        private String last_device;
        private String role;
        private String login;
        private int id;
        private String icon;

        public long getAvatar_updated_at() {
            return avatar_updated_at;
        }

        public void setAvatar_updated_at(long avatar_updated_at) {
            this.avatar_updated_at = avatar_updated_at;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public long getLast_visited_at() {
            return last_visited_at;
        }

        public void setLast_visited_at(long last_visited_at) {
            this.last_visited_at = last_visited_at;
        }

        public long getCreated_at() {
            return created_at;
        }

        public void setCreated_at(long created_at) {
            this.created_at = created_at;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getLast_device() {
            return last_device;
        }

        public void setLast_device(String last_device) {
            this.last_device = last_device;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public String getLogin() {
            return login;
        }

        public void setLogin(String login) {
            this.login = login;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

    }

    public class Votes {

        private int down;
        private int up;

        public int getDown() {
            return down;
        }

        public void setDown(int down) {
            this.down = down;
        }

        public int getUp() {
            return up;
        }

        public void setUp(int up) {
            this.up = up;
        }
    }

}
