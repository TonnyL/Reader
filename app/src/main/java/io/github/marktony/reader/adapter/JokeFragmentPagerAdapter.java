package io.github.marktony.reader.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import io.github.marktony.reader.jd.JiandanFragment;
import io.github.marktony.reader.jd.JiandanPresenter;
import io.github.marktony.reader.nhdz.NhdzFragment;
import io.github.marktony.reader.nhdz.NhdzPresenter;
import io.github.marktony.reader.qsbk.QsbkFragment;
import io.github.marktony.reader.qsbk.QsbkPresenter;

/**
 * Created by Lizhaotailang on 2016/8/5.
 */


public class JokeFragmentPagerAdapter extends FragmentPagerAdapter {

    private String[] titles = {"糗事百科", "煎蛋", "内涵段子" };
    private Context context;

    public JokeFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 1){
            JiandanFragment fragment = JiandanFragment.newInstance(position + 1);
            new JiandanPresenter(context, fragment);
            return fragment;
        } else if (position == 2){
            NhdzFragment fragment = NhdzFragment.newInstance(position + 1);
            new NhdzPresenter(context, fragment);
            return fragment;
        }
        QsbkFragment fragment = QsbkFragment.newInstance(position + 1);
        new QsbkPresenter(context, fragment);
        return fragment;
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

}
