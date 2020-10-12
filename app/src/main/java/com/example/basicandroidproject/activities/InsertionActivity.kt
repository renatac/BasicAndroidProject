package com.example.basicandroidproject.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.basicandroidproject.R
import com.example.basicandroidproject.activities.MainActivity.Companion.RETURNED_INSERTED_NAME
import kotlinx.android.synthetic.main.activity_insertion.*

class InsertionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insertion)
    }

    fun clickAddBtn(view: View) {
        var txtLanguage = edtLanguage.text.toString()
        if(txtLanguage.isNotEmpty()){
            val data = Intent()
            data.putExtra(RETURNED_INSERTED_NAME, txtLanguage)
            setResult(Activity.RESULT_OK, data)
            finish()
        }
    }

}