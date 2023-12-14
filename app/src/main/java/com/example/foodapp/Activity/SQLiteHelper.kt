package com.example.foodapp.Activity

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLiteHelper(context : Context) : SQLiteOpenHelper(context , DATABASE_NAME,null,DATABASE_VERSION) {

    companion object{
        private const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "users.db"
        private  const val TBL_USER = "tbl_user"
        private const val  ID = "id"
        const val  NAME = "name"
        private  const val  EMAIL = "email"
        private  const val  PHONE = "phone"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTBLUSER = ("CREATE TABLE " + TBL_USER + "("
                + ID + " INTEGER PRIMARY KEY,"
                + NAME + " TEXT,"
                + EMAIL + " TEXT,"
                + PHONE + " TEXT" + ")")
        db?.execSQL(createTBLUSER)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TBL_USER")
        onCreate(db)
    }

    fun  insertUser(user: UserModel): Long {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(ID,user.id)
        contentValues.put(NAME,user.name)
        contentValues.put(EMAIL,user.email)
        contentValues.put(PHONE,user.phone)

        val  success = db.insert(TBL_USER,null,contentValues)
        db.close()
        return success
    }
    @SuppressLint("Range")
    fun getAllUsers(): ArrayList<UserModel>{
        val userlist : ArrayList<UserModel> = ArrayList()
        val selectQuery = "SELECT * FROM $TBL_USER"
        val db = this.readableDatabase

        val cursor: Cursor?

        try {
            cursor = db.rawQuery(selectQuery,null)

        }
        catch (e:Exception){
            e.printStackTrace()
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var id: Int
        var name: String
        var email : String
        var phone : String

        if(cursor.moveToFirst()){
            do {
                id = cursor.getInt(cursor.getColumnIndex("id"))
                name = cursor.getString(cursor.getColumnIndex("name"))
                email = cursor.getString(cursor.getColumnIndex("email"))
                phone = cursor.getString(cursor.getColumnIndex("phone"))

                val usr = UserModel(id = id, name = name, email = email, phone = phone)
                userlist.add(usr)
            }while (cursor.moveToNext())
            }
        return userlist
        }

    }
