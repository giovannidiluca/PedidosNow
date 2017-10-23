package giovannidiluca.pedidos;

import java.util.List;

import giovannidiluca.pedidos.Order.OrderDetail;
import giovannidiluca.pedidos.Order.OrderSimple;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

/**
 * Created by Matheus on 09/10/2017.
 */

public interface RetrofitService {

    @Headers({"Accept: text/json", "X-Requested-With: XMLHttpRequest"})
    @GET("orders")
    Call<List<OrderSimple>> getListOrders();

    @Headers({"Accept: text/json", "X-Requested-With: XMLHttpRequest"})
    @GET("orders/{order}")
    Call<List<OrderDetail>> getOrder(@Path("order") int order);


    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://homolog.supermercadonow.com.br/api/selective-process/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
