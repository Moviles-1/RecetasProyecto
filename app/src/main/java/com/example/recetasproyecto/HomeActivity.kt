package com.example.recetasproyecto

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

enum class ProviderType {
    BASIC
}

class HomeActivity : AppCompatActivity() {

    private lateinit var emailTextView: TextView
    private lateinit var providerTextView: TextView
    private lateinit var logOutButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Inicializar las vistas correctamente
        emailTextView = findViewById(R.id.emailTextView)
        providerTextView = findViewById(R.id.providerTextView)
        logOutButton = findViewById(R.id.logOutbutton) // Corregir el ID aquí

        // Recuperar los datos del Intent
        val bundle = intent.extras
        val email = bundle?.getString("email")
        val provider = bundle?.getString("provider")

        // Configurar la vista con los datos
        setup(email ?: "", provider ?: "")
    }

    private fun setup(email: String, provider: String) {
        title = "Inicio"
        emailTextView.text = email
        providerTextView.text = provider

        logOutButton.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            onBackPressed()
        }
    }
}
