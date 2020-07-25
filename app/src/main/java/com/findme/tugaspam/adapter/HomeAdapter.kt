package com.findme.tugaspam.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.findme.tugaspam.R
import com.findme.tugaspam.model.Barang
import kotlinx.android.synthetic.main.item_barang.view.*

class HomeAdapter(private var listBarang: MutableList<Barang>) :
    RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_barang,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = listBarang.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listBarang[position])
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val img: ImageView = itemView.img_item_barang
        private val tvJudul: TextView = itemView.tv_item_barang_judul
        private val tvLokasi: TextView = itemView.tv_item_barang_lokasi
        private val tvTanggal: TextView = itemView.tv_item_barang_tanggal

        fun bind(barang: Barang) {
            Glide.with(itemView)
                .load(Uri.parse(barang.gambar))
                .into(img)
//            img.setImageURI(Uri.parse(barang.gambar))
            tvJudul.text = barang.nama
            tvLokasi.text = barang.lokasi
            tvTanggal.text = barang.tanggal

        }
    }

}