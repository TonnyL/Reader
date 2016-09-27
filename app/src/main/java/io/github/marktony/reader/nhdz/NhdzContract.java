package io.github.marktony.reader.nhdz;

import java.util.ArrayList;

import io.github.marktony.reader.BasePresenter;
import io.github.marktony.reader.BaseView;
import io.github.marktony.reader.data.Neihanduanzi;

/**
 * Created by Lizhaotailang on 2016/8/5.
 */

public interface NhdzContract {

    interface View extends BaseView<NhdzContract.Presenter> {

        void showResult(ArrayList<Neihanduanzi.Data> list);

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
