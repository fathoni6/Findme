package com.findme.tugaspam.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.findme.tugaspam.R
import com.findme.tugaspam.model.Profile
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_update.*

class UpdateActivity : AppCompatActivity() {

    private lateinit var profile: Profile
    private lateinit var databaseReference: DatabaseReference
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

        profile = intent.getParcelableExtra("DATA")!!

        auth = FirebaseAuth.getInstance()

        databaseReference =
            FirebaseDatabase.getInstance().getReference(getString(R.string.data_profile))

        et_update_nim.setText(profile.nim)
        et_update_nama.setText(profile.nama)
        et_update_jurusan.setText(profile.jurusan)
        et_update_nohp.setText(profile.nohp)

        btn_update_submit.setOnClickListener {
            val nim = et_update_nim.text.toString()
            val nama = et_update_nama.text.toString()
            val jurusan = et_update_jurusan.text.toString()
            val nohp = et_update_nohp.text.toString()

            val profile = Profile( nim, nama, jurusan, nohp)
            databaseReference.child(auth.currentUser!!.uid).child("User").setValue(profile)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(
                            this,
                            "Berhasil update akun" + it.isSuccessful,
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(
                            this,
                            "Error membuat akun" + it.isSuccessful,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }
    }
}
