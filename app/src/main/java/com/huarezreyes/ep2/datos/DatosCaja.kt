package com.huarezreyes.ep2.datos

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatosCaja(context: Context
): SQLiteOpenHelper(context, "micaja.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE movimientos(" +
                "idmovimiento INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "fecha DEFAULT CURRENT_TIMESTAMP,"  +
                "descripcion TEXT, " +
                "monto FLOAT," +
                "tipo INTEGER)")
    }

    fun registrarMovimiento(datosCaja: DatosCaja, descripcion: String, monto: Float, tipo: Int ): Long {
        val db = datosCaja.writableDatabase
        //db.execSQL("INSERT INTO ...")
        val contentValues = ContentValues().apply {
            put("descripcion", descripcion)
            put("monto", monto)
            put("tipo", tipo)
        }

        val autonumerico = db.insert("movimientos", null, contentValues)
        return autonumerico
    }

    fun movimientosSelect(datosCaja: DatosCaja) : Cursor {
        val db = datosCaja.readableDatabase
        val sql = "SELECT * from movimientos ORDER BY idmovimiento DESC"
        return db.rawQuery(sql, null)
    }

    fun movimientosTotal(datosCaja: DatosCaja) : Cursor {
        val db = datosCaja.readableDatabase
        val sql = "SELECT SUM(monto*tipo) AS subtotal from movimientos"
        return db.rawQuery(sql,null)
    }

    fun movimientosIngresos(datosCaja: DatosCaja) : Cursor {
        val db = datosCaja.readableDatabase
        val sql = "SELECT SUM(monto*tipo) AS subtotal FROM movimientos WHERE tipo=1"
        return db.rawQuery(sql,null)
    }

    fun movimientosGastos(datosCaja: DatosCaja) : Cursor {
        val db = datosCaja.readableDatabase
        val sql = "SELECT SUM(monto*tipo) AS subtotal FROM movimientos WHERE tipo=-1"
        return db.rawQuery(sql,null)
    }



    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}