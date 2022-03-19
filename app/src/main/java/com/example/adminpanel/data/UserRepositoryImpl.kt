package com.example.adminpanel.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.example.adminpanel.domain.User
import com.example.adminpanel.domain.UserRepository
import kotlin.random.Random

class UserRepositoryImpl(application: Application) : UserRepository {

    private val userListDao = AppDatabase.getInstance(application).userListDao()
    private val mapper = UserMapper()

    override fun addUserItem(user: User) {
        userListDao.addUserItem(mapper.mapEntityToDbModel(user))
    }

    override fun deleteUserItem(user: User) {
      userListDao.deleteUser(user.id)
    }

    override fun editUserItem(user: User) {
        userListDao.addUserItem(mapper.mapEntityToDbModel(user))
    }

    override fun idUserItem(userItemId: Int): User {
     val dbModel = userListDao.getUserItem(userItemId)
        return mapper.mapDbModelToEntity(dbModel)
    }

    override fun getUserList(): LiveData<List<User>>  = MediatorLiveData<List<User>>().apply {
        addSource(userListDao.getUserList()){
            value = mapper.mapListDbModelToListEntity(it)
        }
    }

}