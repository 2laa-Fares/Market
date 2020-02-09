package apit.net.sa.market.Views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import apit.net.sa.market.Adapter.CategoryAdapter;
import apit.net.sa.market.AppConsts.App;
import apit.net.sa.market.Interfaces.CategoryInterface;
import apit.net.sa.market.Intractors.CategoryIntractor;
import apit.net.sa.market.Models.Category;
import apit.net.sa.market.Models.Progress;
import apit.net.sa.market.Presenter.CategoryPresenter;
import apit.net.sa.market.R;
import apit.net.sa.market.Utils.Utils;

/**
 * Home activity list the categories.
 */
public class HomeActivity extends AppCompatActivity implements CategoryInterface.categoryView {

    /**
     * Used to refresh data get from server.
     */
    private SwipeRefreshLayout swipeRefreshLayout;
    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next pictures.
     */
    private ViewPager pager;
    /**
     * Used to view categories.
     */
    private RecyclerView recycler;

    /**
     * The Categories presenter used to request data.
     */
    private CategoryInterface.categoryPresenter presenter;
    /**
     * Progress Dialog.
     */
    private Progress progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        App.setContext(this);

        init();

         swipeRefreshLayout.setOnRefreshListener(() -> {
            if (Utils.isOnline())
                presenter.requestCategories();
            else {
                swipeRefreshLayout.setRefreshing(false);
                Utils.alart(getString(R.string.no_internet));
            }
        });
    }

    /**
     * Initiate fields.
     */
    private void init() {
        swipeRefreshLayout = findViewById(R.id.swiper);
        pager = findViewById(R.id.pager);
        recycler = findViewById(R.id.recycler);
        progress = new Progress(this);
        TabLayout tabLayout = findViewById(R.id.tabDots);
        tabLayout.setupWithViewPager(pager, true);

        RecyclerView.LayoutManager manager = new LinearLayoutManager(App.getContext());
        recycler.setLayoutManager(manager);

        presenter = new CategoryPresenter(this, new CategoryIntractor());
        if (Utils.isOnline()) {
            progress.progressON();
            presenter.requestCategories();
        } else Utils.alart(getString(R.string.no_internet));
    }

    /**
     *
     * @param categories categories gets from server.
     */
    @Override
    public void getCategoriesSuccessfully(List<Category> categories) {
        progress.progressOFF();
        if (swipeRefreshLayout.isRefreshing())
            swipeRefreshLayout.setRefreshing(false);

        if (categories != null && categories.size() > 0) {
            List<String> images = new ArrayList<>();
            for (Category c : categories) {
                images.add(c.getImageUrl());
            }

            MyPager myPager = new MyPager(this, images);
            //myPager.notifyDataSetChanged();
            pager.setAdapter(myPager);

            recycler.removeAllViews();
            CategoryAdapter adapter = new CategoryAdapter(categories);
            recycler.setAdapter(adapter);

        } else {
            Utils.alart(getString(R.string.error));
        }
    }

    /**
     *
     * @param throwable the error occurred.
     */
    @Override
    public void failedToGetCategories(Throwable throwable) {
        progress.progressOFF();
        if (swipeRefreshLayout.isRefreshing()) swipeRefreshLayout.setRefreshing(false);
        Utils.alart(getString(R.string.error));
    }

    /**
     * Pager Adapter to view categories images in slider.
     */
    public class MyPager extends PagerAdapter {
        private Context context;
        private List<String> images;

        private MyPager(Context context, List<String> images) {
            this.context = context;
            this.images = images;
        }

        /*
        This callback is responsible for creating a image view. We inflate the layout and set the drawable
        to the ImageView based on the position. In the end we add the inflated layout to the parent
        container.
        */
        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            @SuppressLint("InflateParams") View view = LayoutInflater.from(context).inflate(R.layout.image_layout, null);
            ImageView imageView = view.findViewById(R.id.image);
            Picasso.get().load(images.get(position)).into(imageView);
            container.addView(view);
            return view;
        }

        /*
        This callback is responsible for destroying a page. Since we are using view only as the
        object key we just directly remove the view from parent container
        */
        @Override
        public void destroyItem(ViewGroup container, int position, @NonNull Object view) {
            container.removeView((View) view);
        }

        /*
        Returns the count of the total pages
        */
        @Override
        public int getCount() {
            return images.size();
        }

        /*
        Used to determine whether the page view is associated with object key returned by instantiateItem.
        Since here view only is the key we return view==object
        */
        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return object == view;
        }
    }
}
