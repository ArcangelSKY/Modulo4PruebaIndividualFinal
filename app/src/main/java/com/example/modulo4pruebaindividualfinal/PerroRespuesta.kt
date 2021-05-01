package com.example.modulo4pruebaindividualfinal

import com.google.gson.annotations.SerializedName

data class PerroRespuesta( // se crea la data para las listas donde esta la api
    @SerializedName("status") var estatus:String,
    @SerializedName("message") var imagenes:List<String>
)
