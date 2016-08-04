package io.github.marktony.reader.home;

import java.util.List;

import io.github.marktony.reader.BasePresenter;
import io.github.marktony.reader.BaseView;
import io.github.marktony.reader.data.QsbkArticle;

/**
 * Created by Lizhaotailang on 2016/8/4.
 */

public interface QsbkContract {

    interface View extends BaseView<Presenter> {
        void showArticle(List<QsbkArticle> articleList);
    }

    interface Presenter extends BasePresenter {
        void loadArticle();
    }

}
