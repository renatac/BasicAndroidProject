package com.example.basicandroidproject.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.basicandroidproject.R
import com.example.basicandroidproject.model.Language
import kotlinx.android.synthetic.main.item_list.view.*

class LanguageAdapter(
    val languagens: MutableList<Language>,
    private val callback: (Language) -> Unit
) : RecyclerView.Adapter<LanguageAdapter.LanguageViewHolder>() {

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

            itemView.txtNumber.text = (position + 1).toString()
            itemView.txtName.text = language.name
            itemView.imgRecycler.setImageResource(
                when (language.name) {
                    "Kotlin" -> R.drawable.ic_kotlin
                    "Java" -> R.drawable.ic_java
                    "Html" -> R.drawable.ic_html
                    "Swift" -> R.drawable.ic_swift
                    "TypeScript" -> R.drawable.ic_typescript
                    "Flutter" -> R.drawable.ic_flutter
                    else-> R.drawable.ic_languagens
                }
            )
        }
    }
}