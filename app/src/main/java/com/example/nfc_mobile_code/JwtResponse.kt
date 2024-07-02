// JwtResponse.kt
package com.example.nfc_mobile_code

// Déclaration de la classe de données JwtResponse
data class JwtResponse(
    // Champ indiquant si l'opération a été un succès ou non
    val success: Boolean,

    // Message retourné par l'API, pour donner plus de détails sur le résultat
    val message: String
)
