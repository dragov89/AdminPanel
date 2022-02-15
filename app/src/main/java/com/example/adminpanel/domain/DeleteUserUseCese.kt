package com.example.registration.domain

import com.example.adminpanel.domain.User
import com.example.adminpanel.domain.UserRepository

class DeleteUserUseCese(private val userRepository: UserRepository) {
 fun deleteUserItem(user: User){
  userRepository.deleteUserItem(user)

 }
}