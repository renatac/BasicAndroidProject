package com.example.basicandroidproject.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.basicandroidproject.R
import com.example.basicandroidproject.adapters.LanguageAdapter
import com.example.basicandroidproject.model.Language
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar_included.toolbarMain

class MainActivity : BaseActivity() {

    private lateinit var languageAdapter : LanguageAdapter

    companion object {
        const val EDITION_REQUEST_CODE = 1
        const val INSERTION_REQUEST_CODE = 2
        const val MODEL_OBJECT = "MODEL_OBJECT"
        const val RETURNED_NAME_OBJECT = "RETURNED_NAME_OBJECT"
        const val RETURNED_NUMBER_OBJECT = "RETURNED_NUMBER_OBJECT"
        const val RETURNED_INSERTED_NAME = "RETURNED_INSERTED_NAME"
        const val ELEMENT_0 = 0
        const val ELEMENT_1 = 1
        const val ELEMENT_2 = 2
        const val ELEMENT_3 = 3
        const val ELEMENT_4 = 4
        const val ELEMENT_5 = 5

        lateinit var listFake : MutableList<Language>

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupToolbar(toolbarMain, R.string.app_name)
        setupFloatingBtn()

        listFake = mutableListOf(
            Language(ELEMENT_0, resources.getString(R.string.label_kotlin)),
            Language(ELEMENT_1, resources.getString(R.string.label_java)),
            Language(ELEMENT_2, resources.getString(R.string.label_html)),
            Language(ELEMENT_3, resources.getString(R.string.label_swift)),
            Language(ELEMENT_4, resources.getString(R.string.label_typescript)),
            Language(ELEMENT_5, resources.getString(R.string.label_flutter))
        )
        languageAdapter = LanguageAdapter(listFake, this::onItemClickListener)
        recyclerView.adapter = languageAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

    }

    private fun setupFloatingBtn() {
        fab.setOnClickListener {
            val intent = Intent(this, InsertionActivity::class.java)
            startActivityForResult(intent, INSERTION_REQUEST_CODE)
        }
    }

    private fun onItemClickListener(language: Language) {

        Toast.makeText(this, "Model: Number=${language.number} Name:${language.name}",
            Toast.LENGTH_SHORT).show()

        val intent = Intent(this, EditionActivity::class.java)
        intent.putExtra(MODEL_OBJECT, language)
        startActivityForResult(intent, EDITION_REQUEST_CODE)
    }

    private fun changeListFake(language: Language){
        listFake[language.number!!] = language
        languageAdapter.notifyItemChanged(language.number!!)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK) {
            var language: Language
            if (requestCode == EDITION_REQUEST_CODE) {
                val returnedName = data?.getStringExtra(RETURNED_NAME_OBJECT)
                val returnedNumber = data?.getIntExtra(RETURNED_NUMBER_OBJECT, 1)
                language = Language(returnedNumber, returnedName)

                Toast.makeText(
                    this, "Model: Number=${language.number} Name:${language.name}",
                    Toast.LENGTH_SHORT
                ).show()

                changeListFake(language)
            }
            else if(requestCode == INSERTION_REQUEST_CODE){
                val returnedInsertedName = data?.getStringExtra(RETURNED_INSERTED_NAME)
                language = Language(listFake.size, returnedInsertedName)
                listFake.add(language)
                languageAdapter.notifyItemInserted(language.number!!)
            }
        }
    }
}
