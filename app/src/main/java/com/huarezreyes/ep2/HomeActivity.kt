package com.huarezreyes.ep2

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.huarezreyes.ep2.ui.theme.EP2Theme

class HomeActivity : ComponentActivity() {
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

                    Row (
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ){
                        Text(text = "KawaiiCastle",
                            color = Color.White
                        )

                        Row (
                            modifier = Modifier.width(100.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Icon(painter = painterResource(id = R.drawable.baseline_search_24)  , contentDescription = "search",
                                tint = Color.White)
                            Icon(painter = painterResource(id = R.drawable.baseline_notifications_24), contentDescription = "notification",
                                tint = Color.White)
                            Icon(painter = painterResource(id = R.drawable.baseline_person_24) , contentDescription ="profile",
                                tint = Color.White,
                                modifier = Modifier.clickable {
                                    startActivity(Intent(this@HomeActivity, ProfileActivity::class.java))
                                })
                        }

                    }

                    Row (
                        modifier = Modifier.padding(vertical = 20.dp),
                        horizontalArrangement = Arrangement.spacedBy(20.dp)
                    ) {
                        Text(text = "90's",
                            color = Color.White)
                        Text(text = "Top 10",
                            color = Color.White,
                            modifier = Modifier.clickable {
                               startActivity(Intent(this@HomeActivity, TopTenActivity::class.java))
                            })
                        Text(text = "Categories",
                            color = Color.White)
                        Text(text = "News",
                            color = Color.White)
                    }

                    Box {
                        AsyncImage(
                            model = "https://vernoshop.com/image/cache/catalog/li/default/6d3885ad51068b71096b34fc4d3e938e-800x800.jpeg",
                            contentDescription = "bnh",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(400.dp),
                            contentScale = ContentScale.Crop
                        )

                        Box (
                            modifier = Modifier
                                .height(400.dp),
                            contentAlignment = Alignment.BottomStart
                        ) {
                            Row (
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(70.dp)
                                    .background(
                                        Brush.verticalGradient(
                                            listOf(
                                                Color.Transparent,
                                                Color.Black,
                                            )
                                        )
                                    ),
                                horizontalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterHorizontally)
                            ) {
                                Button(onClick = {
                                    startActivity(Intent(this@HomeActivity, VideoActivity::class.java))
                                }) {
                                    Text(text = "Play",
                                        color = Color.White)
                                    Icon(painter = painterResource(id = R.drawable.baseline_play_arrow_24)  , contentDescription = "search",
                                        tint = Color.White,
                                        modifier = Modifier.padding(horizontal = 2.dp))
                                }

                                Button(onClick = { /*TODO*/ }) {
                                    Text(text = "Add",
                                        color = Color.White)
                                    Icon(painter = painterResource(id = R.drawable.baseline_add_circle_24)  , contentDescription = "search",
                                        tint = Color.White,
                                        modifier = Modifier.padding(horizontal = 5.dp))
                                }
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(text = "New Releases",
                        color = Color.White)
                    Row (
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        AsyncImage(
                            model = "https://i.ytimg.com/vi/0i0z98M2y5U/mqdefault.jpg",
                            contentDescription = "new1",
                            modifier = Modifier
                                .height(65.dp),
                            contentScale = ContentScale.Fit
                        )

                        AsyncImage(
                            model = "https://img.youtube.com/vi/hTzyapgwHy8/mqdefault.jpg",
                            contentDescription = "new2",
                            modifier = Modifier
                                .height(65.dp),
                            contentScale = ContentScale.Fit
                        )

                        AsyncImage(
                            model = "https://i.ytimg.com/vi/BUr95jyF6BQ/mqdefault.jpg",
                            contentDescription = "new3",
                            modifier = Modifier
                                .height(65.dp),
                            contentScale = ContentScale.Fit
                        )

                    }

                    Spacer(modifier = Modifier.height(20.dp))
                    Text(text = "Now Watching",
                        color = Color.White)
                    Row (
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        AsyncImage(
                            model = "https://1.bp.blogspot.com/-4zXG_MMGvRA/WK7KRAGiauI/AAAAAAAAFJk/qouBIh0GPHUhyZnzpJo7LZ4vdK18ZJQcQCLcB/s320/IMG_114326.png",
                            contentDescription = "new1",
                            modifier = Modifier
                                .height(65.dp),
                            contentScale = ContentScale.Fit
                        )

                        AsyncImage(
                            model = "https://i.ytimg.com/vi/a3faNC5Ki4o/mqdefault.jpg",
                            contentDescription = "new2",
                            modifier = Modifier
                                .height(65.dp),
                            contentScale = ContentScale.Fit
                        )

                        AsyncImage(
                            model = "https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEgVwvbILJq-Je5His03RxKu1i8ZxHHTVqjbDwiOm7tSSazLYPdf1-iS2_8rP5cqXsU7MXMiUM6VSfIpgJsjPVd_hOXYIlkcbbmm1lMM-uAJL6C9ynOmHcq9AchE7PQpGwX3PVoZa3ao08lh8SZVreXNDbF4PtAGQZWGoTnYvlbgATbucnA9MeuQhiqlRA/s320/anime-sinyaya-tyurma-blyu-lok-2-sezon-kogda-data-vyhoda-v-2024.jpg",
                            contentDescription = "new3",
                            modifier = Modifier
                                .height(65.dp),
                            contentScale = ContentScale.Fit
                        )

                    }

                }

            }

        }
    }
}

