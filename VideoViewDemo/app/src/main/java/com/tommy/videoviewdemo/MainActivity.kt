package com.tommy.videoviewdemo

import android.net.Uri
import android.net.Uri.parse
import android.os.Bundle
import android.widget.MediaController
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val videoView =findViewById<VideoView>(R.id.videoView3)
        val mediaController=MediaController(this)
        mediaController.setAnchorView((videoView))
        val uri: Uri = parse("android.resource://"+packageName+"/"+R.raw.video)
        videoView.setMediaController(mediaController)
        videoView.setVideoURI(uri)
        videoView.requestFocus()
        videoView.start()
       videoView.setOnPreparedListener { mediaPlayer -> mediaPlayer.isLooping = true }

    }
}