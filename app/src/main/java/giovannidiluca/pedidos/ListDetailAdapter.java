package giovannidiluca.pedidos;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import giovannidiluca.pedidos.Order.OrderDetail;

/**
 * Created by Matheus on 13/10/2017.
 */

public class ListDetailAdapter extends BaseAdapter {
    private final Activity act;
    private final List<OrderDetail> orders;


    public ListDetailAdapter(Activity act, List<OrderDetail> orders) {
        this.orders = orders;
        this.act = act;
    }

    @Override
    public int getCount() {
        return orders.size();
    }

    @Override
    public Object getItem(int i) {
        return orders.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = act.getLayoutInflater().inflate(R.layout.activity_orderdetails_itemlayout, parent, false);

        //OrderDetail order = orders.get(position);

        ImageView imgView = (ImageView) rowView.findViewById(R.id.imageView_itemlistdetails);
        TextView description = (TextView) rowView.findViewById(R.id.description_itemlist_details);
        TextView quantity = (TextView) rowView.findViewById(R.id.quantity_listdetails);
        TextView price = (TextView) rowView.findViewById(R.id.price_listdetails);

        description.setText(orders.get(position).getProduct().getName());
        quantity.setText(orders.get(position).getQuantity());
        price.setText("R$ " + orders.get(position).getPrice());
        Picasso.with(act).load(orders.get(position).getProduct().getImage()).into(imgView);

        return rowView;
    }
}
