package com.example.modulo4pruebaindividualfinal

import com.google.gson.annotations.SerializedName
// la data de los datos dentro de la api
data class PerroRespuesta( // se crea la data para las listas donde esta la api
    @SerializedName("status") var estatus:String,
    @SerializedName("message") var imagenes:List<String>
)
