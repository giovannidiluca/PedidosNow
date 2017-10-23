package giovannidiluca.pedidos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import giovannidiluca.pedidos.Order.OrderSimple;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSimple();

    }

    private void getSimple() {
        RetrofitService service = RetrofitService.retrofit.create(RetrofitService.class);
        Call<List<OrderSimple>> orderCall = service.getListOrders();

        orderCall.enqueue(new Callback<List<OrderSimple>>() {
            @Override
            public void onResponse(Call<List<OrderSimple>> call, Response<List<OrderSimple>> response) {
                if (response.isSuccessful()) {
                    createListView(response.body());
                } else {
                    Log.i("testePedido", "Falha: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<OrderSimple>> call, Throwable t) {
                Log.i("testePedido", "ERRO: " + t.getMessage());
            }
        });
    }

    private void createListView(List<OrderSimple> order) {
        ListView listViewSimple = (ListView) findViewById(R.id.listviewsimple);
        final ArrayAdapter<OrderSimple> adapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, order);
        listViewSimple.setAdapter(adapter);

        listViewSimple.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, ActivitySecond.class);
                intent.putExtra("id", adapter.getItem(i).getId());
                startActivity(intent);
            }
        });

    }

}
