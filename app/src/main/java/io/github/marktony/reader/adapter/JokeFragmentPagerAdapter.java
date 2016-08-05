package io.github.marktony.reader.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import io.github.marktony.reader.joke.JiandanFragment;
import io.github.marktony.reader.joke.NhdzFragment;
import io.github.marktony.reader.joke.QsbkFragment;

/**
 * Created by Lizhaotailang on 2016/8/5.
 */

/**
 *  pay attention to FragmentStatePagerAdapter
 *  it is not FragmentPagerAdapter
 */
public class JokeFragmentPagerAdapter extends FragmentStatePagerAdapter {

    private String[] titles = {"糗事百科", "煎蛋", "内涵段子" };

    public JokeFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 1){
            return JiandanFragment.newInstance(position + 1);
        } else if (position == 2){
            return NhdzFragment.newInstance(position + 1);
        }
        return QsbkFragment.newInstance(position + 1);
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
