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

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import apit.net.sa.market.AppConsts.App;
import apit.net.sa.market.Models.Category;
import apit.net.sa.market.R;
import apit.net.sa.market.Views.ProductsActivity;

/**
 * Adapter to view category list as a pair of category in row.
 */
public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private List<Category> categories;
    /**
     * Used to handle which row categories user select to view.
     */
    @SuppressLint("UseSparseArrays")
    private HashMap<Integer, ArrayList<Category>> categoriesaMap = new HashMap<>();

    /**
     * Initializer.
     *
     * @param categories list of categories to show.
     */
    public CategoryAdapter(List<Category> categories) {
        this.categories = categories;
    }

    /**
     * Init view.
     */
    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.category_item, viewGroup, false);
        return new CategoryViewHolder(view);
    }

    /**
     * Bind data to view.
     *
     * @param viewHolder view holder object.
     * @param i          position.
     */
    @SuppressLint({"SetTextI18n", "ClickableViewAccessibility"})
    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder viewHolder, @SuppressLint("RecyclerView") final int i) {
        ArrayList<Category> categoriyList = new ArrayList<>();

        categoriyList.add(categories.get(i * 2));
        Picasso.get().load(categories.get(i * 2).getImageUrl()).into(viewHolder.cat1);
        viewHolder.text1.setText(categories.get(i * 2).getName());
        if (categories.size() >= i * 2 + 2) {
            categoriyList.add(categories.get(i * 2 + 1));
            Picasso.get().load(categories.get(i * 2 + 1).getImageUrl()).into(viewHolder.cat2);
            viewHolder.text2.setText(categories.get(i * 2 + 1).getName());
        }
        categoriesaMap.put(i, categoriyList);

        viewHolder.cat1.setOnClickListener(view -> goToProducts(0, viewHolder.getAdapterPosition()));

        viewHolder.cat2.setOnClickListener(view -> goToProducts(1, viewHolder.getAdapterPosition()));
    }

    /**
     *
     * @return categories number divided by 2, because we view 2 categories in same row.
     */
    @Override
    public int getItemCount() {
        if (categories != null)
            if (categories.size() % 2 == 0) return (categories.size() / 2);
            else return (categories.size() / 2 + 1);
        return 0;
    }

    /**
     * Init view fields.
     */
    class CategoryViewHolder extends RecyclerView.ViewHolder {
        ImageView cat1, cat2;
        TextView text1, text2;

        CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            cat1 = itemView.findViewById(R.id.cat1);
            cat2 = itemView.findViewById(R.id.cat2);
            text1 = itemView.findViewById(R.id.text1);
            text2 = itemView.findViewById(R.id.text2);
        }
    }

    /**
     * Go to product activity when click image.
     * @param tabNo to determint which tab will be active.
     * @param holder to get 2 categories of selected row to show.
     */
    private void goToProducts(int tabNo, int holder) {
        //Utils.storeSerializableList(App.getContext(), App.getContext().getString(R.string.categories), categoriesaMap.get(holder));
        Intent intent = new Intent(App.getContext(), ProductsActivity.class);
        intent.putExtra(App.getContext().getString(R.string.tab_no), tabNo);
        intent.putExtra(App.getContext().getString(R.string.categories), categoriesaMap.get(holder));
        App.getContext().startActivity(intent);
    }
}
