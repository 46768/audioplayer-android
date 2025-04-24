package com.yrth.audioplayer

import android.content.ContentResolver
import android.content.ContentUris
import android.icu.util.TimeUnit
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.room.util.query
import com.yrth.audioplayer.ui.theme.AudioPlayerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val playlistManager: AudioPlayer = AudioPlayer()
        val audioMediaManager: AudioFileManager = AudioFileManager()
        val videoNameList: MutableList<String> = mutableListOf<String>()

        enableEdgeToEdge()
        setContent {
            AudioPlayerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column {
                        Row(Modifier.padding(innerPadding)) {
                            Button(onClick = { playlistManager.previousTrack() }) {
                                Text(text = "Previous")
                            }
                            Button(onClick = { playlistManager.togglePlay() }) {
                                Text(text = "Play/Stop")
                            }
                            Button(onClick = { playlistManager.nextTrack() }) {
                                Text(text = "Next")
                            }
                            Button(onClick = { audioMediaManager.loadDirectory() }) {
                                Text(text = "Open")
                            }
                        }
                        AudioList(audioPathList = listOf("A", "B", "C"))
                    }
                }
            }
        }
    }
}

@Composable
fun AudioList(
    audioPathList: List<String>,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier) {
        itemsIndexed(audioPathList) { index, item -> Text("$index. $item")}
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AudioPlayerTheme {
        Greeting("Android")
    }
}