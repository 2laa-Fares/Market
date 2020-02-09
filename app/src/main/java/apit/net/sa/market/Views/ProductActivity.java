package apit.net.sa.market.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


import apit.net.sa.market.AppConsts.App;
import apit.net.sa.market.Models.Product;
import apit.net.sa.market.R;

/**
 * Use to view Product data.
 */
public class ProductActivity extends AppCompatActivity {

    /**
     * Back Button.
     */
    private ImageView back;
    /**
     * Discard Button go back to product list activity.
     */
    private Button discard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        App.setContext(this);

        init();

        back.setOnClickListener(v -> ProductActivity.super.onBackPressed());
        discard.setOnClickListener(v -> ProductActivity.super.onBackPressed());
    }

    /**
     * Initiate fields.
     */
    @SuppressLint("SetTextI18n")
    private void init() {
        ImageView productImage = findViewById(R.id.productImage);
        back = findViewById(R.id.back);
        discard = findViewById(R.id.discard);
        TextView title = findViewById(R.id.title);
        TextView amount = findViewById(R.id.amount);
        TextView price = findViewById(R.id.price);

        Product p = (Product) getIntent().getSerializableExtra(getString(R.string.product));
        if(p != null){
            if(p.getImageUrl() != null)
                Picasso.get().load(p.getImageUrl()).into(productImage);
            if(p.getName() != null)
                title.setText(p.getName());
            if(p.getPrice() != null)
                price.setText(p.getPrice());
            if(p.getWeight() != null)
                amount.setText(p.getWeight());
        }
    }
}
