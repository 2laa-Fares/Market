package apit.net.sa.market.Views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import apit.net.sa.market.Adapter.ProductAdapter;
import apit.net.sa.market.AppConsts.App;
import apit.net.sa.market.Models.Product;
import apit.net.sa.market.R;

/**
 * Used to view products list depend on selected tab.
 */
public class ProductsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_products, container, false);

        init(rootView);

        return rootView;
    }


    /**
     * Initiate fields.
     */
    private void init(View view) {
        RecyclerView productsRecyclerView = view.findViewById(R.id.productsRecyclerView);

        if(getArguments() != null) {
            List<Product> products = (List<Product>) getArguments().getSerializable(App.getContext().getString(R.string.products));
            RecyclerView.LayoutManager manager = new LinearLayoutManager(App.getContext());
            productsRecyclerView.setLayoutManager(manager);

            ProductAdapter adapter = new ProductAdapter(products);
            productsRecyclerView.setAdapter(adapter);
        }
    }

}