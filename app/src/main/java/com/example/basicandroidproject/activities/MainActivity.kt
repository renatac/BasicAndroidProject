package com.example.basicandroidproject.activities

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.basicandroidproject.R
import com.example.basicandroidproject.adapters.LanguagemAdapter
import com.example.basicandroidproject.model.Languagem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar_included.toolbarMain

class MainActivity : BaseActivity() {

    private lateinit var languagemAdapter: LanguagemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupToolbar(toolbarMain, R.string.app_name)

        languagemAdapter = LanguagemAdapter(fillLanguagens())
        recyclerView.adapter = languagemAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

    }

    private fun fillLanguagens() = mutableListOf<Languagem>(
            Languagem(1, resources.getString(R.string.label_kotlin)),
            Languagem(2, resources.getString(R.string.label_java)),
            Languagem(3, resources.getString(R.string.label_html)),
            Languagem(4, resources.getString(R.string.label_swift)),
            Languagem(5, resources.getString(R.string.label_typescript)),
            Languagem(6, resources.getString(R.string.label_flutter)))

}
