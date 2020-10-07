package com.example.basicandroidproject

import android.os.Bundle
import kotlinx.android.synthetic.main.toolbar_included.*

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupToolbar(toolbarMain, R.string.app_name)
    }
}