package com.tommy.clapapp

import android.content.res.ColorStateList
import android.graphics.Color
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import androidx.core.os.postDelayed
import androidx.core.view.isVisible
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var seekBar: SeekBar
    private var mediaPlayer: MediaPlayer? =null
    private lateinit var runnable: Runnable
    private lateinit var handler: Handler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        seekBar=findViewById(R.id.sbClap)
        handler= Handler(Looper.getMainLooper())

        val play=findViewById<FloatingActionButton>(R.id.fabPlay)
        val pause=findViewById<FloatingActionButton>(R.id.fabPause)
        val stop=findViewById<FloatingActionButton>(R.id.fabStop)
        stop.isEnabled=false
        stop.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#515168")));
        play.setOnClickListener {
            if(mediaPlayer==null){
                mediaPlayer=MediaPlayer.create(this,R.raw.clap)
                initSeekBar(play,pause,stop)
            }
            mediaPlayer?.start()
            play.isVisible=false
            pause.isVisible=true
            stop.isEnabled=true
            stop.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#E60000")));


        }

        pause.setOnClickListener {
            if (mediaPlayer!=null) {
                play.isVisible = true
                pause.isVisible = false
                mediaPlayer?.pause()
                stop.isEnabled=false
                stop.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#515168")));
            }
        }

        stop.setOnClickListener {

            if (mediaPlayer!=null){
                play.isVisible=true
                pause.isVisible=false
                mediaPlayer?.stop()
                mediaPlayer?.reset()
                mediaPlayer?.release()
                mediaPlayer=null
                handler.removeCallbacks(runnable)
                seekBar.progress=0
                stop.isEnabled=false
                stop.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#515168")));
            }

        }


    }

    private fun initSeekBar(play:FloatingActionButton,pause:FloatingActionButton,stop:FloatingActionButton){
        seekBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress:Int, fromUser: Boolean) {
               if(fromUser ) {
                   mediaPlayer?.seekTo(progress)
               }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })

        val tvPlayed=findViewById<TextView>(R.id.tvPlayed)
        val tvDue=findViewById<TextView>(R.id.tvDue)
        seekBar.max=mediaPlayer!!.duration
        runnable= Runnable {
            seekBar.progress=mediaPlayer!!.currentPosition
            val playedTime=mediaPlayer!!.currentPosition/1000
            tvPlayed.text="$playedTime s"
            val duration=mediaPlayer!!.duration/1000
            val dueTime=duration-playedTime
            tvDue.text="$dueTime s"
            handler.postDelayed(runnable,1000)

            if(seekBar.progress==seekBar.max){
                seekBar.progress=0
                play.isVisible=true
                pause.isVisible=false
                stop.isEnabled=false
                stop.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#515168")));
            }
        }
        handler.postDelayed(runnable,1000)
    }

}