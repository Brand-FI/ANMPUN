package com.ubayadev.anmpun.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.ubayadev.anmpun.R
import com.ubayadev.anmpun.databinding.FragmentNewHabitBinding
import com.ubayadev.anmpun.model.Habit
import com.ubayadev.anmpun.viewmodel.ListViewModel

class NewHabitFragment : Fragment() {
    private lateinit var viewModel: ListViewModel
    private lateinit var binding: FragmentNewHabitBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewHabitBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)

        val items = arrayOf("💧", "🏃‍♀️", "💪", "📚", "🛏️", "💤",
                            "🍎", "🥗", "⚽", "🏀", "🎾", "🏸",
                            "🧰", "📊", "🛍️", "🛒", "🧹", "💊", "🙏")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, items)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spnIcon.adapter = adapter

        binding.btnCreate.setOnClickListener{
            var filled = ""
            val name = binding.txtHabitName.text.toString()
            val desc = binding.txtShortDescription.text.toString()
            val goal = binding.txtGoal.text.toString()
            val unit = binding.txtUnit.text.toString()
            val icon = binding.spnIcon.selectedItem?.toString()?: "Default"

            if(name.isEmpty())
            {
                filled = "Nama habit belum diisi."
            }
            if(desc.isEmpty())
            {
                filled = "Deskripsi pendek untuk habit belum diisi."
            }
            if(goal.isEmpty())
            {
                filled = "Goal habit belum diisi."
            }
            if(icon.isEmpty())
            {
                filled = "Icon habit belum dipilih."
            }

            if(filled.isEmpty()) //nama habit, deskripsi, goals, dan icon
            {
                val newHabit = Habit(name, desc, 0, Integer.parseInt(goal), unit, icon)
                viewModel.add(newHabit)
                viewModel.refresh()
                val action = NewHabitFragmentDirections.actionNewToDashboard()
                it.findNavController().navigate(action)
            } else {
                Toast.makeText(requireContext(), filled, Toast.LENGTH_SHORT).show()
            }
        }
    }
}