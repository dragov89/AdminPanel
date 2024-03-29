package com.example.adminpanel.presentation.adminList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.adminpanel.R
import com.example.adminpanel.databinding.FragmentAdminListItemBinding

class AdminListItemFragment : Fragment() {

    private lateinit var binding: FragmentAdminListItemBinding
    private lateinit var viewModel: AdminListViewModel
    private lateinit var userAdapter: AdapterUserList

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAdminListItemBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecycleView()
        viewModel = ViewModelProvider(this)[AdminListViewModel::class.java]
        viewModel.userList.observe(viewLifecycleOwner) {
            userAdapter.submitList(it)
        }
        binding.btAddShop.setOnClickListener {
            findNavController().navigate(AdminListItemFragmentDirections.actionAdminListItemFragmentToAdminUserFragment(
                false
            )
            )
        }

    }

    private fun setupRecycleView() {
        userAdapter = AdapterUserList()
        binding.rcUserList.adapter = userAdapter
        binding.rcUserList.recycledViewPool.setMaxRecycledViews(
            AdapterUserList.VIEW_TYPE_ENABLED,
            AdapterUserList.MAX_POL
        )
        binding.rcUserList.recycledViewPool.setMaxRecycledViews(
            AdapterUserList.VIEW_TYPE_DISABLED,
            AdapterUserList.MAX_POL
        )
        clickLongListener()
        clickListener()
//        swipeListener()
    }

    //    private fun swipeListener() {
//        val callback = object :
//            ItemTouchHelper.SimpleCallback(
//                0,
//                ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
//            ) {
//            override fun onMove(
//                recyclerView: RecyclerView,
//                viewHolder: RecyclerView.ViewHolder,
//                target: RecyclerView.ViewHolder
//            ): Boolean {
//                return false
//            }
//
//            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
//                1 варіант(UserListDiffCallback) val item = userAdapter.userList[viewHolder.adapterPosition]
//                2 варіант(UserItemDiffCallback) val item = userAdapter.currentList[viewHolder.adapterPosition]
//                viewModel.deleteUser(item)
//            }
//        }
//        val itemTouchHelper = ItemTouchHelper(callback)
//        itemTouchHelper.attachToRecyclerView(binding.rcUserList)
//    }
    private fun clickListener() {
        userAdapter.onUserClickListener = {
            val itemId = it.id
            findNavController().navigate(
                AdminListItemFragmentDirections.actionAdminListItemFragmentToAdminUserFragment(
                    true,
                    itemId
                )
            )
        }
    }

    private fun clickLongListener() {
        userAdapter.onUserClickLongListener = {
            viewModel.editUserStatus(it)
        }
    }
}