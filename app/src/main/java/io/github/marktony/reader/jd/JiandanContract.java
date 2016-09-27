package io.github.marktony.reader.jd;

import java.util.ArrayList;

import io.github.marktony.reader.BasePresenter;
import io.github.marktony.reader.BaseView;
import io.github.marktony.reader.data.Jiandan;

/**
 * Created by Lizhaotailang on 2016/8/5.
 */

public interface JiandanContract {

    interface View extends BaseView<Presenter> {

        void showResult(ArrayList<Jiandan.Comment> articleList);

        void startLoading();

        void stopLoading();

        void initViews(android.view.View view);

        void showLoadError();

    }

    interface Presenter extends BasePresenter {

        void requestArticles(Boolean forceRefresh);

        void loadMore();

        void shareTo(int position);

        void copyToClipboard(int position);

    }

}
