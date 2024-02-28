package com.example.practica3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    private lateinit var marca: EditText
    private lateinit var modelo: EditText
    private lateinit var anio: EditText
    private lateinit var botonContinuar: Button
    private var listaCoches: ArrayList<Coche> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        marca = findViewById(R.id.editTextMarca)
        modelo = findViewById(R.id.editTextModelo)
        anio = findViewById(R.id.editTextAnio)
        botonContinuar = findViewById(R.id.botonContinuar)

        if (!intent.getParcelableArrayListExtra<Coche>("arrayCoches").isNullOrEmpty())
            listaCoches = intent.getParcelableArrayListExtra("arrayCoches")!!

        botonContinuar.setOnClickListener {
            val marca = marca.text.toString()
            val modelo = modelo.text.toString()
            val anio = anio.text.toString()
            Log.d("***AAAA***", listaCoches.toString())
            val intent = Intent(this, Informacion::class.java).apply {
                intent.putParcelableArrayListExtra("arrayCoches", ArrayList(listaCoches))
                putExtra("intentExtraMarca", marca)
                putExtra("intentExtraModelo", modelo)
                putExtra("intentExtraAnio", anio)
            }
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        marca.setText("")
        modelo.setText("")
        anio.setText("")
    }

}