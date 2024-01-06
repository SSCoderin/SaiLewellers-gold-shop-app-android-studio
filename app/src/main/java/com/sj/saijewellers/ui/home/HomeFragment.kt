package com.sj.saijewellers.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.RecentlyAdd.NewAdapter
import com.RecentlyAdd.NewData
import com.UploadDesign.UploadDesignActivity
import com.Users.Contact
import com.Users.OwnDesign
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.jewellery.Bangles
import com.jewellery.EarRing
import com.jewellery.GoldMani
import com.jewellery.Mangalsutra
import com.jewellery.NeckChain
import com.jewellery.Necklace
import com.jewellery.NoseRing
import com.jewellery.Ring
import com.jewellery.ToeRing
import com.jewellery.WristChain
import com.jewellery.kada
import com.owner.RingActivity
import com.sj.saijewellers.R
import com.sj.saijewellers.databinding.FragmentHomeBinding
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: NewAdapter

    private lateinit var textViewGoldPrice: TextView
    private lateinit var textViewSilverPrice: TextView

    private val databaseReference = FirebaseDatabase.getInstance().reference.child("prices")
    private val recyclerViewDataList = listOf(
        YourDataModel(R.drawable.a1, "Item 1", "Sai Jewellers Gold Shop", "$10"),
        YourDataModel(R.drawable.a2, "Item 2", "Sai Jewellers Gold Shop", "$20"),
        YourDataModel(R.drawable.a3, "Item 2", "Sai Jewellers Gold Shop", "$20"),
        YourDataModel(R.drawable.a4, "Item 2", "Sai Jewellers Gold Shop", "$20"),
        YourDataModel(R.drawable.a5, "Item 2", "Sai Jewellers Gold Shop", "$20"),
        YourDataModel(R.drawable.a6, "Item 2", "Sai Jewellers Gold Shop", "$20"),
        YourDataModel(R.drawable.a7, "Item 2", "Sai Jewellers Gold Shop", "$20"),
        YourDataModel(R.drawable.a8, "Item 2", "Sai Jewellers Gold Shop", "$20"),
        YourDataModel(R.drawable.a9, "Item 2", "Sai Jewellers Gold Shop", "$20"),
        YourDataModel(R.drawable.a10, "Item 2", "Sai Jewellers Gold Shop", "$20"),
        YourDataModel(R.drawable.a11, "Item 2", "Sai Jewellers Gold Shop", "$20"),
        YourDataModel(R.drawable.a12, "Item 2", "Sai Jewellers Gold Shop", "$20"),
        YourDataModel(R.drawable.a13, "Item 2", "Sai Jewellers Gold Shop", "$20"),
        YourDataModel(R.drawable.a15, "Item 2", "Sai Jewellers Gold Shop", "$20"),
        YourDataModel(R.drawable.a16, "Item 2", "Sai Jewellers Gold Shop", "$20"),


        // Add more items as needed
    )
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Find the ImageView using ViewBinding
        val imageView: ImageView = binding.root.findViewById(R.id.RIng)
        imageView.setOnClickListener {
            // Perform the action when the image is clicked (e.g., navigate to the Ring Activity)
            val intent = Intent(activity, RingActivity::class.java)
            startActivity(intent)
        }
        val imageView1: ImageView = binding.root.findViewById(R.id.Mangalsutra)
        imageView1.setOnClickListener {
            // Perform the action when the image is clicked (e.g., navigate to the Ring Activity)
            val intent = Intent(activity, Mangalsutra::class.java)
            startActivity(intent)
        }
        val imageView2: ImageView = binding.root.findViewById(R.id.EarRing)
        imageView2.setOnClickListener {
            // Perform the action when the image is clicked (e.g., navigate to the Ring Activity)
            val intent = Intent(activity, EarRing::class.java)
            startActivity(intent)
        }
        val imageView3: ImageView = binding.root.findViewById(R.id.Anklet)
        imageView3.setOnClickListener {
            // Perform the action when the image is clicked (e.g., navigate to the Ring Activity)
            val intent = Intent(activity, Ring::class.java)
            startActivity(intent)
        }
        val imageView4: ImageView = binding.root.findViewById(R.id.Necklace)
        imageView4.setOnClickListener {
            // Perform the action when the image is clicked (e.g., navigate to the Ring Activity)
            val intent = Intent(activity, Necklace::class.java)
            startActivity(intent)
        }
        val imageView5: ImageView = binding.root.findViewById(R.id.NoseRing)
        imageView5.setOnClickListener {
            // Perform the action when the image is clicked (e.g., navigate to the Ring Activity)
            val intent = Intent(activity, NoseRing::class.java)
            startActivity(intent)
        }
        val imageView6: ImageView = binding.root.findViewById(R.id.Bangles)
        imageView6.setOnClickListener {
            // Perform the action when the image is clicked (e.g., navigate to the Ring Activity)
            val intent = Intent(activity, Bangles::class.java)
            startActivity(intent)
        }
        val imageView7: ImageView = binding.root.findViewById(R.id.Kada)
        imageView7.setOnClickListener {
            // Perform the action when the image is clicked (e.g., navigate to the Ring Activity)
            val intent = Intent(activity, kada::class.java)
            startActivity(intent)
        }
        val imageView8: ImageView = binding.root.findViewById(R.id.WristChain)
        imageView8.setOnClickListener {
            // Perform the action when the image is clicked (e.g., navigate to the Ring Activity)
            val intent = Intent(activity, WristChain::class.java)
            startActivity(intent)
        }
        val imageView9: ImageView = binding.root.findViewById(R.id.NeckChain)
        imageView9.setOnClickListener {
            // Perform the action when the image is clicked (e.g., navigate to the Ring Activity)
            val intent = Intent(activity, NeckChain::class.java)
            startActivity(intent)
        }
        val imageView10: ImageView = binding.root.findViewById(R.id.ToeRing)
        imageView10.setOnClickListener {
            // Perform the action when the image is clicked (e.g., navigate to the Ring Activity)
            val intent = Intent(activity, ToeRing::class.java)
            startActivity(intent)
        }
        val imageView11: ImageView = binding.root.findViewById(R.id.GoldMani)
        imageView11.setOnClickListener {
            // Perform the action when the image is clicked (e.g., navigate to the Ring Activity)
            val intent = Intent(activity, GoldMani::class.java)
            startActivity(intent)
        }
        val imageView12: ImageView = binding.root.findViewById(R.id.Contact)
        imageView12.setOnClickListener {
            // Perform the action when the image is clicked (e.g., navigate to the Ring Activity)
            val intent = Intent(activity, Contact::class.java)
            startActivity(intent)
        }

        val imageView13: ImageView = binding.root.findViewById(R.id.owndesign)
        imageView13.setOnClickListener {
            // Perform the action when the image is clicked (e.g., navigate to the Ring Activity)
            val intent = Intent(activity, OwnDesign::class.java)
            startActivity(intent)
        }
        val imageView14: ImageView = binding.root.findViewById(R.id.uploadDesign)
        imageView14.setOnClickListener {
            // Perform the action when the image is clicked (e.g., navigate to the Ring Activity)
            val intent = Intent(activity, UploadDesignActivity::class.java)
            startActivity(intent)
        }
        textViewGoldPrice = root.findViewById(R.id.textViewGoldPrice)
        textViewSilverPrice = root.findViewById(R.id.textViewSilverPrice)

        // Retrieve prices from Firebase
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val goldPrice = snapshot.child("gold").getValue(Double::class.java)
                    val silverPrice = snapshot.child("silver").getValue(Double::class.java)

                    // Display prices in TextViews
                    textViewGoldPrice.text = "Gold Price: $goldPrice"
                    textViewSilverPrice.text = "Silver Price: $silverPrice"

                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
            }
        })
        recyclerView = root.findViewById(R.id.recyclerView2)
        recyclerView.layoutManager = LinearLayoutManager(activity)

        // Initialize adapter
        adapter = NewAdapter()
        recyclerView.adapter = adapter

        // Fetch data from Firestore
        fetchDataFromFirestore()

        return root


    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize RecyclerView
        val recyclerView: RecyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Create and set the adapter with the dummy data
        val adapter = CustomAdapter(recyclerViewDataList)
        recyclerView.adapter = adapter
    }
    private fun fetchDataFromFirestore() {
        val db = FirebaseFirestore.getInstance()
        db.collection("images")
            .get()
            .addOnSuccessListener { result ->
                val dataList = mutableListOf<NewData>()
                for (document in result) {
                    val newData = document.toObject(NewData::class.java)
                    dataList.add(newData)
                }
                adapter.setData(dataList)
            }
            .addOnFailureListener { exception ->
                // Handle errors
            }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}