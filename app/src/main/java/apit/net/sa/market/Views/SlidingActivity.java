package apit.net.sa.market.Views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import apit.net.sa.market.AppConsts.App;
import apit.net.sa.market.R;

/**
 * The Sliding activity, which is a sliding introduction of three slides.
 */
public class SlidingActivity extends AppCompatActivity {

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private ViewPager mPager;
    private Boolean isLastPageSwiped = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sliding);
        App.setContext(this);

        init();

        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position == 2 && isLastPageSwiped){
                    Intent homeIntant = new Intent(SlidingActivity.this, HomeActivity.class);
                    startActivity(homeIntant);
                }
            }

            @Override
            public void onPageSelected(int position) {
                isLastPageSwiped = position == 2;

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * Initiate fields.
     */
    private void init() {
        // Instantiate a ViewPager and a PagerAdapter.
        mPager = findViewById(R.id.pager);
        TabLayout tabLayout = findViewById(R.id.tabDots);
        tabLayout.setupWithViewPager(mPager, true);

        //The pager adapter, which provides the pages to the view pager widget.
        PagerAdapter pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(pagerAdapter);
    }

    /**
     * Handle on back press depend on current slide.
     */
    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }

    /**
     * A simple pager adapter that represents 3 ScreenSlidePageFragment objects, in
     * sequence.
     */
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        private ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            SlidePageFragment slidePageFragment = new SlidePageFragment();
            Bundle bundle = new Bundle();
            bundle.putInt(getString(R.string.sliding_no), position);
            slidePageFragment.setArguments(bundle);
            return slidePageFragment;
        }

        @Override
        public int getCount() {
            //The number of pages.
            return 3;
        }
    }
}
