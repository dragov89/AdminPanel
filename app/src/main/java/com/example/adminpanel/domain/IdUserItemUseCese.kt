package com.example.registration.domain

import com.example.adminpanel.domain.User
import com.example.adminpanel.domain.UserRepository

class IdUserItemUseCese(private val userRepository: UserRepository) {
    fun idUserItem(userItemId: Int): User {
        return userRepository.idUserItem(userItemId)
    }
}