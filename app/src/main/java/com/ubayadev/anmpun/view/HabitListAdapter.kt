package com.ubayadev.anmpun.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ubayadev.anmpun.databinding.HabitListItemBinding
import com.ubayadev.anmpun.model.Habit

class HabitListAdapter(val habitList: ArrayList<Habit>) :
    RecyclerView.Adapter<HabitListAdapter.HabitViewHolder>() {
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
        val habit = habitList[position]
        holder.binding.txtTitle.text = habit.name
        holder.binding.txtDesc.text = habit.description
        holder.binding.txtProgress.text = "${habit.currentProgress} / ${habit.goal} ${habit.unit}"
        holder.binding.progressBar.max = habit.goal
        holder.binding.progressBar.progress = habit.currentProgress

        if (habit.currentProgress >= habit.goal) {
            holder.binding.txtStatus.text = "Completed"
            holder.binding.txtStatus.setChipBackgroundColorResource(android.R.color.holo_green_light)
        } else {
            holder.binding.txtStatus.text = "In Progress"
            holder.binding.txtStatus.setChipBackgroundColorResource(android.R.color.darker_gray)
        }

        holder.binding.btnAdd.setOnClickListener {
            if (habit.currentProgress < habit.goal) {
                habit.currentProgress++
                notifyItemChanged(position)
            }
        }
        holder.binding.btnSub.setOnClickListener {
            if (habit.currentProgress > 0) {
                habit.currentProgress--
                notifyItemChanged(position)
            }
        }

    }

    override fun getItemCount(): Int {
        return habitList.size

    }

    fun updateHabitList(newHabitList: ArrayList<Habit>) {
        habitList.clear()
        habitList.addAll(newHabitList)
        notifyDataSetChanged()
    }

}
