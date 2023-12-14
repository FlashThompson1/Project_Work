package com.example.foodapp.Activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.foodapp.R



class LogInActivity : AppCompatActivity() {
    private  lateinit var edname: EditText
    private  lateinit var edemail: EditText
    private  lateinit var edphone: EditText
    private  lateinit var loginbtn: Button


    private  lateinit var  usersdatabase: SQLiteHelper


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.registration_activity)

        initView();

        usersdatabase = SQLiteHelper(this)
        loginbtn.setOnClickListener { addUser() }
    }

    private fun getUsers(){
        val userList = usersdatabase.getAllUsers()
        Log.e("pppp","${userList.size}")

    }

    private  fun addUser(){
        val name = edname.text.toString()
        val email = edemail.text.toString()
        val phone = edphone.text.toString()

        if(name.isEmpty() || email.isEmpty() || phone.isEmpty()){
            Toast.makeText(this,"Please enter required field", Toast.LENGTH_SHORT).show()
        }
        else{
            val usr = UserModel(name = name, email = email, phone = phone)
            val userstatus = usersdatabase.insertUser(usr)
            if (userstatus > -1){
                Toast.makeText(this,"Login Successful", Toast.LENGTH_SHORT).show()
                clearEditText()
                var intent = Intent(this, SecondActivity::class.java)
                intent.putExtra("name", name)

                startActivity(intent)
            }
            else{
                Toast.makeText(this,"Information is not saved",Toast.LENGTH_SHORT).show()
            }
        }

    }



    private fun clearEditText() {
        edname.setText("")
        edemail.setText("")
        edphone.setText("")
        edphone.requestFocus()
    }



    private fun initView() {
        edname = findViewById(R.id.username)
        edemail = findViewById(R.id.email)
        edphone = findViewById(R.id.phone_number)
        loginbtn = findViewById(R.id.loginbtn)

    }


}