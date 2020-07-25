package com.findme.tugaspam.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.findme.tugaspam.R
import com.findme.tugaspam.adapter.HomeAdapter
import com.findme.tugaspam.model.Barang
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private lateinit var adapterBarang: HomeAdapter
    private lateinit var databaseReference: DatabaseReference
    private var listTask: MutableList<Barang> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        databaseReference =
            FirebaseDatabase.getInstance().getReference("BARANG")
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                Toast.makeText(context, "Database Error", Toast.LENGTH_SHORT).show()
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (data in dataSnapshot.children) {
                        val task = data.getValue(Barang::class.java)
                        if (task != null) {
                            listTask.add(task)
                        }
                    }
                    adapterBarang.notifyDataSetChanged()
                }
            }
        })

        showList()

        btn_home_cari.setOnClickListener {
            val id = databaseReference.push().key.toString()
            val cari = et_home_cari.text.toString()

            val query = databaseReference.child("")
        }
    }

    override fun onResume() {
        super.onResume()
        showList()
        Log.e("LIST", "RESUME")
    }

    private fun showList() {
        adapterBarang = HomeAdapter(listTask)
        rv_list_barang.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = adapterBarang
        }
    }
}
