package com.example.adminpanel.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.adminpanel.R
import com.example.adminpanel.databinding.FragmentAdminUserBinding

class AdminUserFragment : Fragment() {
    private lateinit var binding: FragmentAdminUserBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAdminUserBinding.inflate(inflater)
    return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnCancel.setOnClickListener {
            activity?.onBackPressed()
        }
    }
}