package com.example.basicandroidproject.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.basicandroidproject.R
import com.example.basicandroidproject.model.Languagem
import kotlinx.android.synthetic.main.item_list.view.*

class LanguagemAdapter(val languagens: MutableList<Languagem>) : RecyclerView.Adapter<LanguagemAdapter.LanguagemViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LanguagemAdapter.LanguagemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return LanguagemViewHolder(view)
    }

    override fun getItemCount() = languagens.size

    override fun onBindViewHolder(holder: LanguagemAdapter.LanguagemViewHolder, position: Int) {
        holder.bind(languagens[position])
        holder.itemView.setOnClickListener {
            //TODO ir pra a EditionActivity com os dados do item específico da lista
            println("cliquei no item de imgRes=${holder.itemView.imgRecycler} e txt=${holder.itemView.txtName}")
        }
    }

    inner class LanguagemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(languagem: Languagem) {
            itemView.imgRecycler.setImageResource(
                when (languagem.imgRes) {
                    1 -> R.drawable.ic_kotlin
                    2 -> R.drawable.ic_java
                    3 -> R.drawable.ic_html
                    4 -> R.drawable.ic_swift
                    5 -> R.drawable.ic_typescript
                    else -> R.drawable.ic_flutter
                }
            )
            itemView.txtName.text = languagem.name
        }
    }
}