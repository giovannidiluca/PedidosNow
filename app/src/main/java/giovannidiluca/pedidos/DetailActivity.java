package giovannidiluca.pedidos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.squareup.picasso.Picasso;

import giovannidiluca.pedidos.Order.OrderDetail;

public class DetailActivity extends AppCompatActivity {

    String codeEANGlobal = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        final OrderDetail order = (OrderDetail) intent.getSerializableExtra("produto");

        TextView quantity = (TextView) findViewById(R.id.quantity);
        TextView price = (TextView) findViewById(R.id.price);
        TextView description = (TextView) findViewById(R.id.description);
        ImageView imgView = (ImageView) findViewById(R.id.imageViewDetail);
        Button btntestEAN = (Button) findViewById(R.id.btn_test_ean);

        btntestEAN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (order.getProduct().getEan() != null && order.getProduct().getEan().length() > 7) {
                        confereEAN(order.getProduct().getEan());
                    } else {
                        Toast.makeText(DetailActivity.this, "Este produto não contém código EAN", Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        quantity.setText(order.getQuantity());
        price.setText("R$ " + order.getPrice());
        description.setText(order.getProduct().getName());
        Picasso.with(this).load(order.getProduct().getImage()).into(imgView);
    }

    public void confereEAN(String codeEAN) {
        codeEANGlobal = codeEAN;
        IntentIntegrator intentIntegrator = new IntentIntegrator(this);
        intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        intentIntegrator.setCameraId(0);
        intentIntegrator.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents().equals(codeEANGlobal)) {
                Toast.makeText(this, "Produto Correto", Toast.LENGTH_LONG).show();
            }
            if (!result.getContents().equals(codeEANGlobal)) {
                Toast.makeText(this, "Produto Incorreto", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Erro ao escanear", Toast.LENGTH_SHORT).show();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}