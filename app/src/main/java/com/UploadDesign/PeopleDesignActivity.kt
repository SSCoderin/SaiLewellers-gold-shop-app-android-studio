package com.UploadDesign

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.*
import com.sj.saijewellers.databinding.ActivityPeopleDesignBinding

class PeopleDesignActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPeopleDesignBinding
    private lateinit var designAdapter: DesignAdapter
    private val designList: MutableList<Design> = mutableListOf()

    private val databaseReference = FirebaseDatabase.getInstance().reference.child("designs")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPeopleDesignBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerViewDesigns.layoutManager = LinearLayoutManager(this)
        designAdapter = DesignAdapter(this, designList)
        binding.recyclerViewDesigns.adapter = designAdapter

        // Retrieve designs from Firebase
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                designList.clear()
                for (postSnapshot in snapshot.children) {
                    val design = postSnapshot.getValue(Design::class.java)
                    design?.let { designList.add(it) }
                }
                designAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
            }
        })
    }
}
