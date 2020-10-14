package com.example.basicandroidproject.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
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

        const val TAG = "TAG"

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
            Log.d(TAG, "onCreate 1")
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
            Log.d(TAG, "onCreate 2")
            listFake = savedInstanceState.getParcelableArrayList<Language>(SAVED_LIST_FAKE) as MutableList<Language>
        }

        languageAdapter = LanguageAdapter(listFake, this::onItemClickListener)
        recyclerView.adapter = languageAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val helper =
            androidx.recyclerview.widget.ItemTouchHelper(
                //Direçoes que irei excutar esses eventos
                ItemTouchHandler(androidx.recyclerview.widget.ItemTouchHelper.UP
                        or androidx.recyclerview.widget.ItemTouchHelper.DOWN,
                    androidx.recyclerview.widget.ItemTouchHelper.LEFT)) //e o swipe só pra esquerda
        helper.attachToRecyclerView(recyclerView)

        languageAdapter.onItemLongClick ={position->
            //enableActionMode(position)
        }

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

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        listFake as ArrayList<Language>
        outState.putParcelableArrayList(SAVED_LIST_FAKE, java.util.ArrayList<Language>(listFake))
        Log.d(TAG, "onSaveInstanceState")
        Log.d(TAG, "listFake = ${listFake}")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        listFake = savedInstanceState.getParcelableArrayList<Language>(SAVED_LIST_FAKE) as MutableList<Language>
        Log.d(TAG, "onRestoreInstanceState")
        Log.d(TAG, "listFake = ${listFake}")

    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")

    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart")
        languageAdapter.notifyDataSetChanged()

    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart")

    }

    //reordenacao dos nossos itens de célula
    inner class ItemTouchHandler(dragDirs: Int, swipeDirs: Int) :
        androidx.recyclerview.widget.ItemTouchHelper.SimpleCallback(dragDirs, swipeDirs) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
//            //usarei para reordenar
//            val from = viewHolder.adapterPosition
//            val to = target.adapterPosition
//
//            Collections.swap(languageAdapter.languagens, from, to)
//            languageAdapter.notifyItemMoved(from, to)
            return true // pra dizer que a lista foi alterada
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            //usarei para excluir dados
            //removo os itens e notifico ao adapter que isso ocorreu
            languageAdapter.languagens.removeAt(viewHolder.adapterPosition)
            languageAdapter.notifyItemRemoved(viewHolder.adapterPosition)
        }
    }



}
