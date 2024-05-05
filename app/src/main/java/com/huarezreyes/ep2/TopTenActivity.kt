package com.huarezreyes.ep2

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.huarezreyes.ep2.ui.theme.EP2Theme
import com.huarezreyes.ep2.utils.Total
import org.json.JSONArray

@OptIn(ExperimentalMaterial3Api::class)
class TopTenActivity : ComponentActivity() {

    val campos =
        arrayOf(
            "idanime",
            "title",
            "genre",
            "episodes",
            "studio",
            "image"
        )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        leerServicio()

    }

    private fun leerServicio() {
        val queue = Volley.newRequestQueue(this)
        val url =  Total.servicioHuarez + "animes.php"
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                Log.d("ANI", response)
                llenarLista(response)
            },
            {
                Log.d("ANIME", it.message.toString())
            })
        queue.add(stringRequest)
    }

    private fun llenarLista(response: String?) {
        val jsonArray = JSONArray(response)
        val arrayList = ArrayList<HashMap<String, String>>()

        val auxArrayValues = ArrayList<String>()

        for (i in 0 until jsonArray.length()) {


            for (j in 0 until campos.size) {
                val valor = jsonArray.getJSONObject(i).getString(campos[j])

                if (valor == "null") {
                    auxArrayValues.add("No hay datos")
                } else {
                    auxArrayValues.add(valor)
                }
            }

            Log.d("PRUEBA", auxArrayValues.toString())

            val map = HashMap<String, String>()


            for (i in 0 until campos.size) {
                map.put(campos[i], auxArrayValues.get(i))
            }

            arrayList.add(map)
            auxArrayValues.clear()
        }

        dibujar(arrayList)
    }

    private fun dibujar(arrayList: ArrayList<HashMap<String, String>>) {
        setContent {
            EP2Theme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(
                                    text = stringResource(id = R.string.title_activity_top_ten),
                                    style = MaterialTheme.typography.titleLarge,
                                    modifier = Modifier
                                        .padding(30.dp),
                                    color = Color.White
                                )
                            },

                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primary
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
                    Box (
                        modifier = Modifier
                            .background(MaterialTheme.colorScheme.background)
                            .padding(it)
                            .padding(vertical = 10.dp, horizontal = 15.dp)
                    ) {
                        Column {
                            LazyVerticalGrid(columns = GridCells.Fixed(2), content = {
                                items(arrayList.size) { posicion ->
                                    Card(
                                        colors = CardDefaults.cardColors(
                                            containerColor = MaterialTheme.colorScheme.background
                                        ),
                                        modifier = Modifier
                                            .padding(all = dimensionResource(id = R.dimen.espacio2))
                                            .height(250.dp)
                                            .shadow(
                                                elevation = dimensionResource(id = R.dimen.espacio2)
                                            )
                                    ) {
                                        Column (
                                            modifier = Modifier
                                                .padding(all = dimensionResource(id = R.dimen.espacio1))
                                                .fillMaxWidth(),
                                            horizontalAlignment = Alignment.CenterHorizontally
                                        ){

                                            Box {
                                                if(arrayList[posicion].get("image").toString() == "null"){
                                                    Image(painter = painterResource(id = R.drawable.noimage), contentDescription = null,
                                                        modifier = Modifier
                                                            .width(100.dp)
                                                            .height(150.dp))
                                                }
                                                else {
                                                    AsyncImage(
                                                        model = Total.servicioHuarez + arrayList[posicion].get(
                                                            "image"
                                                        ).toString(),
                                                        contentDescription = null,
                                                        modifier = Modifier
                                                            .fillMaxWidth()
                                                            .height(150.dp)
                                                    )
                                                }
                                            }
                                            
                                            Spacer(modifier = Modifier.height(8.dp))

                                            Text(
                                                text = arrayList[posicion].get("title").toString(),
                                                textAlign = TextAlign.Center,
                                                fontSize = 14.sp,
                                                fontWeight = FontWeight.Bold
                                            )

                                            Text(
                                                text = arrayList[posicion].get("genre").toString(),
                                                textAlign = TextAlign.Center,
                                                fontSize = 12.sp,
                                            )

                                            Text(
                                                text = arrayList[posicion].get("episodes").toString() + " espisodes",
                                                textAlign = TextAlign.Center,
                                                fontSize = 12.sp,
                                            )

                                            Text(
                                                text = arrayList[posicion].get("studio").toString(),
                                                textAlign = TextAlign.Center,
                                                fontSize = 12.sp,
                                            )


                                        }
                                    }
                                }
                            })//LazyVerticalGrid
                        }
                    }

                }
            }
        }

    }
}
