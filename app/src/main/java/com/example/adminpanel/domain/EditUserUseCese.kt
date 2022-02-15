package com.example.registration.domain

import com.example.adminpanel.domain.User
import com.example.adminpanel.domain.UserRepository

class EditUserUseCese(private val userRepository: UserRepository) {
//    fun editShopItem(name: String, count: Int) {
//    }
    fun editUserItem(user: User){
        userRepository.editUserItem(user)
    }
}