package test.rasel.myapplication;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Rasel on 6/11/2017.
 */

public interface HomeNetworkCall {
    @GET("Categories/LoadCategories")
    Call<List<CategoryModel>> getAllCatData();
}
