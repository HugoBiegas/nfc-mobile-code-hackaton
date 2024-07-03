package com.example.nfc_mobile_code

import com.example.nfc_mobile_code.classapiservice.CodeRequest
import com.example.nfc_mobile_code.classapiservice.JwtResponse
import com.example.nfc_mobile_code.classapiservice.TokenRequest
import com.example.nfc_mobile_code.classapiservice.ValidationResponse
import com.example.nfc_mobile_code.classapiservice.SessionCloseRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("api/auth/validate-code")
    fun validateCode(@Body codeRequest: CodeRequest): Call<ValidationResponse>

    @POST("api/auth/authenticate")
    fun sendToken(@Body tokenRequest: TokenRequest): Call<JwtResponse>

    @POST("api/auth/close-session")
    fun closeSession(@Body sessionCloseRequest: SessionCloseRequest): Call<Void>
}
