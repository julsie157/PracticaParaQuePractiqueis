package com.example.practica3

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class Basededatos(context: Context) : SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION) {
    companion object{
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "db.db"

        private const val TABLA_COCHE = "Coche"
        private const val COLUMN_MODELO ="Modelo"
        private const val COLUMN_ANIO ="Anio"
        private const val COLUMN_MARCA ="Marca"

    }
    override fun onCreate(db: SQLiteDatabase) {
        val createTableCoche = "CREATE TABLE $TABLA_COCHE(" +
                "$COLUMN_MODELO TEXT," +
                "$COLUMN_ANIO INTEGER," +
                "$COLUMN_MARCA TEXT)"

        db.execSQL(createTableCoche)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLA_COCHE")
        onCreate(db)
    }

    fun insertarCoche (coche: Coche) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COLUMN_MARCA,coche.getMarca().name)
        values.put(COLUMN_ANIO,coche.getAnio())
        values.put(COLUMN_MODELO,coche.getModelo())
        db.insert(TABLA_COCHE,null,values)
    }

    @SuppressLint("Range")
    fun leerCoches() : List<Coche> {
        val listaCoches = mutableListOf<Coche>()
        val db = this.readableDatabase
        val query = "SELECT * FROM $TABLA_COCHE"
        val cursor = db.rawQuery(query,null)

        if (cursor.moveToFirst()){
            do {
                var modelo = cursor.getString(cursor.getColumnIndex(COLUMN_MODELO))
                var anio = cursor.getInt(cursor.getColumnIndex(COLUMN_ANIO))
                var marca = Coche.Marca.valueOf(cursor.getString(cursor.getColumnIndex(COLUMN_MARCA)))
                var coche = Coche(modelo, anio, marca)
                listaCoches.add(coche)
            }while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return listaCoches
    }


}