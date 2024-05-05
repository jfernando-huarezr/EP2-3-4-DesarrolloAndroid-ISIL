package com.huarezreyes.ep2

import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.huarezreyes.ep2.datos.DatosCaja
import com.huarezreyes.ep2.ui.theme.EP2Theme

class CajaActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val brush = Brush.linearGradient(listOf(Color.Black, Color.Black))
            EP2Theme {
                Box (
                    modifier = Modifier.fillMaxSize().background(brush)
                ){
//
                    leerDatos()

                    FloatingActionButton(
                        onClick = {
                            startActivity(Intent(this@CajaActivity, CajaInsertActivity::class.java))
                        }, modifier = Modifier
                            .padding(all = 20.dp)
                            .align(Alignment.BottomEnd)
                    ) {
                        Icon(Icons.Filled.Add, contentDescription = null)
                    }
                }
            }
        }


    }

    @Composable private fun leerDatos() {
        val arrayList = ArrayList<HashMap<String, String>>()
        val datosCaja = DatosCaja(this)
        val cursor: Cursor = datosCaja.movimientosSelect(datosCaja)

        if(cursor.moveToFirst()) {
            do {
                val idmovimiento = cursor.getString(cursor.getColumnIndexOrThrow("idmovimiento"))
                val fecha = cursor.getString(cursor.getColumnIndexOrThrow("fecha"))
                val descripcion = cursor.getString(cursor.getColumnIndexOrThrow("descripcion"))
                val monto = cursor.getString(cursor.getColumnIndexOrThrow("monto"))
                val tipo = cursor.getString(cursor.getColumnIndexOrThrow("tipo"))


                val map = HashMap<String, String>();
                map.put("idmovimiento", idmovimiento)
                map.put("fecha", fecha)
                map.put("descripcion", descripcion)
                map.put("monto", monto)
                map.put("tipo", tipo)
                arrayList.add(map)

            } while (cursor.moveToNext())
            
            dibujar(arrayList)
        }
    }

    @Composable private fun dibujar(arrayList: ArrayList<HashMap<String, String>>) {
        val datosCaja = DatosCaja(this)
        val cursorTotal: Cursor = datosCaja.movimientosTotal(datosCaja)
        val cursorIngresos: Cursor = datosCaja.movimientosIngresos(datosCaja)
        val cursorGastos: Cursor = datosCaja.movimientosGastos(datosCaja)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            if(cursorTotal.moveToFirst()) {
                val columnIndex = cursorTotal.getColumnIndex("subtotal")
                if (columnIndex != -1 && !cursorTotal.isNull(columnIndex)) {
                    val subtotal = cursorTotal.getString(columnIndex)?.toFloatOrNull() ?: 0f

                    Text(text = "Total: S/."+ String.format("%.2f", subtotal),
                        style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.White))
                }
            }

            if(cursorIngresos.moveToFirst()) {

                val columnIndex = cursorIngresos.getColumnIndex("subtotal")
                if (columnIndex != -1 && !cursorIngresos.isNull(columnIndex)) {
                    val subtotal = cursorIngresos.getString(columnIndex)?.toFloatOrNull() ?: 0f

                    Text(text = "Ingresos: S/."+ String.format("%.2f", subtotal),
                        style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.Blue))
                }
            }

            if(cursorGastos.moveToFirst()) {

                val columnIndex = cursorGastos.getColumnIndex("subtotal")
                if (columnIndex != -1 && !cursorGastos.isNull(columnIndex)) {
                    val subtotal = cursorGastos.getString(columnIndex)?.toFloatOrNull() ?: 0f

                    Text(text = "Gastos: S/."+ String.format("%.2f", subtotal),
                        style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.Red))
                }
            }
            Spacer(modifier = Modifier.size(20.dp))
            LazyColumn(
                content = {
                    items(
                        items = arrayList,
                        itemContent = {


                            Box (
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {

                                Column(
                                    modifier = Modifier
                                        .padding(all = dimensionResource(id = R.dimen.espacio2))
                                        .border(
                                            width = 1.dp,
                                            color = Color.Gray,
                                            shape = RectangleShape
                                        )
                                        .background(MaterialTheme.colorScheme.primary)
                                        .padding(all = dimensionResource(id = R.dimen.espacio))
                                        .height(100.dp)
                                        .width(200.dp)


                                ) {
                                    Text(text = it.get("idmovimiento").toString())
                                    Text(text = it.get("fecha").toString())
                                    Text(text = it.get("descripcion").toString())
                                    Text(text = it.get("monto").toString())
                                    Text(text = it.get("tipo").toString())
                                }
                            }

                        }
                    )//items
                }
            )//LazyColumn
        }

    }
}
