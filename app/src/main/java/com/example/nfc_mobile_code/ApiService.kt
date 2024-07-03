package com.example.nfc_mobile_code

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("api/auth/validate-code")
    fun validateCode(@Body codeRequest: CodeRequest): Call<ValidationResponse>

    @POST("api/auth/authenticate")
    fun sendToken(@Body tokenRequest: TokenRequest): Call<JwtResponse>
}
