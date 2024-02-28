package com.example.practica3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView

class Listado : AppCompatActivity() {

    private lateinit var coches :TextView
    private lateinit var botoninicio: Button
    private var listaCoches : ArrayList<Coche> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_listado)
        if (!intent.getParcelableArrayListExtra<Coche>("arrayCoches").isNullOrEmpty())
            listaCoches = intent.getParcelableArrayListExtra("arrayCoches")!!
        coches = findViewById(R.id.textViewListado)
        botoninicio = findViewById(R.id.botonInicio)
        val db = Basededatos(this)

        val listadoCoches = db.leerCoches()
        listadoCoches.joinToString("\n")
        coches.text = listadoCoches.toString()


        botoninicio.setOnClickListener {
            intent = Intent(this,MainActivity::class.java)
            intent.putParcelableArrayListExtra("arrayCoches",ArrayList(listadoCoches))
            startActivity(intent)
        }

    }

}