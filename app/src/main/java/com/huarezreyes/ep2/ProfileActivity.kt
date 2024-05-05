package com.huarezreyes.ep2

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.huarezreyes.ep2.ui.theme.EP2Theme
import com.huarezreyes.ep2.utils.Total
import java.time.format.TextStyle

@OptIn(ExperimentalMaterial3Api::class)
class ProfileActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            EP2Theme {
                val context = LocalContext.current
                val sharedPref = getSharedPreferences("Credentials", Context.MODE_PRIVATE)
                val savedUsername = sharedPref?.getString("Username", null)
                val savedPassword = sharedPref?.getString("Password", null)

                var showDialog by remember { mutableStateOf(false) }

                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(
                                    text = "Profile",
                                    style = MaterialTheme.typography.titleLarge,
                                    modifier = Modifier
                                        .padding(30.dp),
                                    color = Color.White,
                                    textAlign = TextAlign.Center
                                )
                            },

                            colors = TopAppBarDefaults.smallTopAppBarColors(
                                containerColor = MaterialTheme.colorScheme.secondary
                            ),

                            navigationIcon = {
                                IconButton(onClick = {
                                    finish()
                                }) {
                                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = null,
                                        tint = Color.White)
                                }
                            },
                        )
                    }
                ) {
                    Column (
                        modifier = Modifier
                            .background(MaterialTheme.colorScheme.background)
                            .padding(it)
                            .padding(vertical = 30.dp, horizontal = 30.dp),

                        verticalArrangement = Arrangement.spacedBy(80.dp)
                    ) {
                       Row (
                           Modifier.fillMaxWidth(),
                           horizontalArrangement = Arrangement.spacedBy(30.dp, Alignment.CenterHorizontally)

                       ) {
                           Column (
                               verticalArrangement = Arrangement.spacedBy(20.dp)
                           ) {

                               Image(
                                   painter =  painterResource(id = R.drawable.profile1),
                                   contentDescription = "profile1",
                                   contentScale = ContentScale.Fit,
                                   modifier = Modifier
                                       .height(60.dp)
                                       .clip(CircleShape)
                                       .border(1.dp, MaterialTheme.colorScheme.primary, CircleShape)
                               )
                               
                               Text(text = "Profile 1")

                           }

                           Column (
                               verticalArrangement = Arrangement.spacedBy(20.dp)
                           ) {
                               Image(
                                   painter =  painterResource(id = R.drawable.profile2),
                                   contentDescription = "profile1",
                                   contentScale = ContentScale.Fit,
                                   modifier = Modifier
                                       .height(60.dp)
                                       .clip(CircleShape)
                                       .border(1.dp, MaterialTheme.colorScheme.primary, CircleShape)
                               )
                               
                               Text(text = "Profile 2")

                           }

                           Column (
                               verticalArrangement = Arrangement.spacedBy(20.dp),
                               horizontalAlignment = Alignment.CenterHorizontally
                           ) {

                               Icon(painter = painterResource(id = R.drawable.baseline_add_circle_24)  , contentDescription = "Add",
                                   tint = Color.White,
                                   modifier = Modifier
                                       .padding(horizontal = 5.dp)
                                       .height(60.dp)
                                       .width(60.dp)
                                       .clip(CircleShape)
                                       .border(1.dp, Color.White, CircleShape)
                               )

                               Text(text = "Add",
                                   textAlign = TextAlign.Center
                               )

                           }
                       }

                        Column (
                            verticalArrangement = Arrangement.spacedBy(15.dp),
                        ) {
                            Row (
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ){
                                Text(text = "Manage Profiles")
                                Icon(painter = painterResource(id = R.drawable.baseline_keyboard_arrow_right_24)  , contentDescription = "Go",
                                )
                            }

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(text = "App Settings")
                                Icon(painter = painterResource(id = R.drawable.baseline_keyboard_arrow_right_24)  , contentDescription = "Go",
                                )
                            }

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(text = "Account")
                                Icon(painter = painterResource(id = R.drawable.baseline_keyboard_arrow_right_24)  , contentDescription = "Go",
                                )
                            }

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(text = "Help")
                                Icon(painter = painterResource(id = R.drawable.baseline_keyboard_arrow_right_24)  , contentDescription = "Go",
                                )
                            }
                        }

                        Box {
                            Button(onClick = {
                                showDialog = true
                            }) {
                                Text(text = "Logout", color = Color.White)
                            }

                            if (showDialog) {
                                AlertDialog(
                                    onDismissRequest = {
                                        showDialog = false
                                    },
                                    title = {
                                        Text(text = "Confirmar", color = MaterialTheme.colorScheme.secondary)
                                    },
                                    text = {
                                        Text("¿Estás seguro de que quieres cerrar sesión?", color = MaterialTheme.colorScheme.secondary)
                                    },
                                    confirmButton = {
                                        Button(onClick = {
                                            if (savedUsername != null && savedPassword != null) {
                                                // Las credenciales están guardadas en SharedPreferences, puedes omitir la pantalla de inicio de sesión
                                                val sharedPref = context.getSharedPreferences("Credentials", Context.MODE_PRIVATE)
                                                with (sharedPref?.edit()) {
                                                    this?.remove("Username")
                                                    this?.remove("Password")
                                                    this?.apply()
                                                }

                                                Toast.makeText(this@ProfileActivity, "Sesión Cerrada", Toast.LENGTH_SHORT).show()
                                                startActivity(Intent(this@ProfileActivity, MainActivity::class.java))
                                            } else {
                                                Toast.makeText(this@ProfileActivity, "No hay credenciales guardadas", Toast.LENGTH_SHORT).show()
                                            }
                                            showDialog = false
                                        }) {
                                            Text("Sí")
                                        }
                                    },
                                    dismissButton = {
                                        Button(onClick = {
                                            showDialog = false
                                        }) {
                                            Text("No")
                                        }
                                    },
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

