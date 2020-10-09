package com.example.basicandroidproject.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.widget.Toolbar
import com.example.basicandroidproject.R
import com.example.basicandroidproject.activities.MainActivity.Companion.RETURNED_IMG_RES_OBJECT
import com.example.basicandroidproject.activities.MainActivity.Companion.RETURNED_NAME_OBJECT
import com.example.basicandroidproject.model.Language
import kotlinx.android.synthetic.main.activity_edition.*


class EditionActivity : BaseActivity(), AdapterView.OnItemSelectedListener {

    private lateinit var nameSelected: String
    private var positionHelp: Int = 0
    private var imgRes : Int? = 0
    private var name : String? = null
    private val nameList = arrayOf<String>("Kotlin", "Java","Html", "Swift", "TypeScript","Flutter")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edition)

        setupToolbar(editionToolbar as Toolbar, R.string.app_name)

        recoverVariables()
        fillSpinners()

        setImage(imgRes)
        setElements()
    }

    private fun setImage(imgRes: Int?) {
        langImg.setImageResource(
            when (imgRes) {
                1 -> R.drawable.ic_kotlin
                2 -> R.drawable.ic_java
                3 -> R.drawable.ic_html
                4 -> R.drawable.ic_swift
                5 -> R.drawable.ic_typescript
                else -> R.drawable.ic_flutter
            })
    }

    private fun setElements() {
        nameList.forEachIndexed { index, nameItem ->
            if(nameItem.equals(name)){
                spinnerName.setSelection(index);
                nameSelected = name as String
            }
        }
    }

    private fun recoverVariables() {
        val language = intent.getParcelableExtra<Language>(MainActivity.MODEL_OBJECT)
        imgRes = language?.imgRes
        name = language?.name
    }

    private fun fillSpinners() {

        ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            nameList
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinnerName.adapter = adapter
        }
        spinnerName.onItemSelectedListener = this
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        nameSelected = parent?.getItemAtPosition(position).toString()
        positionHelp = position +1
        setImage(position+1)
    }

    fun clickBtnEdition(view: View) {
        val data = Intent()
        data.putExtra(RETURNED_IMG_RES_OBJECT, positionHelp)
        data.putExtra(RETURNED_NAME_OBJECT, nameSelected)
        setResult(Activity.RESULT_OK, data)
        finish()
    }

}