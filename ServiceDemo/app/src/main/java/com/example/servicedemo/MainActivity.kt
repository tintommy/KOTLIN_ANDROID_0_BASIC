package com.example.servicedemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.servicedemo.MyBackgroundService.Companion.MARKS
import com.example.servicedemo.MyBackgroundService.Companion.NAME
import com.example.servicedemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val serviceIntent = Intent(applicationContext, MyBackgroundService::class.java)
        serviceIntent.putExtra(NAME,"ALEX")
        serviceIntent.putExtra(MARKS,78)
        binding.apply {
            btnStart.setOnClickListener {
                Log.i("MyTag", "Starting service")
                startService(serviceIntent)
            }

            btnStop.setOnClickListener {
                Log.i("MyTag", "Stopping service")
                stopService(serviceIntent)
            }
        }


    }
}