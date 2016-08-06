package io.github.marktony.reader;

import android.view.View;

/**
 * Created by Lizhaotailang on 2016/8/4.
 */

public interface BaseView<T> {

    void setPresenter(T presenter);

    void initViews(View view);

}
