package com.example.foodapp.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.R;

import Adapter.CartListAdapter;
import Helper.ManagmentCart;
import Interface.ChangerNumberItemListener;

public class CartActivity extends AppCompatActivity{
private RecyclerView.Adapter adapter;
private  RecyclerView recyclerViewList;
private ManagmentCart managmentCart;
private TextView totalFeeText , taxTxt, deliveryTxt,totalTxt,emptyTxt;
private  double tax;
private ScrollView scrollView;
    @Override
    protected void onCreate(Bundle savedInstanceBundle) {

        super.onCreate(savedInstanceBundle);
        setContentView(R.layout.activity_cart);

        managmentCart = new ManagmentCart(this);

        initView();
        initList();
        calculateCard();

    }

    private void initList() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerViewList.setLayoutManager(linearLayoutManager);
        adapter = new CartListAdapter(managmentCart.getListCart(), this, this::calculateCard);
        recyclerViewList.setAdapter(adapter);
        if (managmentCart.getListCart().isEmpty()){
            emptyTxt.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);
        }else {
            emptyTxt.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
        }
    }

    private void calculateCard() {
        double percentTax = 0.05;
        double delivery = 10;

        tax = Math.round((managmentCart.getTotal()*percentTax)*100.0)/100.0;
        double total = Math.round((managmentCart.getTotal()+tax+delivery)*100.0) / 100.0;
        double itemTotal=Math.round(managmentCart.getTotal()*100.0) / 100.0;

        totalFeeText.setText("$" + itemTotal);
        taxTxt.setText("$" + tax);
        deliveryTxt.setText("$" + delivery);
        totalTxt.setText("$" + total);

    }

    private void initView() {
        totalFeeText = findViewById(R.id.total_text);
        taxTxt = findViewById(R.id.tax_text);
        deliveryTxt = findViewById(R.id.delivery_text);
        totalTxt = findViewById(R.id.total_amount_text);
        recyclerViewList=findViewById(R.id.view);
        scrollView = findViewById(R.id.scrollView);
        emptyTxt = findViewById(R.id.emptytext);
    }
}
