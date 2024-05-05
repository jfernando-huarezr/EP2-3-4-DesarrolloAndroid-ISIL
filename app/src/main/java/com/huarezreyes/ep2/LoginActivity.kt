package com.huarezreyes.ep2

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.huarezreyes.ep2.ui.theme.EP2Theme
import com.huarezreyes.ep2.utils.Total
import org.json.JSONArray

@OptIn(ExperimentalMaterial3Api::class)
class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EP2Theme {

                //para probar usar ALFKI - 12209
                val sharedPref = getSharedPreferences("Credentials", Context.MODE_PRIVATE)
                val savedUsername = sharedPref?.getString("Username", null)
                val savedPassword = sharedPref?.getString("Password", null)

                val context = LocalContext.current


                if (savedUsername != null && savedPassword != null) {
                    // Las credenciales están guardadas en SharedPreferences, puedes omitir la pantalla de inicio de sesión
                    startActivity(Intent(this, HomeActivity::class.java))
                    finish()
                } else {

                    var usuario by remember { mutableStateOf("") }
                    var clave by remember { mutableStateOf("") }
                    var rememberMe by remember { mutableStateOf(false) }

                    var isValidUsuario by remember { mutableStateOf(false) }

                    val brush = Brush.linearGradient(listOf(Color.Black, Color.Black))

                    Column (
                        modifier = Modifier
                            .fillMaxSize()
                            .background(brush),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "KawaiiApp", style = TextStyle(color = Color.White, fontSize = 20.sp))
                        Text(text = "LOGIN", style = TextStyle(color = Color.White, fontSize = 40.sp))
                        Spacer(modifier = Modifier.size(30.dp))
                        Column(
                            modifier = Modifier.padding(all = 32.dp)
                        ) {
                            Text(text = "Username",
                            color = Color.White)
                            TextField(value = usuario,
                                modifier = Modifier.fillMaxWidth(),
                                textStyle = TextStyle(color = Color.Black),
                                onValueChange = {
                                    usuario = it
                                    isValidUsuario = it.isNotEmpty()
                                })

                            Spacer(modifier = Modifier.size(16.dp))
                            Text(text = "Password",
                                color = Color.White)
                            TextField(value = clave,
                                modifier = Modifier
                                    .fillMaxWidth(),
                                textStyle = TextStyle(color = Color.Black),
                                visualTransformation = PasswordVisualTransformation(),
                                onValueChange = {
                                    clave = it
                                })

                            Spacer(modifier = Modifier.size(16.dp))

                            Row (
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Checkbox(
                                    checked = rememberMe,
                                    onCheckedChange = { checked ->
                                        rememberMe = checked
                                    },
                                    colors = CheckboxDefaults.colors(
                                        checkmarkColor = Color.White
                                    )
                                )

                                Text(text = "Remember me",
                                    color = Color.White)
                            }



                            Spacer(modifier = Modifier.size(16.dp))
                            Button(onClick = {
                                leerServicioIniciarSesion(usuario, clave, rememberMe)
                            },
                                modifier = Modifier.fillMaxWidth()) {
                                Text(text = "Login",
                                    color = Color.White,
                                    textAlign = TextAlign.Center)
                            }

                            Spacer(modifier = Modifier.size(16.dp))
                            if(!isValidUsuario) {
                                Text(text = "Incorrect user", color = Color.Red)
                            }
                        }
                    }


                }
            }
        }
    }

    private fun leerServicioIniciarSesion(usuario: String, clave: String, rememberMe:Boolean) {
        val queue = Volley.newRequestQueue(this)
        val url = Total.rutaServicio + "iniciarsesion.php"
        val stringRequest = object: StringRequest(
            Request.Method.POST, url,
            { response ->
                Log.d("DATOS", response)
                verificarInicioSesion(response, usuario, clave, rememberMe)
            },
            {
                Log.d("DATOSERROR", it.message.toString())
            }) {
            override fun getParams(): MutableMap<String, String>? {
                val params = HashMap<String, String>()
                params.put("usuario", usuario)
                params.put("clave", clave)
                return params
            }
        }
        queue.add(stringRequest)
    }

    private fun verificarInicioSesion(response: String?, usuario: String, clave: String, rememberMe: Boolean) {
        when (response) {
            "-1" -> Toast.makeText(this, "El usuario no existe", Toast.LENGTH_SHORT).show()
            "-2" -> Toast.makeText(this, "La contraseña es incorrecta", Toast.LENGTH_SHORT).show()
            else -> {
                Total.usuarioActivo = JSONArray(response).getJSONObject(0)
                Toast.makeText(
                    this,
                    "Hola " + Total.usuarioActivo.getString("nombres"),
                    Toast.LENGTH_SHORT
                ).show()
                startActivity(Intent(this, HomeActivity::class.java))

                if (rememberMe) {
                    val sharedPref = this.getSharedPreferences("Credentials", Context.MODE_PRIVATE)
                    with(sharedPref?.edit()) {
                        this?.putString("Username", usuario)
                        this?.putString("Password", clave)
                        this?.apply()
                    }
                }
            }
        }
    }
}
