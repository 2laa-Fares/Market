package apit.net.sa.market.Views;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.List;

import apit.net.sa.market.AppConsts.App;
import apit.net.sa.market.Models.Category;
import apit.net.sa.market.R;

/**
 * Used To view Product List.
 */
public class ProductsActivity extends AppCompatActivity {
    /**
     * View current category image.
     */
    private ImageView categoryImage;
    /**
     * List of categories in same row selected in pervious activity max 2.
     */
    private List<Category> categories;
    /**
     * Used To view list for each category tab.
     */
    private ViewPager viewPager;
    /**
     * Back Button.
     */
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        App.setContext(this);

        init();

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Picasso.get().load(categories.get(position).getImageUrl()).into(categoryImage);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        back.setOnClickListener(v -> ProductsActivity.super.onBackPressed());
    }

    /**
     * Initiate fields.
     */
    @SuppressLint("SetTextI18n")
    private void init() {
        viewPager = findViewById(R.id.view_pager);
        TabLayout tabs = findViewById(R.id.tabDots);
        tabs.setupWithViewPager(viewPager, true);
        categoryImage = findViewById(R.id.categoryImage);
        TextView categoriesTitle = findViewById(R.id.categoriesTitle);
        back = findViewById(R.id.back);

        int position = getIntent().getIntExtra(getString(R.string.tab_no), 0);
        categories = (List<Category>) getIntent().getSerializableExtra(getString(R.string.categories));
        //categories = Utils.getList(this, getString(R.string.categories));

        //The pager adapter, which provides the pages to the view pager widget.
        PagerAdapter pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(position);
        Picasso.get().load(categories.get(position).getImageUrl()).into(categoryImage);

        if (categories != null && categories.size() > 0)
            if (categories.size() > 1) {
                categoriesTitle.setText(categories.get(0).getName() + " & " + categories.get(1).getName());
                tabs.getTabAt(0).setText(categories.get(0).getName());
                tabs.getTabAt(1).setText(categories.get(1).getName());
            } else {
                categoriesTitle.setText(categories.get(0).getName());
                tabs.getTabAt(0).setText(categories.get(0).getName());
            }
    }


    /**
     * A simple pager adapter that represents 2 ScreenSlidePageFragment objects, to
     * view lists of products which related to selected  category tab
     * */
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {

        private ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            ProductsFragment productsFragment = new ProductsFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable(getString(R.string.products), (Serializable) categories.get(position).getProducts());
            productsFragment.setArguments(bundle);
            return productsFragment;
        }

        @Override
        public int getCount() {
            //The number of pages.
            return categories.size();
        }
    }

}
