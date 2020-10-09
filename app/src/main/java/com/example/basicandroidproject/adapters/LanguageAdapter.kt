package com.example.basicandroidproject.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.basicandroidproject.R
import com.example.basicandroidproject.model.Language
import kotlinx.android.synthetic.main.item_list.view.*

class LanguageAdapter(val languagens: MutableList<Language>,
                      private val callback: (Language) -> Unit)
    : RecyclerView.Adapter<LanguageAdapter.LanguageViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LanguageAdapter.LanguageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        val vHelp = LanguageViewHolder(view)
        vHelp.itemView.setOnClickListener {
            val lang = languagens[vHelp.adapterPosition]
            callback(lang)
        }
        return vHelp
    }

    override fun getItemCount() = languagens.size

    override fun onBindViewHolder(holder: LanguageAdapter.LanguageViewHolder, position: Int) {
        holder.bind(languagens[position], position)
    }

    inner class LanguageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(language: Language, position: Int) {

            itemView.txtNumber.text = (position+1).toString()
            itemView.imgRecycler.setImageResource(
                when (language.imgRes) {
                    1 -> R.drawable.ic_kotlin
                    2 -> R.drawable.ic_java
                    3 -> R.drawable.ic_html
                    4 -> R.drawable.ic_swift
                    5 -> R.drawable.ic_typescript
                    else -> R.drawable.ic_flutter
                }
            )
            itemView.txtName.text = language.name
        }
    }
}