package apit.net.sa.market.Adapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import apit.net.sa.market.AppConsts.App;
import apit.net.sa.market.Models.Product;
import apit.net.sa.market.R;
import apit.net.sa.market.Views.ProductActivity;

/**
 * Adapter to view product list as a pair of product in row.
 */
public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private List<Product> products;
    /**
     * Used to handle which row where user select product to view.
     */
    @SuppressLint("UseSparseArrays")
    private HashMap<Integer, ArrayList<Product>> productsMap = new HashMap<>();

    /**
     * Initializer.
     *
     * @param products list of products to show.
     */
    public ProductAdapter(List<Product> products) {
        this.products = products;
    }

    /**
     * Init view.
     */
    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.product_item, viewGroup, false);
        return new ProductViewHolder(view);
    }

    /**
     * Bind data to view.
     *
     * @param viewHolder view holder object.
     * @param i          position.
     */
    @SuppressLint({"SetTextI18n", "ClickableViewAccessibility"})
    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder viewHolder, @SuppressLint("RecyclerView") final int i) {
        ArrayList<Product> productsList = new ArrayList<>();

        if(products.get(i * 2).getImageUrl() != null)
            Picasso.get().load(products.get(i * 2).getImageUrl()).into(viewHolder.productImg1);
        if(products.get(i * 2).getName() != null)
            viewHolder.productTitle1.setText(products.get(i * 2).getName());
        if(products.get(i * 2).getWeight() != null)
            viewHolder.productAmount1.setText(products.get(i * 2).getWeight());
        if(products.get(i * 2).getPrice() != null)
            viewHolder.productPrice1.setText(products.get(i * 2).getPrice());
        
        productsList.add(products.get(i * 2));

        if (products.size() >= i * 2 + 2) {
            if(products.get(i * 2 + 1).getImageUrl() != null)
                Picasso.get().load(products.get(i * 2 + 1).getImageUrl()).into(viewHolder.productImg2);
            if(products.get(i * 2 + 1).getName() != null)
                viewHolder.productTitle2.setText(products.get(i * 2 + 1).getName());
            if(products.get(i * 2 + 1).getWeight() != null)
                viewHolder.productAmount2.setText(products.get(i * 2 + 1).getWeight());
            if(products.get(i * 2 + 1).getPrice() != null)
                viewHolder.productPrice2.setText(products.get(i * 2 + 1).getPrice());
            
            productsList.add(products.get(i * 2 + 1));
        }else viewHolder.productButton2.setVisibility(View.GONE);
        
        productsMap.put(i, productsList);

        final View.OnClickListener onClickListener1 = view -> viewProduct(viewHolder.getAdapterPosition(), 0);
        viewHolder.productTitle1.setOnClickListener(onClickListener1);
        viewHolder.productButton1.setOnClickListener(onClickListener1);

        final View.OnClickListener onClickListener = view -> viewProduct(viewHolder.getAdapterPosition(), 1);
        viewHolder.productTitle2.setOnClickListener(onClickListener);
        viewHolder.productButton2.setOnClickListener(onClickListener);
    }

    /**
     *
     * @return products number divided by 2, because we view 2 products in same row.
     */
    @Override
    public int getItemCount() {
        if (products != null)
            if (products.size() % 2 == 0) return (products.size() / 2);
            else return (products.size() / 2 + 1);
        return 0;
    }

    /**
     * Init view fields.
     */
    class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView productImg1, productImg2;
        TextView productTitle1, productTitle2, productAmount1, productAmount2, productPrice1, productPrice2;
        FloatingActionButton productButton1, productButton2;

        ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            productImg1 = itemView.findViewById(R.id.productImg1);
            productImg2 = itemView.findViewById(R.id.productImg2);
            productTitle1 = itemView.findViewById(R.id.productTitle1);
            productTitle2 = itemView.findViewById(R.id.productTitle2);
            productAmount1 = itemView.findViewById(R.id.productAmount1);
            productAmount2 = itemView.findViewById(R.id.productAmount2);
            productPrice1 = itemView.findViewById(R.id.productPrice1);
            productPrice2 = itemView.findViewById(R.id.productPrice2);
            productButton1 = itemView.findViewById(R.id.productButton1);
            productButton2 = itemView.findViewById(R.id.productButton2);
        }
    }
    
    private void viewProduct(int productPosition, int tabNo){
        Intent intent = new Intent(App.getContext(), ProductActivity.class);
        ArrayList<Product> p = productsMap.get(productPosition);
        intent.putExtra(App.getContext().getString(R.string.product), p.get(tabNo));
        App.getContext().startActivity(intent);
    }
}
