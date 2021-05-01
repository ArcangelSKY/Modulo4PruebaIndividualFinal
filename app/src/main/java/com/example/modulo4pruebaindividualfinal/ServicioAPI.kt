package com.example.modulo4pruebaindividualfinal

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface ServicioAPI {
    @GET        //Toma el dato segun la llamada que tiene la lista de la data Perros Respuesta
    fun tomarPorNombre(@Url url: String): Call<PerroRespuesta>
}