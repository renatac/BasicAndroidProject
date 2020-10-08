package com.example.basicandroidproject.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.basicandroidproject.R
import com.example.basicandroidproject.adapters.LanguageAdapter
import com.example.basicandroidproject.model.Language
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar_included.toolbarMain

class MainActivity : BaseActivity() {

    private lateinit var languageAdapter: LanguageAdapter

    companion object{
        const val REQUEST_CODE = 1
        const val MODEL_OBJECT = "MODEL_OBJECT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupToolbar(toolbarMain, R.string.app_name)

        languageAdapter = LanguageAdapter(fillLanguagens(), this::onItemClickListener)
        recyclerView.adapter = languageAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

    }

    private fun fillLanguagens() = mutableListOf(
            Language(1, resources.getString(R.string.label_kotlin)),
            Language(2,resources.getString(R.string.label_java)),
            Language(3, resources.getString(R.string.label_html)),
            Language(4, resources.getString(R.string.label_swift)),
            Language(5, resources.getString(R.string.label_typescript)),
            Language(6, resources.getString(R.string.label_flutter)))


    private fun onItemClickListener(language: Language) {

        Toast.makeText(this, "Model: ImgRes=${language.imgRes} Name:${language.name}",
            Toast.LENGTH_SHORT).show()

        val intent = Intent(this, EditionActivity::class.java)
        intent.putExtra(MODEL_OBJECT, language)
        startActivityForResult(intent, REQUEST_CODE)

    }

}
