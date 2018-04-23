package com.rongke.baselibrary.base.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/4/24.
 */

public class BaseFragmentPagerAdapter extends FragmentStatePagerAdapter {
    ArrayList<Fragment> list;
    ArrayList<String> list_title;
    private Context context;

    public BaseFragmentPagerAdapter(FragmentManager fm, Context context, ArrayList<Fragment> list, ArrayList<String> list_title) {
        super(fm);
        this.context = context;
        this.list_title=list_title;
        this.list=list;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list==null?0:list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return list_title.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }
}
