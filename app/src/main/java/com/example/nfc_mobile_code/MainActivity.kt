package com.example.nfc_mobile_code

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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
            .baseUrl("https://api.yourservice.com/") // api Node.js
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
        val call = apiService.validateCode(code)
        call.enqueue(object : Callback<ValidationResponse> {
            override fun onResponse(call: Call<ValidationResponse>, response: Response<ValidationResponse>) {
                if (response.isSuccessful && response.body()?.isValid == true) {
                    showNfcDataModal() // Affiche la modale avant de lancer l'activité
                } else {
                    showInvalidCodeNotification()
                }
            }

            override fun onFailure(call: Call<ValidationResponse>, t: Throwable) {
                goToNfcScanActivity()
                //Toast.makeText(this@MainActivity, "API call failed", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun showNfcDataModal() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("NFC Data")
        builder.setMessage("NFC Data will appear here")
        builder.setPositiveButton("OK") { _, _ ->
            goToNfcScanActivity()
        }
        builder.create().show()
    }

    private fun goToNfcScanActivity() {
        val intent = Intent(this, NfcScanActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

    private fun showInvalidCodeNotification() {
        Toast.makeText(this, "Invalid code", Toast.LENGTH_SHORT).show()
    }
}
