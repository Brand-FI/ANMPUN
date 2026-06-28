package com.ubayadev.anmpun.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ubayadev.anmpun.databinding.HabitListItemBinding
import com.ubayadev.anmpun.model.Habit
import com.ubayadev.anmpun.viewmodel.ListViewModel

class HabitListAdapter(val habitList: ArrayList<Habit>, val listener: HabitListListener) :
    RecyclerView.Adapter<HabitListAdapter.HabitViewHolder>(){
    class HabitViewHolder(var binding: HabitListItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HabitViewHolder {
        val binding = HabitListItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return HabitViewHolder(binding)

    }

    override fun onBindViewHolder(
        holder: HabitViewHolder,
        position: Int
    ) {
        holder.binding.habit = habitList[position]
        holder.binding.listener = listener
    }

    override fun getItemCount(): Int {
        return habitList.size

    }

    fun updateHabitList(newHabitList: List<Habit>) {
        habitList.clear()
        habitList.addAll(newHabitList)
        notifyDataSetChanged()
    }

}
