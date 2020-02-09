package apit.net.sa.market.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Category model.
 */
public class Category implements Serializable {
    // Category ID.
    @SerializedName("id")
    @Expose
    private String ID;

    // Category Name.
    @SerializedName("name")
    @Expose
    private String Name;

    // Category Image url.
    @SerializedName("category_img")
    @Expose
    private String ImageUrl;

    // List of products related to category.
    @SerializedName("products")
    @Expose
    private List<Product> Products;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public List<Product> getProducts() {
        return Products;
    }

    public void setProducts(List<Product> products) {
        Products = products;
    }
}
