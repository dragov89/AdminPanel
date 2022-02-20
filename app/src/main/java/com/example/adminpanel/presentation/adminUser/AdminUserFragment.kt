package com.example.adminpanel.presentation.adminUser

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
    companion object{
        private const val ID_ITEM = "idUserItem"
        private const val EXTRA_SCREEN_MOD = "extra_mode"
        private const val EDIT_MOD = "edit_mod"
        private const val ADD_MOD = "add_mod"
    }
}