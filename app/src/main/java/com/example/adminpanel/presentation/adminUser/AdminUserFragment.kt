package com.example.adminpanel.presentation.adminUser

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.adminpanel.R
import com.example.adminpanel.databinding.FragmentAdminUserBinding

class AdminUserFragment : Fragment() {
    private lateinit var binding: FragmentAdminUserBinding
    private lateinit var viewModel: AdminUserViewModel

    private val args by navArgs<AdminUserFragmentArgs>()
    private var userItemId = 0

    //private val ViewModelFactiry by lazy {
//    val args = AdminUserFragmentArgs.fromBundle(requireArguments())
//        args.screenMod
//    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAdminUserBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[AdminUserViewModel::class.java]
        parseArgs()
        inputErrorMessage()
        resetErrorMessage()
        binding.btnCancel.setOnClickListener {
            activity?.onBackPressed()
        }
    }

    private fun resetErrorMessage() {
        binding.InputEditTextLogin.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.resetErrorLogin()
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })
        binding.InputEditTextPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.resetErrorPassword()
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })
        binding.InputEditTextName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.resetErrorName()
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })
        binding.InputEditTextYer.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.resetErrorYer()
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })
    }

    private fun inputErrorMessage() {
        viewModel.errorInputLogin.observe(viewLifecycleOwner) {
            val message = if (it) {
                getString(R.string.error_input)
            } else {
                null
            }
            binding.InputLayoutLogin.error = message
        }
        viewModel.errorInputPassword.observe(viewLifecycleOwner) {
            val message = if (it) {
                getString(R.string.error_input)
            } else {
                null
            }
            binding.InputLayoutPassword.error = message
        }
        viewModel.errorInputName.observe(viewLifecycleOwner) {
            val message = if (it) {
                getString(R.string.error_input)
            } else {
                null
            }
            binding.InputLayoutName.error = message
        }
        viewModel.errorInputYer.observe(viewLifecycleOwner) {
            val message = if (it) {
                getString(R.string.error_input)
            } else {
                null
            }
            binding.InputLayoutYer.error = message
        }
        viewModel.closedFragment.observe(viewLifecycleOwner) {
            activity?.onBackPressed()
        }
    }


    private fun parseArgs() {
        if (args.screenMod) {
            binding.btnDelete.visibility = View.VISIBLE
            userItemId = args.idUserItem
            viewModel.idUserItem(userItemId)
            viewModel.users.observe(viewLifecycleOwner) {
                binding.InputEditTextLogin.setText(it.login)
                binding.InputEditTextPassword.setText(it.password)
                binding.InputEditTextName.setText(it.name)
                binding.InputEditTextYer.setText(it.yer.toString())
            }
            binding.btnAddUser.setOnClickListener {
                viewModel.editUserItem(
                    binding.InputEditTextLogin.text?.toString(),
                    binding.InputEditTextPassword.text?.toString(),
                    binding.InputEditTextName.text?.toString(),
                    binding.InputEditTextYer.text?.toString()
                )
            }
            binding.btnDelete.setOnClickListener {
                viewModel.deleteUser(userItemId)
            }
        }else{
            binding.btnAddUser.setOnClickListener {
                viewModel.addUser(
                    binding.InputEditTextLogin.text?.toString(),
                    binding.InputEditTextPassword.text?.toString(),
                    binding.InputEditTextName.text?.toString(),
                    binding.InputEditTextYer.text?.toString()
                )
            }

        }

    }

//    companion object{
//        private const val ID_ITEM = "idUserItem"
//        private const val EXTRA_SCREEN_MOD = "extra_mode"
//        private const val EDIT_MOD = "edit_mod"
//        private const val ADD_MOD = "add_mod"
//
//        fun newIntentAddItem(context: Context):Intent{
//            val intent = Intent(context, MainActivity::class.java )
//            intent.putExtra(EXTRA_SCREEN_MOD, ADD_MOD)
//            return intent
//        }
//        fun newIntentEditItem(context: Context, userItemId: Int):Intent{
//            val intent = Intent(context, MainActivity::class.java )
//            intent.putExtra(EXTRA_SCREEN_MOD, EDIT_MOD)
//            intent.putExtra(ID_ITEM, userItemId)
//            return intent
//        }
//    }
}