package com.huarezreyes.ep2

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Switch
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.Circle
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.huarezreyes.ep2.ui.theme.EP2Theme


class DetalleEnvioActivity : ComponentActivity() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = intent
        val latitudEnviada = intent.getStringExtra("latitud")
        val longitudEnviada = intent.getStringExtra("longitud")
        val nombre = intent.getStringExtra("nombre")
        val telefono = intent.getStringExtra("telefono")

        dibujarMapa(latitudEnviada, longitudEnviada, nombre, telefono)
    }

    fun dibujarMapa(
        latitudEnviada: String?,
        longitudEnviada: String?,
        nombre: String?,
        telefono: String?
    ) {

        var latitudInicial = latitudEnviada!!.toDouble()
        var longitudInicial = longitudEnviada!!.toDouble()

        setContent {

            EP2Theme {
                var latitud by remember {
                    mutableStateOf(latitudInicial)
                }
                var longitud by remember {
                    mutableStateOf(longitudInicial)
                }

                val ubicacion = LatLng(latitud,longitud)
                var uiSettings by remember { mutableStateOf(MapUiSettings()) }

                val properties by remember {
                    mutableStateOf(
                        MapProperties(
                            //mapStyleOptions = MapStyleOptions.loadRawResourceStyle(this, R.raw.map_style,),
                            mapType = MapType.NORMAL
                        )
                    )
                }


                Box(modifier = Modifier.fillMaxSize()) {
                    GoogleMap(
                        modifier = Modifier.fillMaxSize(),
                        cameraPositionState = CameraPositionState(
                            CameraPosition.fromLatLngZoom(
                                ubicacion,
                                17f
                            )
                        ),
                        properties = properties,
                        uiSettings = uiSettings
                    ) {
                        Marker(
                            state = MarkerState(position = ubicacion),
                            title = nombre,
                            snippet = "Llamenos: " + telefono
                        )

                        Circle(
                            center = ubicacion,
                            fillColor = Color(0x6687CEEB),
                            radius = 100.0,
                            clickable = true,
                            onClick = {
                                Toast.makeText(
                                    this@DetalleEnvioActivity,
                                    nombre,
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        )
                    }

                    Switch(
                        checked = uiSettings.zoomControlsEnabled,
                        onCheckedChange = {
                            uiSettings = uiSettings.copy(zoomControlsEnabled = it)
                        },
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }


        }
    }
}

