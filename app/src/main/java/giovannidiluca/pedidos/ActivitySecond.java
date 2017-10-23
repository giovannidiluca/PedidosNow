package giovannidiluca.pedidos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.Serializable;
import java.util.List;

import giovannidiluca.pedidos.Order.OrderDetail;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivitySecond extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();
        int id = (int) intent.getSerializableExtra("id");

        getDetails(id);


    }

    private void getDetails(int orderID) {
        RetrofitService service = RetrofitService.retrofit.create(RetrofitService.class);
        Call<List<OrderDetail>> orderCall = service.getOrder(orderID);

        orderCall.enqueue(new Callback<List<OrderDetail>>() {
            @Override
            public void onResponse(Call<List<OrderDetail>> call, Response<List<OrderDetail>> response) {
                if (response.isSuccessful()) {
                    createListView(response.body());
                } else {
                    Log.i("testePedido", "Falha: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<OrderDetail>> call, Throwable t) {
                Log.i("testePedido", "ERRO: " + t.getMessage());
            }
        });

    }

    private void createListView(List<OrderDetail> order) {
        ListView listViewDetail = (ListView) findViewById(R.id.listviewdetails);
        final ListDetailAdapter adapter = new ListDetailAdapter(ActivitySecond.this, order);
        listViewDetail.setAdapter(adapter);

        listViewDetail.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ActivitySecond.this, DetailActivity.class);
                intent.putExtra("produto", (Serializable) adapter.getItem(i));
                startActivity(intent);
            }
        });

    }


}
