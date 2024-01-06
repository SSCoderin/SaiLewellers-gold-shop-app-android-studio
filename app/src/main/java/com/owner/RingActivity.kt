// RingActivity.kt
package com.owner

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.sj.saijewellers.databinding.ActivityRingBinding

class RingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRingBinding
    private lateinit var databaseReference: DatabaseReference
    private lateinit var ringAdapter: ImageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = "Ring"

        databaseReference = FirebaseDatabase.getInstance().reference.child("images").child("Ring")

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        ringAdapter = ImageAdapter()
        binding.recyclerView.adapter = ringAdapter

        // Fetch images from the "Ring" category
        fetchRingImages()
    }

    private fun fetchRingImages() {
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val images = mutableListOf<ImageDetails>()
                for (childSnapshot in snapshot.children) {
                    val imageDetail = childSnapshot.getValue(ImageDetails::class.java)
                    imageDetail?.let {
                        images.add(it)
                    }
                }
                ringAdapter.submitList(images)
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
                Toast.makeText(this@RingActivity, "Error fetching Ring images", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
