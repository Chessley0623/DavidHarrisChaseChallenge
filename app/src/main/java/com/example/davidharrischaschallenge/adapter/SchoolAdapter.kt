package com.example.davidharrischaschallenge.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.davidharrischaschallenge.databinding.RowItemBinding
import com.example.davidharrischaschallenge.model.SchoolModel

//using recycler view for the list of data
//the adapter lets us bridge the data source and UI components
class SchoolAdapter(val listener: (Int) -> Unit) : RecyclerView.Adapter<SchoolAdapter.SchoolViewHolder>(){


    private var dataList: List<SchoolModel> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SchoolViewHolder =
        SchoolViewHolder(RowItemBinding.inflate(LayoutInflater.from(parent.context)))

    override fun onBindViewHolder(holder: SchoolViewHolder, position: Int) {
        val school = dataList.get(position)
        holder.bind.schoolModel = school
        holder.bind.rowLayout.setOnClickListener{listener(position)}
    }

    override fun getItemCount(): Int = dataList.size

    class SchoolViewHolder(binding: RowItemBinding):
        RecyclerView.ViewHolder(binding.root) {val bind = binding}

    fun loadData(data: List<SchoolModel>){
        dataList = data
        notifyDataSetChanged()
    }
}