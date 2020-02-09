package apit.net.sa.market.Intractors;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import apit.net.sa.market.Interfaces.CategoryInterface;
import apit.net.sa.market.Models.Category;
import apit.net.sa.market.Network.APIClient;
import apit.net.sa.market.Network.APIService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryIntractor implements CategoryInterface.categoryIntractor {

    private List<Category> categories = new ArrayList<>();

    /**
     * Request categories from server.
     *
     * @param categoryListener the category listener
     */
    @Override
    public void getCategories(final categoryListener categoryListener) {
        APIService apiService = APIClient.getInstanceRetrofit().create(APIService.class);
        Call<List<Category>> call = apiService.getCategories();
        call.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(@Nullable Call<List<Category>> call, @Nullable Response<List<Category>> response) {
                assert response != null;
                categoryListener.onfinished(response.body());
            }

            @Override
            public void onFailure(@Nullable Call<List<Category>> call, @Nullable Throwable t) {
                categoryListener.onFail(t);
            }
        });
    }

}
