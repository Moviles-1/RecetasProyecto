package com.example.retrofit_prueba.recetaPrueba

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface MyApiService {

    @GET("api")
    fun getRecetas(): Call<List<Post>>

    @POST("api")
    fun createReceta(@Body post: Post): Call<Post>

    @GET("api/search")
    fun searchRecipes(@Query("name") name: String): Call<List<Post>>
}
