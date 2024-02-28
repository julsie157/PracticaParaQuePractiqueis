package com.example.practica3

import android.os.Parcel
import android.os.Parcelable


class Coche(
    private var modelo: String?,
    private var anio:Int,
    private var marca: Marca
) : Parcelable {
    enum class Marca {Audi,BMW,Mercedes}

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readInt(),
        parcel.readSerializable() as Marca
    ) {
    }

    fun getModelo()=modelo
    fun getAnio()=anio
    fun getMarca()=marca

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(modelo)
        parcel.writeInt(anio)
        parcel.writeSerializable(marca)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun toString(): String {
        return "Coche(modelo=$modelo, anio=$anio, marca=$marca)"
    }

    companion object CREATOR : Parcelable.Creator<Coche> {
        override fun createFromParcel(parcel: Parcel): Coche {
            return Coche(parcel)
        }

        override fun newArray(size: Int): Array<Coche?> {
            return arrayOfNulls(size)
        }
    }
}