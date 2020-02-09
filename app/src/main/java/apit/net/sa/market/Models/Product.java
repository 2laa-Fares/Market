package apit.net.sa.market.Models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Product model.
 */
public class Product implements Serializable {
    //Product ID.
    @SerializedName("id")
    @Expose
    private String ID;

    //Product name.
    @SerializedName("name")
    @Expose
    private String Name;

    //Product weight.
    @SerializedName("weight")
    @Expose
    private String Weight;

    //Product price.
    @SerializedName("price")
    @Expose
    private String Price;

    //Product image url.
    @SerializedName("product_img")
    @Expose
    private String ImageUrl;

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

    public String getWeight() {
        return Weight;
    }

    public void setWeight(String weight) {
        Weight = weight;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }
}
