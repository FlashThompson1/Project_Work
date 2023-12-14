package com.example.foodapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.foodapp.R;

import Domain.FoodDomain;
import Helper.ManagmentCart;

public class ShowDetailActivity extends AppCompatActivity {
    private TextView addticartbtn;
    private  TextView title,feetxt,descriptiontxt,numberordertxt,totalPriceTxt,startxt,calorytxt,timetxt;
    private ImageView plusbtn,minusbtn,picfood;
    private FoodDomain object;
    private  int numberorder = 1;
    private ManagmentCart managmentCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail);

        managmentCart = new ManagmentCart(this);

                initView();
                getBundle();
    }

    private void getBundle() {
        object = (FoodDomain)getIntent().getSerializableExtra("object");

        int drawableResourcesId = this.getResources().getIdentifier(object.getPic(),"drawable",this.getPackageName());
        Glide.with(this).load(drawableResourcesId).into(picfood);

        title.setText(object.getTitle());
        feetxt.setText("$" + object.getFee());
        descriptiontxt.setText(object.getDescription());
        numberordertxt.setText(String.valueOf(numberorder));
        calorytxt.setText(object.getCalories() + "Calories");
        startxt.setText(object.getStar() + "");
        timetxt.setText(object.getTime() + "minute");
        totalPriceTxt.setText(String.valueOf("$" +Math.round(numberorder * object.getFee())));
        plusbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberorder= numberorder+ 1;
                numberordertxt.setText(String.valueOf(numberorder));
                totalPriceTxt.setText(String.valueOf("$" +Math.round(numberorder * object.getFee())));
            }
        });

        minusbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(numberorder > 1){
                    numberorder = numberorder-1;


                }
                numberordertxt.setText(String.valueOf(numberorder));
                totalPriceTxt.setText(String.valueOf("$" +Math.round(numberorder * object.getFee())));
            }
        });

        addticartbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                object.setNumberInCart(numberorder);
                managmentCart.insertFood(object);
            }
        });


    }

    private void initView() {
        addticartbtn = findViewById(R.id.addtocartbtn);
        title = findViewById(R.id.producttitle);
        feetxt  = findViewById(R.id.priceTxt);
        descriptiontxt = findViewById(R.id.descriptiontxt);
        numberordertxt = findViewById(R.id.numberItemTxt);
        plusbtn = findViewById(R.id.plusCardbtn);
        minusbtn = findViewById(R.id.minusCardbtn);
        picfood = findViewById(R.id.foodpic);
        totalPriceTxt = findViewById(R.id.totalPricetxt);
        startxt = findViewById(R.id.starttxt);
        timetxt = findViewById(R.id.timetxt);
        calorytxt = findViewById(R.id.textVicaloriesTxt);



    }
}