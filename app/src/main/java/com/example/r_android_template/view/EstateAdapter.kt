package com.example.r_android_template.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.r_android_template.databinding.RowEstateBinding
import com.example.r_android_template.service.Estate

class EstateAdapter(private val estateList: List<Estate>,
                    private val viewModel: EstateListViewModel,
                    ) : RecyclerView.Adapter<EstateAdapter.EstateHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EstateHolder{
        val inflater = LayoutInflater.from(parent.context)
        val binding = RowEstateBinding.inflate(inflater, parent, false)

        return EstateHolder(binding, viewModel)
    }

    override fun onBindViewHolder(holder: EstateHolder, position: Int) =
        holder.bind(estateList[position])

    override fun getItemCount(): Int = estateList.size

    inner class EstateHolder(
        private val binding: RowEstateBinding,
        private val viewModel: EstateListViewModel,
                             ): RecyclerView.ViewHolder(binding.root){
        fun bind(estate: Estate){
            binding.estate = estate
            binding.estateHolder = this
        }

        fun changePickedEstate(estate: Estate){
            viewModel.changePickedEstate(estate)
        }
    }

}