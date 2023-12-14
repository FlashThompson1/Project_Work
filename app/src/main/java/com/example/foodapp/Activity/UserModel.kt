package com.example.foodapp.Activity

import kotlin.random.Random


data class UserModel(
    val  id: Int = getAutoId(),
    var name: String = "",
    var email: String = "",
    var phone: String = ""

){
    companion object {
        fun getAutoId(): Int {
            val random = Random(0)
            return random.nextInt(100)
        }
    }
}