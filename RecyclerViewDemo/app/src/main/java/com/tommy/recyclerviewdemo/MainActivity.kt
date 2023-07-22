package com.tommy.recyclerviewdemo

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    val fruitList = mutableListOf<Fruit>(
        Fruit("Táo"),
        Fruit("Mãng cầu"),
        Fruit("Cam"),
        Fruit("Chanh"),
        Fruit("Quýt"),
        Fruit("Đào"),
        Fruit("Ổi"),
        Fruit("Lê"),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rV = findViewById<RecyclerView>(R.id.MyRecyclerView)


//        rV.setBackgroundColor(Color.YELLOW)
        rV.layoutManager = LinearLayoutManager(this)

        rV.adapter = MyRecyclerViewAdapter(fruitList)
       /* { selectedItem: Fruit ->
            listItemClicked(selectedItem)
        }*/
    }

    private fun listItemClicked(fruit:Fruit){
        Toast.makeText(
            this, "${fruit.getName()}", Toast.LENGTH_SHORT
        ).show()
    }
}