package com.example.nfc_mobile_code

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.nfc_mobile_code.classapiservice.CodeRequest
import com.example.nfc_mobile_code.classapiservice.ValidationResponse

class MainActivity : AppCompatActivity() {

    private lateinit var codeInput: EditText
    private lateinit var validateButton: Button
    private lateinit var apiService: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        codeInput = findViewById(R.id.codeInput)
        validateButton = findViewById(R.id.validateButton)

        // Initialize Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("http://172.20.10.6:5000/") // Ajuste le port si nécessaire
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apiService = retrofit.create(ApiService::class.java)

        validateButton.setOnClickListener {
            val code = codeInput.text.toString()
            if (code.isNotEmpty()) {
                validateCode(code)
            } else {
                Toast.makeText(this, "Please enter a code", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validateCode(code: String) {
        val call = apiService.validateCode(CodeRequest(code))
        call.enqueue(object : Callback<ValidationResponse> {
            override fun onResponse(call: Call<ValidationResponse>, response: Response<ValidationResponse>) {
                if (response.isSuccessful) {

                    val validationResponse = response.body()
                    if (validationResponse?.valid == true) {
                        goToNfcScanActivity(code) // Affiche la modale avant de lancer l'activité
                    } else {
                        Log.e("API_RESPONSE", "Invalid code: ${response.body()}")
                        showInvalidCodeNotification()
                    }
                } else {
                    Log.e("API_RESPONSE", "Response not successful: ${response.code()} - ${response.message()}")
                    showInvalidCodeNotification()
                }
            }

            override fun onFailure(call: Call<ValidationResponse>, t: Throwable) {
                Log.e("API_ERROR", "API call failed: ${t.message}", t)
                Toast.makeText(this@MainActivity, "API call failed: ${t.message}", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun goToNfcScanActivity(code: String) {
        val intent = Intent(this, NfcScanActivity::class.java).apply {
            putExtra("EXTRA_CODE", code) // Pass the code to the next activity
        }
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

    private fun showInvalidCodeNotification() {
        Toast.makeText(this, "Invalid code", Toast.LENGTH_SHORT).show()
    }
}
