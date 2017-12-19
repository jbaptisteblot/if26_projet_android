package fr.utt.if26.if26_sncfprojet;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;

/**
 * Test
 * Created by Jeanba on 19/12/2017.
 */

public class CustomPageAdapter extends FragmentPagerAdapter {
    public CustomPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {

            case 2:
                return new Configuration();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
