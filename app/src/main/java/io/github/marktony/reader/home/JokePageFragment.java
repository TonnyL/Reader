package io.github.marktony.reader.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.github.marktony.reader.R;
import io.github.marktony.reader.adapter.JokeFragmentPagerAdapter;

/**
 * Created by Lizhaotailang on 2016/8/5.
 */

public class JokePageFragment extends Fragment {

    public JokePageFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.view_pager);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(new JokeFragmentPagerAdapter(getChildFragmentManager(), getContext()));
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tab);
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }

}
