package com.huarezreyes.ep2

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.huarezreyes.ep2.ui.theme.EP2Theme
import java.time.format.TextStyle


class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val brush = Brush.linearGradient(listOf(Color.Black, Color.Black))

            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .background(brush)
            ) {
                Box {
                    Column {
                            Image(
                                painter =  painterResource(id = R.drawable.bluelock),
                                contentDescription = "anime1",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(230.dp)
                            )
                            Image(
                                painter =  painterResource(id = R.drawable.onepiece),
                                contentDescription = "anime2",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(230.dp)
                            )
                            Image(
                                painter =  painterResource(id = R.drawable.jjk),
                                contentDescription = "anime3",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(230.dp)
                            )
                        }

                        Box (
                            modifier = Modifier
                                .height(690.dp),
                            contentAlignment = Alignment.BottomStart
                        ) {

                            Column (
                                modifier = Modifier
                                    .height(150.dp)
                                    .fillMaxWidth()
                                    .background(
                                        Brush.verticalGradient(
                                            listOf(
                                                Color.Transparent,
                                                Color.Black,
                                            )
                                        )
                                    )
                            ) {
                                Text(text = "Download\nand Enjoy",
                                    color = Color.White,
                                    modifier = Modifier
                                        .padding(horizontal = 10.dp, vertical = 5.dp),
                                    style = MaterialTheme.typography.titleLarge,
                                    fontWeight = FontWeight(weight = 600),
                                    fontSize = 30.sp
                                )
                                Text(text = "You can download any of episodes and seasons then watch it offline",
                                    color = Color.White,
                                    modifier = Modifier
                                        .padding(horizontal = 10.dp, vertical = 5.dp),
                                    style = MaterialTheme.typography.bodyLarge
                                )
                            }
                        }
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 40.dp, horizontal = 5.dp),
                        contentAlignment = Alignment.Center
                    ) {

                        Row (
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(5.dp, Alignment.CenterHorizontally)

                        ) {
                            Button(
                                onClick = {
                                    startActivity(Intent(this@MainActivity, LoginActivity::class.java))
                                },
                                modifier = Modifier
                                    .width(95.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = MaterialTheme.colorScheme.primary
                                )
                            ) {
                                Text(text = "Next",
                                    fontSize = 11.sp)
                            }

                            Button(
                                onClick = {
                                    startActivity(Intent(this@MainActivity, PedidosActivity::class.java))
                                },
                                modifier = Modifier
                                    .width(95.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = MaterialTheme.colorScheme.primary
                                )
                            ) {
                                Text(text = "Pedidos",
                                    fontSize = 10.sp)
                            }

                            Button(
                                onClick = {
                                    startActivity(Intent(this@MainActivity, CajaActivity::class.java))
                                },
                                modifier = Modifier
                                    .width(95.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = MaterialTheme.colorScheme.primary
                                )
                            ) {
                                Text(text = "Caja",
                                    fontSize = 11.sp)
                            }

                            Button(
                                onClick = {
                                    startActivity(Intent(this@MainActivity, EnviosActivity::class.java))
                                },
                                modifier = Modifier
                                    .width(95.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = MaterialTheme.colorScheme.primary
                                )
                            ) {
                                Text(text = "Envios",
                                    fontSize = 10.sp)
                            }
                        }

                    }

                }


        }
    }
}