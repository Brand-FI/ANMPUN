package com.ubayadev.anmpun.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.ubayadev.anmpun.databinding.FragmentLoginBinding
import com.ubayadev.anmpun.viewmodel.LoginViewModel

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container:
        ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(
            inflater,
            container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        if(viewModel.isLogin()) {//cek session login
            val action = LoginFragmentDirections.actionDashboardFragment()
            findNavController().navigate(action)
        }

        observeViewModel()

        binding.btnLogin.setOnClickListener {
            val username = binding.txtUsername.text.toString()
            val password = binding.txtPassword.text.toString()
            viewModel.login(username, password)
        }
    }

    fun observeViewModel() {
        viewModel.loginResult.observe(viewLifecycleOwner) {
            if (it == true) {
                val action = LoginFragmentDirections.actionDashboardFragment()
                findNavController().navigate(action)
            } else {
                Toast.makeText(requireContext(), "Username atau password salah!", Toast.LENGTH_SHORT).show()
            }
        }
    }

}