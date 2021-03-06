package com.example.basicandroidproject.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.widget.Toolbar
import com.example.basicandroidproject.R
import com.example.basicandroidproject.activities.MainActivity.Companion.RETURNED_BEFORE_NAME
import com.example.basicandroidproject.activities.MainActivity.Companion.RETURNED_NAME_OBJECT
import com.example.basicandroidproject.activities.MainActivity.Companion.RETURNED_NUMBER_OBJECT
import com.example.basicandroidproject.model.Language
import kotlinx.android.synthetic.main.activity_edition.*

class EditionActivity : BaseActivity(), AdapterView.OnItemSelectedListener {

    private lateinit var nameSelected: String
    private var recoveredNumber: Int? = 0
    private var recoveredName: String? = null
    private var beforeName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edition)

        setupToolbar(editionToolbar as Toolbar, R.string.app_name, true)

        recoverVariables()
        fillSpinners()
        setImage(recoveredNumber)
        setselectedElement()
    }

    private fun setImage(index: Int?) {
        langImg.setImageResource(
            when (MainActivity.listFake[index!!].name) {
                resources.getString(R.string.label_kotlin) -> R.drawable.ic_kotlin
                resources.getString(R.string.label_java) -> R.drawable.ic_java
                resources.getString(R.string.label_html) -> R.drawable.ic_html
                resources.getString(R.string.label_swift) -> R.drawable.ic_swift
                resources.getString(R.string.label_typescript) -> R.drawable.ic_typescript
                resources.getString(R.string.label_flutter) -> R.drawable.ic_flutter
                else -> R.drawable.ic_languagens
            }
        )
    }

    private fun setselectedElement() {
        MainActivity.listFake.map { it.name }.forEachIndexed { index, nameItem ->
            if (nameItem.equals(recoveredName)) {
                spinnerName.setSelection(index)
                nameSelected = recoveredName as String
            }
        }
    }

    private fun recoverVariables() {
        val language = intent.getParcelableExtra<Language>(MainActivity.MODEL_OBJECT)
        recoveredNumber = language?.number
        beforeName = language?.name
        recoveredName = language?.name
    }

    private fun fillSpinners() {
        ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            MainActivity.listFake.map { it.name }
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerName.adapter = adapter
        }
        spinnerName.onItemSelectedListener = this
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        //
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        nameSelected = parent?.getItemAtPosition(position).toString()
        setImage(position)
    }

    fun clickBtnFinishEdition(view: View) {
        val data = Intent()
        data.putExtra(RETURNED_NAME_OBJECT, nameSelected)
        data.putExtra(RETURNED_BEFORE_NAME, beforeName)
        data.putExtra(RETURNED_NUMBER_OBJECT, recoveredNumber)
        setResult(Activity.RESULT_OK, data)
        finish()
    }

}