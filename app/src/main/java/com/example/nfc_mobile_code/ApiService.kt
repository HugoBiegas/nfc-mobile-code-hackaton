// ApiService.kt
package com.example.nfc_mobile_code

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

// Interface définissant les appels API que l'application peut effectuer
interface ApiService {

    // Définition d'un appel GET pour valider un code
    // Le code est passé en tant que paramètre de requête
    // La réponse est encapsulée dans un objet Call de type ValidationResponse
    @GET("validateCode")
    fun validateCode(@Query("code") code: String): Call<ValidationResponse>

    // Définition d'un appel POST pour envoyer un jeton (token)
    // Le jeton est passé dans le corps de la requête
    // La réponse est encapsulée dans un objet Call de type JwtResponse
    @POST("/endpoint")
    fun sendToken(@Body token: String): Call<JwtResponse>
}

