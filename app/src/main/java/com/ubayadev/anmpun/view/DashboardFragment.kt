package com.ubayadev.anmpun.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ubayadev.anmpun.R
import com.ubayadev.anmpun.databinding.FragmentDashboardBinding
import com.ubayadev.anmpun.viewmodel.ListViewModel

class DashboardFragment : Fragment() {

    private lateinit var viewModel: ListViewModel
    private val habitListAdapter = HabitListAdapter(arrayListOf())
    private lateinit var binding: FragmentDashboardBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)

        binding.recViewHabit.layoutManager = LinearLayoutManager(context)
        binding.recViewHabit.adapter = habitListAdapter

        observeViewModel()

        binding.refreshLayout.setOnRefreshListener {
            binding.recViewHabit.visibility = View.GONE
            binding.txtError.visibility = View.GONE
            binding.progressLoad.visibility = View.VISIBLE
            viewModel.refresh()
            binding.refreshLayout.isRefreshing = false
        }
        binding.fabNewHabit.setOnClickListener {
            val action = DashboardFragmentDirections.actionNewHabitFragment()
            it.findNavController().navigate(action)
        }

    }

    fun observeViewModel() {
        viewModel.habitsLD.observe(viewLifecycleOwner, Observer {
            Log.d("CHECK_DATA", "Data diterima: ${it.size} items")
            habitListAdapter.updateHabitList(it)
        })

        viewModel.habitLoadErrorLD.observe(viewLifecycleOwner, Observer {
            if(it == true) {
                binding.txtError?.visibility = View.VISIBLE
            } else {
                binding.txtError?.visibility = View.GONE
            }

        })
        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                binding.recViewHabit.visibility = View.GONE
                binding.progressLoad.visibility = View.VISIBLE
                binding.txtError?.visibility = View.VISIBLE
            } else {
                binding.recViewHabit.visibility = View.VISIBLE
                binding.progressLoad.visibility = View.GONE
                binding.txtError?.visibility = View.GONE
            }
        })

    }


}