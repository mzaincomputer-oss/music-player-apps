package com.example.musicplayer

import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.musicplayer.ui.theme.MusicPlayerTheme

class MainActivity : ComponentActivity() {
    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MusicPlayerTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    MusicPlayerUI()
                }
            }
        }
    }

    @Composable
    fun MusicPlayerUI() {
        var isPlaying by remember { mutableStateOf(false) }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text("Simple Music Player", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = {
                    if (!isPlaying) {
                        mediaPlayer = MediaPlayer.create(this@MainActivity, R.raw.sample_audio)
                        mediaPlayer?.start()
                        isPlaying = true
                        mediaPlayer?.setOnCompletionListener {
                            isPlaying = false
                        }
                    } else {
                        mediaPlayer?.pause()
                        isPlaying = false
                    }
                }
            ) {
                Text(if (isPlaying) "Pause" else "Play")
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.release()
        mediaPlayer = null
    }
}
