package com.example.basicandroidproject.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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

        edtLanguage.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                txtFeedback.text = ""
            }
        })

    }

    fun clickAddBtn(view: View) {
        var txtLanguage = edtLanguage.text.toString()
        if(isValidLanguage(txtLanguage)){
            val data = Intent()
            data.putExtra(RETURNED_INSERTED_NAME, txtLanguage)
            setResult(Activity.RESULT_OK, data)
            finish()
        }
    }

    private fun isValidLanguage(txtLanguage: String): Boolean {
        if (txtLanguage.isNotEmpty() && txtLanguage.isNotBlank()) {
            for (list in listFake) {
                if (list.name.equals(txtLanguage)) {
                    txtFeedback.text = getString(R.string.feedback_language_already_exist)
                    return false
                }
            }
        } else if (txtLanguage.isEmpty() || txtLanguage.isBlank()) {
            txtFeedback.text = getString(R.string.feedback_empty_language)
            return false
        }
            return true
    }

}
