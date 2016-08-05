package io.github.marktony.reader.joke;

import java.util.ArrayList;

import io.github.marktony.reader.BasePresenter;
import io.github.marktony.reader.BaseView;
import io.github.marktony.reader.data.JiandanArticle;

/**
 * Created by Lizhaotailang on 2016/8/5.
 */

public interface JiandanContract {

    interface View extends BaseView<Presenter> {
        void showArticle(ArrayList<JiandanArticle> articleList);
    }

    interface Presenter extends BasePresenter {

        void loadArticle(Boolean forceRefresh);

        void loadMore();
    }

}
