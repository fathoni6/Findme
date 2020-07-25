package com.findme.tugaspam.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.findme.tugaspam.R
import com.findme.tugaspam.model.Profile
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {

    private lateinit var databaseReference: DatabaseReference
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        auth = FirebaseAuth.getInstance()

        databaseReference =
            FirebaseDatabase.getInstance().getReference(getString(R.string.data_profile))

        btn_signup_submit.setOnClickListener {
            val email = et_signup_email.text.toString()
            val nim = et_signup_nim.text.toString()
            val nama = et_signup_nama.text.toString()
            val jurusan = et_signup_jurusan.text.toString()
            val password = et_signup_password.text.toString()
            val nohp = et_signup_nohp.text.toString()


            val profile = Profile(nim, nama, jurusan, nohp)

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(
                            this@SignUpActivity,
                            "Berhasil membuat akun" + it.isSuccessful,
                            Toast.LENGTH_SHORT
                        ).show()

                        databaseReference.child(auth.currentUser!!.uid).child("User")
                            .setValue(profile)
                    } else {
                        Toast.makeText(
                            this@SignUpActivity,
                            "Error membuat akun" + it.isSuccessful,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }
    }
}
