package com.huarezreyes.ep2.utils

import org.json.JSONObject

class Total {
    companion object{
        const val rutaServicio= "https://servicios.campus.pe/"
        //const val rutaServicio= "https://localhost/servicio/" NOOOOOOOOO https://127.0.0.1/servicio/ TAMPOCO
        //const val rutaServicio= "https://10.0.2.2/servicio/"
        //const val rutaServicio= "https://192.168.18.4/servicio/" El IP de su PC en la red local
        var usuarioActivo: JSONObject = JSONObject()

        const val servicioHuarez = "http://192.168.1.117:8080/servicioweb/"
    }
}