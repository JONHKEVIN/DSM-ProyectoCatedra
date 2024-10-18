package com.example.infotour

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TurismoActivity : AppCompatActivity() {

    private lateinit var lugarAdapter: LugarAdapter
    private lateinit var rvLugares: RecyclerView


    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_turismo)


        auth = FirebaseAuth.getInstance()

        rvLugares = findViewById(R.id.rvLugares)
        rvLugares.layoutManager = LinearLayoutManager(this)


        val btnLogout: Button = findViewById(R.id.btnLogout)
        val btnRegresar: Button = findViewById(R.id.btnRegresar)

        btnLogout.setOnClickListener {
            logoutUser()
        }

        btnRegresar.setOnClickListener {
            finish()
        }

        loadLugares()
    }

    private fun loadLugares() {
        val apiService = RetrofitClient.apiService
        apiService.getLugares().enqueue(object : Callback<List<Lugar>> {
            override fun onResponse(call: Call<List<Lugar>>, response: Response<List<Lugar>>) {
                if (response.isSuccessful) {
                    val lugares = response.body() ?: emptyList()
                    lugarAdapter = LugarAdapter(lugares, { lugar ->
                        openMapForLugar(lugar)
                    }, { lugar ->
                        getDirectionsForLugar(lugar)
                    })
                    rvLugares.adapter = lugarAdapter
                } else {
                    Toast.makeText(this@TurismoActivity, "Error al cargar los datos", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<Lugar>>, t: Throwable) {
                Toast.makeText(this@TurismoActivity, "Fallo en la conexión", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun openMapForLugar(lugar: Lugar) {
        Toast.makeText(this, "Abrir mapa para: ${lugar.name}", Toast.LENGTH_SHORT).show()

    }

    private fun getDirectionsForLugar(lugar: Lugar) {
        val locationName = lugar.name
        val uri = Uri.parse("geo:0,0?q=$locationName")
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }

    private fun logoutUser() {

        auth.signOut()
        Toast.makeText(this, "Sesión cerrada", Toast.LENGTH_SHORT).show()


        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}
