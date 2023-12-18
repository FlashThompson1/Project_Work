package com.example.foodapp.Activity

import Adapter.CategoryAdapter
import Adapter.RecommendedAdapter
import Domain.CategoryDomain
import Domain.FoodDomain
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp.R


class SecondActivity : AppCompatActivity() {
    lateinit var adapter: RecyclerView.Adapter<*>
    lateinit var adapter2: RecyclerView.Adapter<*>
    lateinit var recyclerViewCategoryList: RecyclerView
    lateinit var recyclerViewPopularList: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        recyclerViewCategory();
        recyclerViewPopular();
        val cartbutton = findViewById<Button>(R.id.cartbtn);
        val homebtn = findViewById<Button>(R.id.homebtn);

        val enteredName = intent.getStringExtra("name")

        val textView = findViewById<TextView>(R.id.nametext)
        textView.text = enteredName






        homebtn.setOnClickListener{
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent);
        }
        cartbutton.setOnClickListener{
            val intent = Intent(this, CartActivity::class.java)
            startActivity(intent);
        }


    }




    private fun recyclerViewPopular() {
        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewPopularList = findViewById(R.id.view2)
        recyclerViewPopularList.layoutManager = linearLayoutManager

        val foodlist = ArrayList<FoodDomain>()
        foodlist.add(FoodDomain("Pepperoni pizza","pizza1","slices pepperoni, mozzarella cheese, fresh oregano, ground black pepper, pizza sauce", 16.0,5,20,1000))
        foodlist.add(FoodDomain("Cheese Burger","burger1","beef, Gouda Cheese, Special sauce, Lettuce, tomato", 12.0,4,15,1500))
        foodlist.add(FoodDomain("Black Burger","blackburger","beef, Cheddar Cheese, Special sauce, Salted Cucumbers, Lettuce, tomato", 13.0,5,16,1600))
        foodlist.add(FoodDomain("Vegetable pizza","cat_1","olive oil, Vegetable oil, pitted Kalamata, cherry tomatoes, fresh oregano, basil", 15.0,3,18,800))
        foodlist.add(FoodDomain("Sandwich","sandwich","fried bread,  Cheddar cheese, cherry tomatoes, lettuce leaf", 8.0,4,13,500))
        foodlist.add(FoodDomain("Hot-Dog","cat_3","Sausages for hot dogs,  Hot dog buns, Mustard, Ketchup,Mayonnaise", 5.0,3,10,900))
        adapter2= RecommendedAdapter(foodlist)
        recyclerViewPopularList.adapter = adapter2
    }


    private fun recyclerViewCategory() {
        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewCategoryList = findViewById(R.id.view1);
        recyclerViewCategoryList.layoutManager = linearLayoutManager;

        val categoryList = ArrayList<CategoryDomain>()
        categoryList.add(CategoryDomain("Pizza"," cat_1"))
        categoryList.add(CategoryDomain("Burger"," cat_2"))
        categoryList.add(CategoryDomain("Hot-Dog"," cat_3"))
        categoryList.add(CategoryDomain("Drink"," cat_4"))
        categoryList.add(CategoryDomain("Donut"," cat_5"))

        adapter =  CategoryAdapter(categoryList)
        recyclerViewCategoryList.adapter = adapter

    }
}