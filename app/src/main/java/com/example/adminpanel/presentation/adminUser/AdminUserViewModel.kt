package com.example.adminpanel.presentation.adminUser

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.adminpanel.data.UserRepositoryImpl
import com.example.adminpanel.domain.User
import com.example.registration.domain.*

class AdminUserViewModel : ViewModel() {
    private val repository = UserRepositoryImpl

    private val deleteUserUseCase = DeleteUserUseCese(repository)

    private val idUserItemUseCese = IdUserItemUseCese(repository)
    private val editUserUseCese = EditUserUseCese(repository)
    private val addUserUseCese = AddUserUseCese(repository)

    private val _errorInputLogin = MutableLiveData<Boolean>()
    val errorInputLogin: LiveData<Boolean>
        get() = _errorInputLogin

    private val _errorInputPassword = MutableLiveData<Boolean>()
    val errorInputPassword: LiveData<Boolean>
        get() = _errorInputPassword

    private val _errorInputName = MutableLiveData<Boolean>()
    val errorInputName: LiveData<Boolean>
        get() = _errorInputName

    private val _errorInputYer = MutableLiveData<Boolean>()
    val errorInputYer: LiveData<Boolean>
        get() = _errorInputYer

    private val _users = MutableLiveData<User>()
    val users: LiveData<User>
        get() = _users

    private lateinit var items:User

    private val _closedFragment = MutableLiveData<Unit>()
    val closedFragment: LiveData<Unit>
    get() = _closedFragment

    fun deleteUser(idUserItem: Int) {
        val item =idUserItemUseCese.idUserItem(idUserItem)
            deleteUserUseCase.deleteUserItem(item)
            finishFragment()
        }


    fun idUserItem(idUserItem: Int) {
        val item =idUserItemUseCese.idUserItem(idUserItem)
        _users.value =  item

    }

    fun addUser(
        inputLogin: String?,
        inputPassword: String?,
        inputName: String?,
        inputYer: String?
    ) {
        val login = parseLogin(inputLogin)
        val password = parsePassword(inputPassword)
        val name = parseName(inputName)
        val yer = parseYer(inputYer)
        val correctly = correctlyInput(login, password, name, yer)
        if (correctly) {
            val user = User(login, password, name, yer = yer)
            addUserUseCese.addUserItem(user)
            finishFragment()
        }
    }

    fun editUserItem (inputLogin: String?,
    inputPassword: String?,
    inputName: String?,
    inputYer: String?
    ) {
        val login = parseLogin(inputLogin)
        val password = parsePassword(inputPassword)
        val name = parseName(inputName)
        val yer = parseYer(inputYer)
        val correctly = correctlyInput(login, password, name, yer)
        if (correctly) {
            _users.value?.let {
             val item = it.copy(login = login, password = password, name = name, yer = yer)
                editUserUseCese.editUserItem(item)
                finishFragment()
            }
        }
    }

    fun parseLogin(inputLogin: String?): String {
        return inputLogin?.trim() ?: ""
    }

    fun parsePassword(inputPassword: String?): String {
        return inputPassword?.trim() ?: ""
    }

    fun parseName(inputName: String?): String {
        return inputName?.trim() ?: ""
    }

    fun parseYer(inputYer: String?): Int {
        return try {
            inputYer?.trim()?.toInt() ?: User.DEF_YER
        } catch (e: Exception) {
            0
        }
    }

    fun correctlyInput(login: String, password: String, name: String, yer: Int): Boolean {
        var result = true
        if (login.isBlank()) {
            _errorInputLogin.value = true
            result = false
        }
        if (password.isBlank()) {
            _errorInputPassword.value = true
            result = false
        }
        if (name.isBlank()) {
            _errorInputName.value = true
            result = false
        }
        if (yer !in 1930 until 2020) {
            _errorInputYer.value = true
            result = false
        }
        return result
    }


    fun resetErrorLogin() {
        _errorInputLogin.value = false
    }

    fun resetErrorPassword() {
        _errorInputPassword.value = false
    }

    fun resetErrorName() {
        _errorInputName.value = false
    }

    fun resetErrorYer() {
        _errorInputYer.value = false
    }


    private fun finishFragment() {
        _closedFragment.value = Unit

    }

}