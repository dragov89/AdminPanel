package com.example.adminpanel.presentation.adminList

import androidx.lifecycle.ViewModel
import com.example.adminpanel.data.UserRepositoryImpl
import com.example.adminpanel.domain.User
import com.example.registration.domain.EditUserUseCese
import com.example.registration.domain.GetUserListUseCese

class AdminListViewModel : ViewModel() {
    private val repository = UserRepositoryImpl

    private val getUserListUseCase = GetUserListUseCese(repository)
    private val editUserUseCase = EditUserUseCese(repository)

    val userList = getUserListUseCase.getUserList()

    fun editUserStatus(user: User) {
        val newItem = user.copy(status = !user.status)
        editUserUseCase.editUserItem(newItem)
    }


}