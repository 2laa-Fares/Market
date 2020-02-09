package apit.net.sa.market.Network;


import java.util.List;

import apit.net.sa.market.Models.Category;
import retrofit2.Call;
import retrofit2.http.GET;

public interface APIService {

    /**
     * This API call for get all categories data.
     *
     * @return List of categories.
     */
    @GET("task/categories")
    Call<List<Category>> getCategories();
}
