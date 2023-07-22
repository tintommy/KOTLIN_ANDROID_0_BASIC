package com.trongtin.spdemo

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var sf: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sf = getSharedPreferences("my_sf", MODE_PRIVATE)
        editor = sf.edit()
    }

    override fun onPause() {
        super.onPause()
        val name = edName.text.toString()
        val age = edAge.text.toString().toInt()
        editor.apply {
            putString("sf_name", name)
            putInt("sf_age", age)
            commit()

        }
    }

    override fun onResume() {
        super.onResume()
        val name = sf.getString("sf_name", "")
        val age = sf.getInt("sf_age", 0)
        edName.setText(name)
        if (age != 0) {
            edAge.setText(age.toString())
        }

    }
}