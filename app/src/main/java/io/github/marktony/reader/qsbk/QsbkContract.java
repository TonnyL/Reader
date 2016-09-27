package io.github.marktony.reader.qsbk;

import java.util.ArrayList;

import io.github.marktony.reader.BasePresenter;
import io.github.marktony.reader.BaseView;
import io.github.marktony.reader.data.Qiushibaike;

/**
 * Created by Lizhaotailang on 2016/8/4.
 */

public interface QsbkContract {

    interface View extends BaseView<Presenter> {

        void showResult(ArrayList<Qiushibaike.Item> articleList);

        void startLoading();

        void stopLoading();

        void showLoadError();

    }

    interface Presenter extends BasePresenter {

        void loadArticle(Boolean forceRefresh);

        void loadMore();

        void shareTo(int position);

        void copyToClipboard(int position);

    }

}
