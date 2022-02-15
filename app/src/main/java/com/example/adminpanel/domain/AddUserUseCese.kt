package com.example.registration.domain

import com.example.adminpanel.domain.User
import com.example.adminpanel.domain.UserRepository

class AddUserUseCese(private val userRepository: UserRepository) {
    fun addUserItem(user: User){
        userRepository.addUserItem(user)

    }
}