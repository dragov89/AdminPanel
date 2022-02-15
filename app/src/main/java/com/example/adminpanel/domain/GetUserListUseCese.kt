package com.example.registration.domain

import androidx.lifecycle.LiveData
import com.example.adminpanel.domain.User
import com.example.adminpanel.domain.UserRepository

class GetUserListUseCese(private val userRepository: UserRepository) {
    fun getUserList(): LiveData<List<User>> {
        return userRepository.getUserList()
    }
}
