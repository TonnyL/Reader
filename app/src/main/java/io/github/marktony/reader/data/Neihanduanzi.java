package io.github.marktony.reader.data;

import java.util.ArrayList;

/**
 * Created by Lizhaotailang on 2016/9/27.
 */

public class Neihanduanzi {

    private String message;
    private PreData data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public PreData getData() {
        return data;
    }

    public void setData(PreData data) {
        this.data = data;
    }

    public class PreData {

        private boolean has_more;
        private String tip;
        private boolean has_new_message;
        private double max_time;
        private double min_time;
        private ArrayList<Data> data;

        public boolean isHas_more() {
            return has_more;
        }

        public void setHas_more(boolean has_more) {
            this.has_more = has_more;
        }

        public String getTip() {
            return tip;
        }

        public void setTip(String tip) {
            this.tip = tip;
        }

        public boolean isHas_new_message() {
            return has_new_message;
        }

        public void setHas_new_message(boolean has_new_message) {
            this.has_new_message = has_new_message;
        }

        public double getMax_time() {
            return max_time;
        }

        public void setMax_time(double max_time) {
            this.max_time = max_time;
        }

        public double getMin_time() {
            return min_time;
        }

        public void setMin_time(long min_time) {
            this.min_time = min_time;
        }

        public ArrayList<Data> getData() {
            return data;
        }

        public void setData(ArrayList<Data> data) {
            this.data = data;
        }
    }

    public class Data {

        private Group group;
        private Ad ad;
        private int type;
        private double display_time;
        private double online_time;
        private ArrayList<Comment> comments;

        public Group getGroup() {
            return group;
        }

        public void setGroup(Group group) {
            this.group = group;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public double getDisplay_time() {
            return display_time;
        }

        public void setDisplay_time(double display_time) {
            this.display_time = display_time;
        }

        public double getOnline_time() {
            return online_time;
        }

        public void setOnline_time(double online_time) {
            this.online_time = online_time;
        }

        public ArrayList<Comment> getComments() {
            return comments;
        }

        public void setComments(ArrayList<Comment> comments) {
            this.comments = comments;
        }

        public Ad getAd() {
            return ad;
        }

        public void setAd(Ad ad) {
            this.ad = ad;
        }
    }

    public class Group {

        private String text;
        private String neihan_hot_start_time;
        private ArrayList<DislikeReason> dislike_reason;
        private long create_time;
        private long id;
        private int favorite_count;
        private int go_detail_count;
        private int user_favorite;
        private int share_type;
        private User user;
        private int is_can_share;
        private int category_type;
        private String share_url;
        private int label;
        private String content;
        private int comment_count;
        private long id_str;
        private int media_type;
        private int share_count;
        private int type;
        private int status;
        private int has_comments;
        private int user_bury;
        private Activity activity;
        private String status_desc;
        private boolean quick_comment;
        private int display_type;
        private String neihan_hot_end_time;
        private int user_digg;
        private long online_time;
        private String category_name;
        private boolean category_visible;
        private int bury_count;
        private boolean is_anonymous;
        private int repin_count;
        private boolean is_neihan_hot;
        private int digg_count;
        private int has_hot_comments;
        private boolean allow_dislike;
        private int user_repin;
        private NeihanHotLink neihan_hot_link;
        private long group_id;
        private int category_id;

        public String getShare_url() {
            return share_url;
        }

