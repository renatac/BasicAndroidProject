package com.example.basicandroidproject.activities

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.widget.Toolbar
import com.example.basicandroidproject.R
import com.example.basicandroidproject.model.Language
import kotlinx.android.synthetic.main.activity_edition.*

class EditionActivity : BaseActivity(), AdapterView.OnItemSelectedListener {

    private var imgRes : Int? = 0
    private var name : String? = null
    private val nameList = arrayOf<String>("Kotlin", "Java","Html", "Swift", "TypeScript","Flutter")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edition)

        setupToolbar(editionToolbar as Toolbar, R.string.app_name)

        recoverVariables()
        fillSpinners()
        setSelectionSpinners()
    }

    private fun setSelectionSpinners() {
        nameList.forEachIndexed { index, nameItem ->
            if(nameItem.equals(name)){
                spinnerName.setSelection(index);
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

        val intList = arrayOf<Int>(
            R.drawable.ic_kotlin,
            R.drawable.ic_java,
            R.drawable.ic_html,
            R.drawable.ic_swift,
            R.drawable.ic_typescript,
            R.drawable.ic_flutter)

        ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            intList
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinnerImg.adapter = adapter
        }
        spinnerImg.onItemSelectedListener = this

    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        parent?.getItemAtPosition(position)
    }
}