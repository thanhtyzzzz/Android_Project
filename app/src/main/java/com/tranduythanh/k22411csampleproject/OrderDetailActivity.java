package com.tranduythanh.k22411csampleproject;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.tranduythanh.adapters.OrderDetailAdapter;
import com.tranduythanh.connectors.OrderDetailConnector;
import com.tranduythanh.connectors.SQLiteConnector;
import com.tranduythanh.models.OrderDetails;
import java.util.ArrayList;

public class OrderDetailActivity extends AppCompatActivity {

    ListView lvOrderDetails;
    OrderDetailAdapter adapter;
    TextView txtOrderCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        // Lấy OrderId và OrderCode từ Intent
        int orderId = getIntent().getIntExtra("ORDER_ID", -1);
        String orderCode = getIntent().getStringExtra("ORDER_CODE");

        // Thiết lập giao diện
        txtOrderCode = findViewById(R.id.txtOrderCode);
        txtOrderCode.setText("Order Code: " + orderCode);

        lvOrderDetails = findViewById(R.id.lvOrderDetails);
        adapter = new OrderDetailAdapter(this, R.layout.item_oder_detail);
        lvOrderDetails.setAdapter(adapter);

        // Query dữ liệu chi tiết hóa đơn
        SQLiteConnector connector = new SQLiteConnector(this);
        OrderDetailConnector odc = new OrderDetailConnector();
        ArrayList<OrderDetails> orderDetails = odc.getOrderDetailsByOrderId(connector.openDatabase(), orderId);
        adapter.addAll(orderDetails);
    }
}