package com.findme.tugaspam.ui.fragment


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.findme.tugaspam.R
import com.findme.tugaspam.model.Profile
import com.findme.tugaspam.ui.MainActivity
import com.findme.tugaspam.ui.UpdateActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_account.*

/**
 * A simple [Fragment] subclass.
 */
class AccountFragment : Fragment() {

    private lateinit var databaseReference: DatabaseReference
    private lateinit var auth: FirebaseAuth
    private var profile: Profile? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_account, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()
        databaseReference =
            FirebaseDatabase.getInstance().getReference(getString(R.string.data_profile))
                .child(auth.currentUser!!.uid)

        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                Toast.makeText(context, "Database Error", Toast.LENGTH_SHORT).show()
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (data in dataSnapshot.children) {
                        profile = data.getValue(Profile::class.java)
                        if (profile != null) {
                            tv_account_nim.text = profile?.nim
                            tv_account_nama.text = profile?.nama
                            tv_account_jurusan.text = profile?.jurusan
                            tv_account_nomerhp.text = profile?.nohp
                        }
                    }
                }
            }
        })

        btn_account_logout.setOnClickListener {
            auth.signOut()
            startActivity(Intent(context, MainActivity::class.java))
            activity?.finish()
        }

        btn_account_update.setOnClickListener {
            val intent = Intent(context, UpdateActivity::class.java)
            intent.putExtra("DATA", profile)
            startActivity(intent)
        }
    }

}
