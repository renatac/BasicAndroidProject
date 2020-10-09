package com.example.basicandroidproject.activities

import android.app.Activity
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

    private lateinit var listFake : MutableList<Language>

    private lateinit var languageAdapter : LanguageAdapter

    companion object {
        const val REQUEST_CODE = 1
        const val MODEL_OBJECT = "MODEL_OBJECT"
        const val RETURNED_NAME_OBJECT = "RETURNED_NAME_OBJECT"
        const val RETURNED_IMG_RES_OBJECT = "RETURNED_IMG_RES_OBJECT"
        const val ELEMENT_0 = 0
        const val ELEMENT_1 = 1
        const val ELEMENT_2 = 2
        const val ELEMENT_3 = 3
        const val ELEMENT_4 = 4
        const val ELEMENT_5 = 5
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupToolbar(toolbarMain, R.string.app_name)

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

    private fun onItemClickListener(language: Language) {

        Toast.makeText(this, "Model: ImgRes=${language.imgRes} Name:${language.name}",
            Toast.LENGTH_SHORT).show()

        val intent = Intent(this, EditionActivity::class.java)
        intent.putExtra(MODEL_OBJECT, language)
        startActivityForResult(intent, REQUEST_CODE)
    }

    private fun changeListFake(position: Int, language: Language){
        listFake[position] = language
        languageAdapter.notifyItemChanged(position)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_CODE) {
                val returnedName = data?.getStringExtra(RETURNED_NAME_OBJECT)
                val returnedImgRes = data?.getIntExtra(RETURNED_IMG_RES_OBJECT,1)
                val language = Language(returnedImgRes, returnedName)
                when(returnedImgRes){
                    ELEMENT_1 -> changeListFake(ELEMENT_0, language)
                    ELEMENT_0 -> changeListFake(ELEMENT_1, language)
                    ELEMENT_2 -> changeListFake(ELEMENT_2, language)
                    ELEMENT_3 -> changeListFake(ELEMENT_3, language)
                    ELEMENT_4 -> changeListFake(ELEMENT_4, language)
                    ELEMENT_5 -> changeListFake(ELEMENT_5, language)
                }
            }
        }
    }

}
