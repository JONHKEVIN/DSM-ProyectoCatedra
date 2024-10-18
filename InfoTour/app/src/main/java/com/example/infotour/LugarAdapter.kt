package com.example.infotour

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class LugarAdapter(
    private val lugares: List<Lugar>,
    private val onMapClick: (Lugar) -> Unit,
    private val onDirectionsClick: (Lugar) -> Unit
) : RecyclerView.Adapter<LugarAdapter.LugarViewHolder>() {

    class LugarViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtNombre: TextView = view.findViewById(R.id.txtNombre)
        val txtDescripcion: TextView = view.findViewById(R.id.txtDescripcion)
        val imgLugar: ImageView = view.findViewById(R.id.imgLugar)
        val btnVerMas: Button = view.findViewById(R.id.btnVerMas)
        val btnObtenerIndicaciones: Button = view.findViewById(R.id.btnObtenerIndicaciones)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LugarViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_lugar, parent, false)
        return LugarViewHolder(view)
    }

    override fun onBindViewHolder(holder: LugarViewHolder, position: Int) {
        val lugar = lugares[position]

        holder.txtNombre.text = lugar.name

        // Cargar la imagen desde la URL usando Glide
        Glide.with(holder.imgLugar.context)
            .load(lugar.image) // Aquí se carga la imagen desde la URL
            .into(holder.imgLugar)

        holder.txtDescripcion.text = lugar.description // Usar "description" en lugar de "descripcion"

        holder.btnVerMas.setOnClickListener {
            // Mostrar u ocultar la descripción
            if (holder.txtDescripcion.visibility == View.VISIBLE) {
                holder.txtDescripcion.visibility = View.GONE
                holder.btnVerMas.text = "Ver más"
            } else {
                holder.txtDescripcion.visibility = View.VISIBLE
                holder.btnVerMas.text = "Ver menos"
            }
        }

        holder.btnObtenerIndicaciones.setOnClickListener {
            onDirectionsClick(lugar)
        }
    }

    override fun getItemCount() = lugares.size
}
