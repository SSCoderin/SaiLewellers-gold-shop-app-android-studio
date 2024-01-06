package com.owner

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.Price.PriceUploadActivity
import com.RecentlyAdd.NewUpload
import com.UploadDesign.PeopleDesignActivity
import com.sj.saijewellers.databinding.ActivityOwnerBinding

class owner : AppCompatActivity() {

    private lateinit var binding: ActivityOwnerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOwnerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val btnOpenUploadActivity = binding.U

        btnOpenUploadActivity.setOnClickListener {
            // Handle button click, navigate to UploadActivity
            val intent = Intent(this, UploadActivity::class.java)
            startActivity(intent)
        }

        val PriceU = binding.pricebutton

        PriceU.setOnClickListener {
            // Handle button click, navigate to UploadActivity
            val intent = Intent(this, PriceUploadActivity::class.java)
            startActivity(intent)
        }

        val uploadD = binding.pbutton

        uploadD.setOnClickListener {
            // Handle button click, navigate to UploadActivity
            val intent = Intent(this, PeopleDesignActivity::class.java)
            startActivity(intent)
        }
        val NewArrival = binding.newupload

        NewArrival.setOnClickListener {
            // Handle button click, navigate to UploadActivity
            val intent = Intent(this, NewUpload::class.java)
            startActivity(intent)
        }
    }
}

