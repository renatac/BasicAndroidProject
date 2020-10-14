package com.example.basicandroidproject.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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

        const val SAVED_LIST_FAKE = "SAVED_LIST_FAKE"

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

        if(savedInstanceState == null) {
            listFake = mutableListOf(
                Language(ELEMENT_0, resources.getString(R.string.label_kotlin)),
                Language(ELEMENT_1, resources.getString(R.string.label_java)),
                Language(ELEMENT_2, resources.getString(R.string.label_html)),
                Language(ELEMENT_3, resources.getString(R.string.label_swift)),
                Language(ELEMENT_4, resources.getString(R.string.label_typescript)),
                Language(ELEMENT_5, resources.getString(R.string.label_flutter))
            )
        }
        else{
            listFake = savedInstanceState.getParcelableArrayList<Language>(SAVED_LIST_FAKE) as MutableList<Language>
        }

        languageAdapter = LanguageAdapter(listFake, this::onItemClickListener)
        recyclerView.adapter = languageAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val decorator = DividerItemDecoration(applicationContext, LinearLayoutManager.VERTICAL)
        decorator.setDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.divider)!!)
        recyclerView.addItemDecoration(decorator)

        val helper =
            androidx.recyclerview.widget.ItemTouchHelper(
                // Directions I will run these events
                ItemTouchHandler(androidx.recyclerview.widget.ItemTouchHelper.UP
                        or androidx.recyclerview.widget.ItemTouchHelper.DOWN,
                    androidx.recyclerview.widget.ItemTouchHelper.LEFT)) // and the swipe just to the left
        helper.attachToRecyclerView(recyclerView)

    }

    private fun setupFloatingBtn() {
        fab.setOnClickListener {
            val intent = Intent(this, InsertionActivity::class.java)
            startActivityForResult(intent, INSERTION_REQUEST_CODE)
        }
    }

    private fun onItemClickListener(language: Language) {
        Toast.makeText(this, language.number.toString(), Toast.LENGTH_LONG).show()
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

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        listFake as ArrayList<Language>
        outState.putParcelableArrayList(SAVED_LIST_FAKE, java.util.ArrayList<Language>(listFake))
     }

    override fun onStart() {
        super.onStart()
        languageAdapter.notifyDataSetChanged()
    }

    // reordering our cell items
    inner class ItemTouchHandler(dragDirs: Int, swipeDirs: Int) :
        androidx.recyclerview.widget.ItemTouchHelper.SimpleCallback(dragDirs, swipeDirs) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            languageAdapter.languagens.removeAt(viewHolder.adapterPosition)
            languageAdapter.notifyItemRemoved(viewHolder.adapterPosition)
            languageAdapter.notifyDataSetChanged()
            var index = 0
            listFake.forEach {
                it.number = index
                index++
            }

        }
    }

}
