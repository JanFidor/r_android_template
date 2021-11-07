package com.example.r_android_template.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.r_android_template.databinding.FragmentEstateBinding
import com.example.r_android_template.service.Service

class EstateListFragment : Fragment() {
    private lateinit var binding: FragmentEstateBinding
    private val viewModel: EstateListViewModel by activityViewModels()
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle? ): View {
        binding = FragmentEstateBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.fragment = this

        sharedPreferences = binding.root.context.getSharedPreferences("ID", Context.MODE_PRIVATE)
        val startingID = sharedPreferences.getInt("ID", -1)
        val startingEstate = Service.getEstate(startingID)
        startingEstate?.let{viewModel.changePickedEstate(startingEstate)}

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(binding.root.context)
        recyclerView.adapter = EstateAdapter(Service.estateDeserialized.toList(), viewModel)

        viewModel.pickedEstate.observe(viewLifecycleOwner){estate ->
            with(sharedPreferences.edit()){
                estate?.let{
                    putInt("ID", it.id)
                    apply()
                }
            }
        }
    }

    fun showToast(){
        val estateNo = viewModel.pickedEstate.value?.estateNo
        val context = binding.root.context.applicationContext
        Toast.makeText(context, estateNo, Toast.LENGTH_SHORT).show()
    }
}