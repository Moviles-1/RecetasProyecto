package com.example.recetasproyecto

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.recetasproyecto.databinding.ActivityMainBinding
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth

class AuthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // Analytics Event
        val analytics = FirebaseAnalytics.getInstance(this)
        val bundle = Bundle()
        bundle.putString("message", "Integracion de Firebase completa")
        analytics.logEvent("InitScreen", bundle)

        //setup
        setup()
    }

    private fun setup(){

        title = "Autenticación"

       binding.signUpButton.setOnClickListener{

           val email = binding.emailEditText.text.toString()//Convierte Email a tipo String
           val password = binding.passwordEditText.text.toString()//Convierte Password  a tipo String

           if (email.isNotEmpty() && password.isNotEmpty()) {
               FirebaseAuth.getInstance()
                   .createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                       task ->

                       if (task.isSuccessful) {
                           showHome(task.result?.user?.email ?: "", ProviderType.BASIC)
                       } else {
                                showAlert()
                       }
                   }
           }
       }

        binding.loginButton.setOnClickListener{

            val email = binding.emailEditText.text.toString()//Convierte Email a tipo String
            val password = binding.passwordEditText.text.toString()//Convierte Password  a tipo String

            if (email.isNotEmpty() && password.isNotEmpty()) {
                FirebaseAuth.getInstance()
                    .signInWithEmailAndPassword(email, password).addOnCompleteListener {
                            task ->

                        if (task.isSuccessful) {
                            showHome(task.result?.user?.email ?: "", ProviderType.BASIC)
                        } else {
                            showAlert()
                        }
                    }

            }

        }

    }

    //Genera una alerta de que paso algo
    private fun showAlert(){

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error autenticando al usuario")
        builder.setPositiveButton("Aceptar",   null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun showHome(email: String, provider: ProviderType){

            val homeIntent = Intent(this, HomeActivity::class.java).apply {
                    putExtra("email", email)
                    putExtra("provider",provider.name)
            }
        startActivity(homeIntent)
    }

}