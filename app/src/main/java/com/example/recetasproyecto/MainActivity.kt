package com.example.recetasproyecto

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.retrofit_prueba.recetaPrueba.Post
import com.example.retrofit_prueba.recetaPrueba.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var recipesTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recetas_home)

        recipesTextView = findViewById(R.id.recipesTextView)

        val apiService = RetrofitClient.apiService

        // Obtener recetas
        apiService.getRecetas().enqueue(object : Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                if (response.isSuccessful) {
                    val posts = response.body()
                    // Construir un String con las recetas
                    val recipesText = posts?.joinToString(separator = "\n") { post ->
                        "Receta: ${post.nomReceta}\nIngredientes: ${post.ingredientes}\nProcedimiento: ${post.procedimiento}"
                    } ?: "No hay recetas disponibles"
                    // Actualizar el TextView con las recetas
                    recipesTextView.text = recipesText
                } else {
                    recipesTextView.text = "Error en la respuesta: ${response.code()}"
                }
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                recipesTextView.text = "Error al obtener recetas: ${t.message}"
            }
        })
    }
}
