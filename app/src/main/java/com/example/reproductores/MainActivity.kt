package com.example.reproductores

import android.media.AudioManager
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.widget.ImageButton
import android.widget.MediaController
import android.widget.Toast
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import java.io.IOException

class MainActivity : AppCompatActivity() {

    //Variables reproductor por URL
    lateinit var iBtnPlayURL: ImageButton
    lateinit var iBtnPauseURL: ImageButton
    lateinit var mediaPlayerURL: MediaPlayer

    //Variables reproductor por Almacenamiento
    lateinit var btnPlayAlmacenamiento: ImageButton
    lateinit var btnPauseAlmacenamiento: ImageButton
    lateinit var btnStopAlmacenamiento: ImageButton
    lateinit var mediaplayerAlmacenamiento: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Primer Reproductor de audio (Via URL)
        iBtnPlayURL = findViewById(R.id.ibtnPlayURL)
        iBtnPauseURL = findViewById(R.id.ibtnPauseURL)
        mediaPlayerURL = MediaPlayer()

        iBtnPlayURL.setOnClickListener {
            var audioUrl = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3"
            mediaPlayerURL.setAudioStreamType(AudioManager.STREAM_MUSIC)
            try {
                mediaPlayerURL.setDataSource(audioUrl)
                mediaPlayerURL.prepare()
                mediaPlayerURL.start()
            } catch (e: Exception) {
                e.printStackTrace()
            }
            Toast.makeText(applicationContext, "Audio iniciado", Toast.LENGTH_SHORT).show()
        }

        iBtnPauseURL.setOnClickListener {

            if (mediaPlayerURL.isPlaying) {
                mediaPlayerURL.stop()
                mediaPlayerURL.reset()
                mediaPlayerURL.release()

                Toast.makeText(applicationContext, "Audio pausado", Toast.LENGTH_SHORT)
                    .show()

            } else {
                Toast.makeText(applicationContext, "Ha ocurrido un error con el audio", Toast.LENGTH_SHORT).show()
            }

        }


        //Segundo reproductor de audio (Via almacenamiento)
        mediaplayerAlmacenamiento = MediaPlayer.create(this, R.raw.audio2);


        btnPlayAlmacenamiento= findViewById(R.id.ibtnPlayAlmacenamiento);
        btnStopAlmacenamiento= findViewById(R.id.ibtnStopAlmacenamiento);
        btnPauseAlmacenamiento= findViewById(R.id.ibtnPauseAlmacenamiento);


        btnPlayAlmacenamiento.setOnClickListener {
            mediaplayerAlmacenamiento.start()
            Toast.makeText(applicationContext, "Audio inciado", Toast.LENGTH_SHORT).show()}
        btnStopAlmacenamiento.setOnClickListener {
            try {
                mediaplayerAlmacenamiento!!.stop()
                mediaplayerAlmacenamiento!!.prepare()
                mediaplayerAlmacenamiento!!.seekTo(0)
                Toast.makeText(applicationContext, "Audio detenido", Toast.LENGTH_SHORT)
            } catch (e: IOException) {
                Toast.makeText(applicationContext, "Ha ocurrido un error con el audio", Toast.LENGTH_SHORT)
             e.printStackTrace()
        } }
        btnPauseAlmacenamiento.setOnClickListener { mediaplayerAlmacenamiento.pause()
            Toast.makeText(applicationContext, "Audio pausado", Toast.LENGTH_SHORT)}

        //Reproductor video
        val videoView = findViewById<VideoView>(R.id.videoView)
        videoView.setVideoURI(Uri.parse("android.resource://$packageName/raw/monkiflip"))
        val mediaController = MediaController(this)
        mediaController.setMediaPlayer(videoView)
        videoView.setMediaController(mediaController)
        videoView.requestFocus()
        videoView.start()
    }
}