package com.example.foodapp.Activity

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.foodapp.R



class PurchaseAcitivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {



        super.onCreate(savedInstanceState)
        setContentView(R.layout.purchase_activity)


        var paymebtnclick: ImageButton = findViewById(R.id.paymebtn)
        var clickbtn: ImageButton = findViewById(R.id.clickbtn)
        var cashbtn : ImageButton = findViewById(R.id.cashbtn)

        paymebtnclick.setOnClickListener {

            val targetAppPackageName = "uz.dida.payme"


            if (isAppInstalled(targetAppPackageName)) {

                openTargetApp(targetAppPackageName)
            } else {

                openLink("https://play.google.com/store/apps/details?id=uz.dida.payme")
            }
        }

        // CLICK

        clickbtn.setOnClickListener {

            val targetAppPackageName = "air.com.ssdsoftwaresolutions.clickuz"


            if (isAppInstalled(targetAppPackageName)) {

                openTargetApp(targetAppPackageName)
            } else {

                openLink("https://play.google.com/store/apps/details?id=air.com.ssdsoftwaresolutions.clickuz")
            }
        }

        cashbtn.setOnClickListener {
            var intent = Intent(this,SuccessActivity::class.java)
            startActivity(intent)
        }


    }

    private fun isAppInstalled(packageName: String): Boolean {
        val pm: PackageManager = packageManager
        return try {
            pm.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES)
            true
        } catch (e: PackageManager.NameNotFoundException) {
            false
        }
    }

    private fun openTargetApp(packageName: String) {
        val intent = packageManager.getLaunchIntentForPackage(packageName)
        if (intent != null) {
            startActivity(intent)
        }
    }

    private fun openLink(link: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
        startActivity(intent)
    }
}