package com.example.mycircuitfinder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.mycircuitfinder.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var database : DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.readdataBtn.setOnClickListener {

            val userName : String = binding.etusername.text.toString()
            if  (userName.isNotEmpty()){

                readData(userName)

            }else{

                Toast.makeText(this,"Please enter the circuit id",Toast.LENGTH_SHORT).show()

            }

        }

    }

    private fun readData(userName: String) {

        database = FirebaseDatabase.getInstance().getReference("circuits")
        database.child(userName).get().addOnSuccessListener {

            if (it.exists()){

                val firstname = it.child("pono").value
                val lastName = it.child("validtill").value

                val quatone = it.child("qone").value
                val quattwo = it.child("qtwo").value
                val quatthree = it.child("qthree").value
                val quatfour = it.child("qfour").value
                val age = it.child("arc").value.toString()
                var bal = age.toInt()
                var bal1 = bal - (bal/4)
                var bal2 = bal1 - (bal/4)
                var bal3 = bal2 - (bal/4)
                var bal4 = 0



                Toast.makeText(this,"Successfuly Read",Toast.LENGTH_SHORT).show()
                binding.etusername.text.clear()
                binding.tvFirstName.text = firstname.toString()
                binding.tvLastName.text = lastName.toString()

                if(quatone.toString().equals("")){
                    binding.tvAge.text = age.toString()
                }else if(quatone.toString().equals("paid") && quattwo.toString().equals("")){
                        binding.tvAge.text = bal1.toString()
                }else if(quatone.toString().equals("paid") && quattwo.toString().equals("paid") && quatthree.toString().equals("")){
                    binding.tvAge.text = bal2.toString()
                }else if(quatone.toString().equals("paid") && quattwo.toString().equals("paid") && quatthree.toString().equals("paid") && quatfour.toString().equals("")){
                    binding.tvAge.text = bal3.toString()
                }else if(quatone.toString().equals("paid") && quattwo.toString().equals("paid") && quatthree.toString().equals("paid") && quatfour.toString().equals("paid")){
                    binding.tvAge.text = bal4.toString()
                }




            }else{

                Toast.makeText(this,"circuit Doesn't Exist",Toast.LENGTH_SHORT).show()


            }

        }.addOnFailureListener{

            Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()


        }


    }
}


