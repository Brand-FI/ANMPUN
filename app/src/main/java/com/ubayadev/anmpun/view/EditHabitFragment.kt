package com.ubayadev.anmpun.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.ubayadev.anmpun.R
import com.ubayadev.anmpun.databinding.FragmentEditHabitBinding
import com.ubayadev.anmpun.model.Habit
import com.ubayadev.anmpun.viewmodel.NewHabitViewModel

class EditHabitFragment : Fragment(), EditHabitListener {
    private lateinit var viewModel: NewHabitViewModel
    private lateinit var binding: FragmentEditHabitBinding
    private lateinit var iconAdapter: ArrayAdapter<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditHabitBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(NewHabitViewModel::class.java)

        val items = arrayOf("💧", "🏃‍♀️", "💪", "📚", "🛏️", "💤",
                            "🍎", "🥗", "⚽", "🏀", "🎾", "🏸",
                            "🧰", "📊", "🛍️", "🛒", "🧹", "💊", "🙏")

        iconAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, items)
        iconAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spnIcon.adapter = iconAdapter
        binding.txtTitle2.text = "Edit Habit"
        binding.btnCreate.text = "Save Changes"
        val uuid = EditHabitFragmentArgs.fromBundle(requireArguments()).uuid
        viewModel.fetch(uuid)
        observeViewModel()

        binding.listener = this

    }

    private fun observeViewModel() {
        viewModel.habitLD.observe(viewLifecycleOwner, Observer{
            binding.habit = it
            val position = iconAdapter.getPosition(it.iconUrl)
            binding.spnIcon.setSelection(position)
        })
    }

    override fun onClick(v: View) {
        binding.habit!!.goal = binding.txtGoal.text.toString().toInt()
        binding.habit!!.iconUrl = binding.spnIcon.selectedItem.toString()
        binding.habit!!.goal = binding.txtGoal.text.toString().toInt()
        viewModel.update(binding.habit!!)

        Toast.makeText(context, "Habit Updated", Toast.LENGTH_SHORT).show()
        v.findNavController().popBackStack()
    }
}