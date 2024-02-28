package com.example.practica3

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Informacion : AppCompatActivity() {

    private lateinit var db: Basededatos
    private lateinit var marca: TextView
    private lateinit var modelo: TextView
    private lateinit var anio: TextView
    private lateinit var botonAtras: Button
    private lateinit var botonVer: Button
    private lateinit var botonGuardar: Button
    private lateinit var botonAñadir: Button
    private var listaCoches : ArrayList<Coche> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_info)

        marca = findViewById(R.id.textViewMarca)
        modelo = findViewById(R.id.textViewModelo)
        anio = findViewById(R.id.textViewAnio)
        botonAtras = findViewById(R.id.botonAtras)
        botonVer = findViewById(R.id.botonVer)
        botonGuardar = findViewById(R.id.botonGuardar)
        botonAñadir = findViewById(R.id.botonAñadir)
        if (!intent.getParcelableArrayListExtra<Coche>("arrayCoches").isNullOrEmpty())
            listaCoches = intent.getParcelableArrayListExtra("arrayCoches")!!

        marca.text = intent.getStringExtra("intentExtraMarca")
        modelo.text = intent.getStringExtra("intentExtraModelo")
        anio.text = intent.getStringExtra("intentExtraAnio")

        var marcas = when (marca.text.toString())  {
            "BMW"-> Coche.Marca.BMW
            "Mercedes" -> Coche.Marca.Mercedes
            "Audi" -> Coche.Marca.Audi
            else -> throw IllegalArgumentException("ERROR")
        }

        botonAñadir.setOnClickListener {
            listaCoches.add(Coche(modelo.text.toString(),anio.text.toString().toInt(),marcas))
            var intent = Intent(this,MainActivity::class.java)
            intent.putParcelableArrayListExtra("arrayCoches", ArrayList(listaCoches))
            Log.d("***AAA1***", listaCoches.toString())
            startActivity(intent)
        }

        botonAtras.setOnClickListener {
            var intent = Intent(this,MainActivity::class.java)
            intent.putParcelableArrayListExtra("arrayCoches",ArrayList(listaCoches))
            startActivity(intent)
        }

        botonGuardar.setOnClickListener {
            Log.d("***AAA2***", listaCoches.toString())
            if(!listaCoches.isNullOrEmpty()){
                for (c in listaCoches){

                    db.insertarCoche(c)
                    Log.d("***AAA3***", listaCoches.toString())
                }
                listaCoches= arrayListOf()
            }
        }

        botonVer.setOnClickListener {
            var intent = Intent(this,Listado::class.java)
            intent.putParcelableArrayListExtra("arrayCoches",ArrayList(listaCoches))
            startActivity(intent)
        }

    }
}