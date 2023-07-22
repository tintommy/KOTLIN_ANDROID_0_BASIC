package com.tommy.recyclerviewdemo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ExpandableListView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class MyRecyclerViewAdapter(private val dataset: MutableList<Fruit>/*, private val clickListener:(Fruit)->Unit*/) :
    RecyclerView.Adapter<MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val name = dataset[position].getName()
        holder.myTextView.text = name
        holder.click(dataset[position]/*,clickListener*/)

    }

    override fun getItemCount(): Int {
        return dataset.size
    }

}

class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    val myTextView = view.findViewById<TextView>(R.id.textView)
    val btn = view.findViewById<Button>(R.id.button1)

    fun click(fruit: Fruit/*,clickListener: (Fruit) -> Unit*/) {
        view.setOnClickListener {
//            clickListener(fruit)
            Toast.makeText(
                view.context, "${fruit.getName()}", Toast.LENGTH_SHORT
            ).show()
        }
        btn.setOnClickListener {
            myTextView.text = "DONE"
        }
    }
}