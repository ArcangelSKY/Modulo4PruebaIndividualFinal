package com.example.modulo4pruebaindividualfinal





import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import layout.PerroAdaptador
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

        // asigna la variable con la vista
        RecyclerView = findViewById(R.id.recyclerview)

        // se llama el metodo para la ejecucion del programa
        consumoApi()
    }

    private fun adapatdorMasRecicler(puppies:PerroRespuesta) {
        // metodo para llevar los datos al adapter y que se puedan mostrar las imagenes en listas
        perroAdaptador = PerroAdaptador(puppies.imagenes)
        RecyclerView.setHasFixedSize(true)
        RecyclerView.layoutManager = LinearLayoutManager(this)

        RecyclerView.adapter = perroAdaptador
    }
    // metodo consume la api de la pagina web
    private fun consumoApi() {
        // se sincroniza o realiza al busqueda de los elementos dentro de la api
        doAsync {
            val call = getRetrofit().create(ServicioAPI::class.java).tomarPorNombre("mix/images").execute()
            val puppies = call.body() as PerroRespuesta
            uiThread {

                    adapatdorMasRecicler(puppies)

            }
        }
    }
    // se tiene la datos de la página web dentro de la api
    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl("https://dog.ceo/api/breed/").addConverterFactory(
            GsonConverterFactory.create()).build()
    }





}