        public void setShare_url(String share_url) {
            this.share_url = share_url;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getNeihan_hot_start_time() {
            return neihan_hot_start_time;
        }

        public void setNeihan_hot_start_time(String neihan_hot_start_time) {
            this.neihan_hot_start_time = neihan_hot_start_time;
        }

        public ArrayList<DislikeReason> getDislike_reason() {
            return dislike_reason;
        }

        public void setDislike_reason(ArrayList<DislikeReason> dislike_reason) {
            this.dislike_reason = dislike_reason;
        }

        public long getCreate_time() {
            return create_time;
        }

        public void setCreate_time(long create_time) {
            this.create_time = create_time;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public int getFavorite_count() {
            return favorite_count;
        }

        public void setFavorite_count(int favorite_count) {
            this.favorite_count = favorite_count;
        }

        public int getGo_detail_count() {
            return go_detail_count;
        }

        public void setGo_detail_count(int go_detail_count) {
            this.go_detail_count = go_detail_count;
        }

        public int getUser_favorite() {
            return user_favorite;
        }

        public void setUser_favorite(int user_favorite) {
            this.user_favorite = user_favorite;
        }

        public int getShare_type() {
            return share_type;
        }

        public void setShare_type(int share_type) {
            this.share_type = share_type;
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

        public int getIs_can_share() {
            return is_can_share;
        }

        public void setIs_can_share(int is_can_share) {
            this.is_can_share = is_can_share;
        }

        public int getCategory_type() {
            return category_type;
        }

        public void setCategory_type(int category_type) {
            this.category_type = category_type;
        }

        public int getLabel() {
            return label;
        }

        public void setLabel(int label) {
            this.label = label;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getComment_count() {
            return comment_count;
        }

        public void setComment_count(int comment_count) {
            this.comment_count = comment_count;
        }

        public long getId_str() {
            return id_str;
        }

        public void setId_str(long id_str) {
            this.id_str = id_str;
        }

        public int getMedia_type() {
            return media_type;
        }

        public void setMedia_type(int media_type) {
            this.media_type = media_type;
        }

        public int getShare_count() {
            return share_count;
        }

        public void setShare_count(int share_count) {
            this.share_count = share_count;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getHas_comments() {
            return has_comments;
        }

        public void setHas_comments(int has_comments) {
            this.has_comments = has_comments;
        }

        public int getUser_bury() {
            return user_bury;
        }

        public void setUser_bury(int user_bury) {
            this.user_bury = user_bury;
        }

        public Activity getActivity() {
            return activity;
        }

        public void setActivity(Activity activity) {
            this.activity = activity;
        }

        public String getStatus_desc() {
            return status_desc;
        }

        public void setStatus_desc(String status_desc) {
            this.status_desc = status_desc;
        }

        public boolean isQuick_comment() {
            return quick_comment;
        }

        public void setQuick_comment(boolean quick_comment) {
            this.quick_comment = quick_comment;
        }

        public int getDisplay_type() {
            return display_type;
        }

        public void setDisplay_type(int display_type) {
            this.display_type = display_type;
        }

        public String getNeihan_hot_end_time() {
            return neihan_hot_end_time;
        }

        public void setNeihan_hot_end_time(String neihan_hot_end_time) {
            this.neihan_hot_end_time = neihan_hot_end_time;
        }

        public int getUser_digg() {
            return user_digg;
        }

        public void setUser_digg(int user_digg) {
            this.user_digg = user_digg;
        }

        public long getOnline_time() {
            return online_time;
        }

        public void setOnline_time(long online_time) {
            this.online_time = online_time;
        }

        public String getCategory_name() {
            return category_name;
        }

        public void setCategory_name(String category_name) {
            this.category_name = category_name;
        }

        public boolean isCategory_visible() {
            return category_visible;
        }

        public void setCategory_visible(boolean category_visible) {
            this.category_visible = category_visible;
        }

        public int getBury_count() {
            return bury_count;
        }

        public void setBury_count(int bury_count) {
            this.bury_count = bury_count;
        }

        public boolean is_anonymous() {
            return is_anonymous;
        }

        public void setIs_anonymous(boolean is_anonymous) {
            this.is_anonymous = is_anonymous;
        }

        public int getRepin_count() {
            return repin_count;
        }

        public void setRepin_count(int repin_count) {
            this.repin_count = repin_count;
        }

        public boolean is_neihan_hot() {
            return is_neihan_hot;
        }

        public void setIs_neihan_hot(boolean is_neihan_hot) {
            this.is_neihan_hot = is_neihan_hot;
        }

        public int getDigg_count() {
            return digg_count;
        }

        public void setDigg_count(int digg_count) {
            this.digg_count = digg_count;
        }

        public int getHas_hot_comments() {
            return has_hot_comments;
        }

        public void setHas_hot_comments(int has_hot_comments) {
            this.has_hot_comments = has_hot_comments;
        }

        public boolean isAllow_dislike() {
            return allow_dislike;
        }

        public void setAllow_dislike(boolean allow_dislike) {
            this.allow_dislike = allow_dislike;
        }

        public int getUser_repin() {
            return user_repin;
        }

        public void setUser_repin(int user_repin) {
            this.user_repin = user_repin;
        }

        public NeihanHotLink getNeihan_hot_link() {
            return neihan_hot_link;
        }

        public void setNeihan_hot_link(NeihanHotLink neihan_hot_link) {
            this.neihan_hot_link = neihan_hot_link;
        }

        public long getGroup_id() {
            return group_id;
        }

        public void setGroup_id(long group_id) {
            this.group_id = group_id;
        }

        public int getCategory_id() {
            return category_id;
        }

        public void setCategory_id(int category_id) {
            this.category_id = category_id;
        }

    }

    public class DislikeReason {

        private int type;
        private long id;
        private String title;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

    public class User {

        private long user_id;
        private String name;
        private int followings;
        private int ugc_count;
        private String avatar_url;
        private int followers;
        private boolean is_following;
        private boolean user_verified;

        public long getUser_id() {
            return user_id;
        }

        public void setUser_id(long user_id) {
            this.user_id = user_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getFollowings() {
            return followings;
        }

        public void setFollowings(int followings) {
            this.followings = followings;
        }

        public int getUgc_count() {
            return ugc_count;
        }

        public void setUgc_count(int ugc_count) {
            this.ugc_count = ugc_count;
        }

        public String getAvatar_url() {
            return avatar_url;
        }

        public void setAvatar_url(String avatar_url) {
            this.avatar_url = avatar_url;
        }

        public int getFollowers() {
            return followers;
        }

        public void setFollowers(int followers) {
            this.followers = followers;
        }

        public boolean is_following() {
            return is_following;
        }

        public void setIs_following(boolean is_following) {
            this.is_following = is_following;
        }

        public boolean isUser_verified() {
            return user_verified;
        }

        public void setUser_verified(boolean user_verified) {
            this.user_verified = user_verified;
        }
    }

    public class Activity {

    }

    public class NeihanHotLink {

    }

    public class Comment {

    }

    public class Ad {

        private LongExtra long_extra;
        private int display_image_height;
        private String gif_url;
        private long ad_id;
        private int display_image_width;
        private String title;
        private String track_url;
        private String button_text;
        private String avatar_url;
        private int is_ad;
        private String display_info;
        private int ab_show_style;
        private String web_url;
        private ArrayList<String> track_url_list;
        private int display_type;
        private String avatar_name;
        private long end_time;
        private String label;
        private String type;
        private long id;
        private String display_image;

        public LongExtra getLong_extra() {
            return long_extra;
        }

        public void setLong_extra(LongExtra long_extra) {
            this.long_extra = long_extra;
        }

        public int getDisplay_image_height() {
            return display_image_height;
        }

        public void setDisplay_image_height(int display_image_height) {
            this.display_image_height = display_image_height;
        }

        public String getGif_url() {
            return gif_url;
        }

        public void setGif_url(String gif_url) {
            this.gif_url = gif_url;
        }

        public long getAd_id() {
            return ad_id;
        }

        public void setAd_id(long ad_id) {
            this.ad_id = ad_id;
        }

        public int getDisplay_image_width() {
            return display_image_width;
        }

        public void setDisplay_image_width(int display_image_width) {
            this.display_image_width = display_image_width;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTrack_url() {
            return track_url;
        }

        public void setTrack_url(String track_url) {
            this.track_url = track_url;
        }

        public String getButton_text() {
            return button_text;
        }

        public void setButton_text(String button_text) {
            this.button_text = button_text;
        }

        public String getAvatar_url() {
            return avatar_url;
        }

        public void setAvatar_url(String avatar_url) {
            this.avatar_url = avatar_url;
        }

        public int getIs_ad() {
            return is_ad;
        }

        public void setIs_ad(int is_ad) {
            this.is_ad = is_ad;
        }

        public String getDisplay_info() {
            return display_info;
        }

        public void setDisplay_info(String display_info) {
            this.display_info = display_info;
        }

        public int getAb_show_style() {
            return ab_show_style;
        }

        public void setAb_show_style(int ab_show_style) {
            this.ab_show_style = ab_show_style;
        }

        public String getWeb_url() {
            return web_url;
        }

        public void setWeb_url(String web_url) {
            this.web_url = web_url;
        }

        public ArrayList<String> getTrack_url_list() {
            return track_url_list;
        }

        public void setTrack_url_list(ArrayList<String> track_url_list) {
            this.track_url_list = track_url_list;
        }

        public int getDisplay_type() {
            return display_type;
        }

        public void setDisplay_type(int display_type) {
            this.display_type = display_type;
        }

        public String getAvatar_name() {
            return avatar_name;
        }

        public void setAvatar_name(String avatar_name) {
            this.avatar_name = avatar_name;
        }

        public long getEnd_time() {
            return end_time;
        }

        public void setEnd_time(long end_time) {
            this.end_time = end_time;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getDisplay_image() {
            return display_image;
        }

        public void setDisplay_image(String display_image) {
            this.display_image = display_image;
        }
    }

    public class LongExtra {

        private int rit;
        private String ad_price;
        private String req_id;
        private int convert_id;

        public int getRit() {
            return rit;
        }

        public void setRit(int rit) {
            this.rit = rit;
        }

        public String getAd_price() {
            return ad_price;
        }

        public void setAd_price(String ad_price) {
            this.ad_price = ad_price;
        }

        public String getReq_id() {
            return req_id;
        }

        public void setReq_id(String req_id) {
            this.req_id = req_id;
        }

        public int getConvert_id() {
            return convert_id;
        }

        public void setConvert_id(int convert_id) {
            this.convert_id = convert_id;
        }
    }

}
