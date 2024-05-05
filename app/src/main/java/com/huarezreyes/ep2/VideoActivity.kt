package com.huarezreyes.ep2

import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.VideoView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.huarezreyes.ep2.ui.theme.EP2Theme

class VideoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EP2Theme {

                val brush = Brush.linearGradient(listOf(Color.Black, Color.Black))

                Column (
                    modifier = Modifier
                        .fillMaxSize()
                        .background(brush)
                        .padding(horizontal = 20.dp, vertical = 30.dp),
                ) {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "KawaiiCastle",
                            color = Color.White
                        )

                        Row(
                            modifier = Modifier.width(100.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_search_24),
                                contentDescription = "search",
                                tint = Color.White
                            )
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_notifications_24),
                                contentDescription = "notification",
                                tint = Color.White
                            )
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_person_24),
                                contentDescription = "profile",
                                tint = Color.White
                            )
                        }

                    }

                    Row(
                        modifier = Modifier.padding(vertical = 20.dp),
                        horizontalArrangement = Arrangement.spacedBy(20.dp)
                    ) {
                        Text(
                            text = "90's",
                            color = Color.White
                        )
                        Text(
                            text = "Top 10",
                            color = Color.White
                        )
                        Text(
                            text = "Categories",
                            color = Color.White
                        )
                        Text(
                            text = "News",
                            color = Color.White
                        )
                    }

                    Column (
                        verticalArrangement = Arrangement.spacedBy(30.dp)
                    ) {
                        YoutubeVideoPlayer(videoId = "wPGjn1uM9zA")

                        Text(text = "Episode 49: One For All",
                            color = Color.White,
                            style = MaterialTheme.typography.titleLarge)

                        Text(text = "All Might's time is running out as he faces his ultimate nemesis head on. Will he finally be able to defeat All For One once and for all?",
                            color = Color.White)
                    }
                }
            }
        }
    }

    @Composable
    fun YoutubeVideoPlayer(videoId: String) {
        val context = LocalContext.current
        val webView = remember {
            WebView(context).apply {
                settings.javaScriptEnabled = true
                settings.loadWithOverviewMode = true
                settings.useWideViewPort = true
                webViewClient = WebViewClient()
            }
        }

        val htmlData = getHTMLData(videoId)

        Column(
            Modifier.fillMaxWidth()
        ) {

            AndroidView(
                factory = { webView },
                modifier = Modifier.background(color = Color.Black)

            ) { view ->
                view.loadDataWithBaseURL(
                    "https://www.youtube.com",
                    htmlData,
                    "text/html",
                    "UTF-8",
                    null
                )
            }

            Row (
                Modifier
                    .padding(top = 30.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterHorizontally)
            ){
                Button(onClick = { webView.loadUrl("javascript:playVideo();") }) {
                    Text(text = "Play",
                        color = Color.White)
                }

                Button(onClick = { webView.loadUrl("javascript:pauseVideo();") }) {
                    Text(text = "Pause",
                        color = Color.White)
                }
            }


        }
    }

    fun getHTMLData(videoId: String): String {
        return """
        <html>
        <body style="margin:0px;padding:0px;background-color: black; display:flex; justify-content: center">
        <div id="player"></div>
        <script>
        var player;
        function onYouTubeIframeAPIReady() {
            player = new YT.Player('player', {
                height: '505',
                width: '853',
                videoId: '$videoId',
                playerVars: {
                    'playsinline': 1
                },
                events: {
                    'onReady': onPlayerReady
                }
            });
        }
        function onPlayerReady(event) {
            player.playVideo();
        }
        function seekTo(time) {
            player.seekTo(time, true);
        }
        function playVideo() {
            player.playVideo();
        }
        function pauseVideo() {
            player.pauseVideo();
        }
        </script>
        <script src="https://www.youtube.com/iframe_api"></script>
        </body>
        </html>
    """.trimIndent()
    }
}