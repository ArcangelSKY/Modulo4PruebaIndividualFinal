package com.example.modulo4pruebaindividualfinal

import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import layout.PerroAdaptador
import org.jetbrains.anko.alert
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {
    lateinit var RecyclerView: RecyclerView
    lateinit var perroAdaptador:PerroAdaptador

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        RecyclerView = findViewById(R.id.recyclerview)
        searchByName()
    }

    private fun initCharacter(puppies:PerroRespuesta) {

        perroAdaptador = PerroAdaptador(puppies.imagenes)
        RecyclerView.setHasFixedSize(true)
        RecyclerView.layoutManager = LinearLayoutManager(this)

        RecyclerView.adapter = perroAdaptador
    }

    private fun searchByName() {
        doAsync {
            val call = getRetrofit().create(ServicioAPI::class.java).tomarPorNombre("mix/images").execute()
            val puppies = call.body() as PerroRespuesta
            uiThread {

                    initCharacter(puppies)

            }
        }
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl("https://dog.ceo/api/breed/").addConverterFactory(
            GsonConverterFactory.create()).build()
    }





}
