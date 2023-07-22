package com.example.servicedemo

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log


class MyBackgroundService: Service() {

    init {
        Log.i("MyTag","Service has been created")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.i("MyTag","Service Started")
        val name=intent?.getStringExtra(NAME)
        val marks=intent?.getIntExtra(MARKS,0)
        Log.i(TAG,"name is $name, marks : $marks")
        return START_STICKY
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        Log.i("MyTag", "Destroying....")
        super.onDestroy()
    }

    companion object{
        const val TAG="MyTag"
        const val NAME="NAME"
        const val MARKS="TOTAL MARKS"
    }
}