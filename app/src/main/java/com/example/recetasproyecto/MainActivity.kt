package com.example.recetasproyecto

import RecipeAdapter
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit_prueba.recetaPrueba.Post
import com.example.retrofit_prueba.recetaPrueba.RetrofitClient
import com.example.retrofit_prueba.recetaPrueba.MyApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var recipesRecyclerView: RecyclerView
    private lateinit var recipeAdapter: RecipeAdapter
    private lateinit var searchEditText: EditText
    private lateinit var searchButton: Button
    private lateinit var apiService: MyApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recetas_home)

        recipesRecyclerView = findViewById(R.id.recipesRecyclerView)
        searchEditText = findViewById(R.id.searchEditText)
        searchButton = findViewById(R.id.searchButton)

        recipesRecyclerView.layoutManager = LinearLayoutManager(this)

        // Inicializar Retrofit
        apiService = RetrofitClient.apiService

        // Configurar el botón de búsqueda
        searchButton.setOnClickListener {
            val query = searchEditText.text.toString()
            if (query.isNotEmpty()) {
                searchRecipes(query)
            }
        }

        // Código para obtener recetas iniciales (opcional)
        fetchRecipes()
    }

    private fun fetchRecipes() {
        apiService.getRecetas().enqueue(object : Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                if (response.isSuccessful) {
                    val recipes = response.body() ?: emptyList()
                    recipeAdapter = RecipeAdapter(recipes)
                    recipesRecyclerView.adapter = recipeAdapter
                } else {
                    Log.e("API", "Error en la respuesta: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                Log.e("API", "Error al obtener las recetas: ${t.message}")
            }
        })
    }

    private fun searchRecipes(query: String) {
        apiService.searchRecipes(query).enqueue(object : Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                if (response.isSuccessful) {
                    val recipes = response.body() ?: emptyList()
                    recipeAdapter = RecipeAdapter(recipes)
                    recipesRecyclerView.adapter = recipeAdapter
                } else {
                    Log.e("API", "Error en la respuesta: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                Log.e("API", "Error al buscar las recetas: ${t.message}")
            }
        })
    }
}
