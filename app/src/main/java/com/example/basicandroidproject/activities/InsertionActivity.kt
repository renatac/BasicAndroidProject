package com.example.basicandroidproject.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import com.example.basicandroidproject.R
import com.example.basicandroidproject.activities.MainActivity.Companion.RETURNED_INSERTED_NAME
import com.example.basicandroidproject.activities.MainActivity.Companion.listFake
import kotlinx.android.synthetic.main.activity_insertion.*

class InsertionActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insertion)
        setupToolbar(insertionToolbar as Toolbar, R.string.app_name, true)
    }

    fun clickAddBtn(view: View) {
        var txtLanguage = edtLanguage.text.toString()
        if(txtLanguage.isNotEmpty()){

            listFake.forEach {
                if(it.name.equals(txtLanguage)){

                }

            }

            val data = Intent()
            data.putExtra(RETURNED_INSERTED_NAME, txtLanguage)
            setResult(Activity.RESULT_OK, data)
            finish()
        }
    }

}