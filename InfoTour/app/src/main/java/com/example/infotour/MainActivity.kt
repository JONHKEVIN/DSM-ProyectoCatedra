package com.example.infotour

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializa Firebase Auth
        auth = FirebaseAuth.getInstance()

        val btnLogout: Button = findViewById(R.id.btnLogout)
        btnLogout.setOnClickListener {
            logout()
        }
    }

    private fun logout() {
        // Cerrar sesión
        auth.signOut()

        // Redirigir a la pantalla de login
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish() // Opcional: cerrar la actividad actual
    }
}
