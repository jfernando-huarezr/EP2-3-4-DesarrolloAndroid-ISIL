package com.huarezreyes.ep2

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.huarezreyes.ep2.datos.DatosCaja
import com.huarezreyes.ep2.ui.theme.EP2Theme

@OptIn(ExperimentalMaterial3Api::class)
class CajaInsertActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EP2Theme {
                val brush = Brush.linearGradient(listOf(Color.Black, Color.Black))
                var descripcion by remember { mutableStateOf("") }
                var monto by remember { mutableStateOf("") }
                var tipo by remember { mutableStateOf(-1) }

                Box (
                    modifier = Modifier
                        .fillMaxSize()
                        .background(brush)
                ){
                    Column(modifier = Modifier.padding(all = 32.dp)) {
                        TextField(value = descripcion,
                            label = { Text(text = "Descripcion del gasto",
                                color = Color.Black) },
                            modifier = Modifier.fillMaxWidth()
                                .background(Color.Black),
                            textStyle = TextStyle(color = Color.Black),
                            onValueChange = {
                                descripcion= it
                            },

                        )
                        Spacer(modifier = Modifier.size(16.dp))
                        TextField(value = monto,
                            label = { Text(text = "Monto",
                                        color = Color.Black) },
                            modifier = Modifier.fillMaxWidth()
                                .background(Color.Black),
                            textStyle = TextStyle(color = Color.Black),
                            onValueChange = {
                                monto = it
                            })
                        Spacer(modifier = Modifier.size(16.dp))
                        Switch(
                            checked = tipo > 0,
                            onCheckedChange = { isChecked ->
                                tipo = if (isChecked) 1 else -1
                            },
                            modifier = Modifier.background(Color.Black)
                        )

                        if(tipo>0) {
                            Text(text = "Ingreso", color = Color.Green)
                        }
                        else {
                            Text(text = "Gasto", color = Color.Red)
                        }
                        Spacer(modifier = Modifier.size(16.dp))
                        Button(onClick = {
                            guardarDatos(descripcion, monto, tipo)
                        }) {
                            Text(text = "Registrar")
                        }
                    }
                }


            }
        }
    }

    private fun guardarDatos(descripcion: String, monto: String, tipo: Int) {
        val datosCaja = DatosCaja(this)
        val autonumerico = datosCaja.registrarMovimiento(datosCaja, descripcion, monto.toFloat(), tipo)
        Toast.makeText(this, "id: "+ autonumerico, Toast.LENGTH_SHORT).show()

        startActivity(Intent(this@CajaInsertActivity, CajaActivity::class.java))
        finish()
    }
}
