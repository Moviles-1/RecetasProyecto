package com.example.retrofit_prueba.recetaPrueba

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

//SERVICIO
interface  RetrofitService {

    @GET ("complexSearch?")
    suspend fun  recipes(

        @Query("apiKey") apiKey: String,
      // @Query("query")query:String
        // @Path("type") type: String,
    )
}

//Implementacion del servicio
object RetrofitServiceFactory{
    fun makeRetrofitService(): RetrofitService{
        return Retrofit.Builder()
            .baseUrl("http://localhost:9000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(RetrofitService::class.java)
    }
}