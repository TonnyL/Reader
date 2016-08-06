package io.github.marktony.reader.joke;

import java.util.ArrayList;

import io.github.marktony.reader.BasePresenter;
import io.github.marktony.reader.BaseView;
import io.github.marktony.reader.data.QsbkArticle;

/**
 * Created by Lizhaotailang on 2016/8/4.
 */

public interface QsbkContract {

    interface View extends BaseView<Presenter> {
        void showArticle(ArrayList<QsbkArticle> articleList);
    }

    interface Presenter extends BasePresenter {
        void loadArticle(Boolean forceRefresh);

        QsbkArticle getElement(int position);
    }

}
