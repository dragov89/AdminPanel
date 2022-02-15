package com.example.adminpanel.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.adminpanel.domain.User
import com.example.adminpanel.domain.UserRepository
import kotlin.random.Random

object UserRepositoryImpl : UserRepository {


    private val listUserLiveData = MutableLiveData<List<User>>()

    //сортування списку (компаратор) ({p0, p1 -> p0.id.compareTo(p1.id)}) по ід
    private val userList = sortedSetOf<User>({ p0, p1 -> p0.id.compareTo(p1.id) })

    private var autoIncrementId = 0

        //список (заповнення списка даними)
    init {
        for (i in 0 until 33){
            val item = User("Login $i", "$i","Name $i", Random.nextBoolean())
            addUserItem(item)
        }
    }

    override fun addUserItem(user: User) {
        if (user.id == User.UNDEF_ID){
        user.id = autoIncrementId++
        }
        userList.add(user)
        updateList()
    }

    override fun deleteUserItem(user: User) {
        userList.remove(user)
        updateList()

    }


    override fun editUserItem(user: User) {
        val oldUser = idUserItem(user.id)
        userList.remove(oldUser)
        addUserItem(user)

    }

    override fun idUserItem(userItemId: Int): User {
        return userList.find { it.id == userItemId }
            ?: throw RuntimeException("id $userItemId not found")
    }

    override fun getUserList(): LiveData<List<User>> {
        return listUserLiveData
    }

    fun updateList(){
        listUserLiveData.value = userList.toList()
    }
}

//    private var defId = 0
//private val shopListLD = MutableLiveData<List<ShopItem>>()
////    private val shopList = sortedSetOf<ShopItem>(object :Comparator<ShopItem>{
////        override fun compare(p0: ShopItem?, p1: ShopItem?): Int {
////        }
////    })
//
//    private val shopList = sortedSetOf<User>({ p0, p1 -> p0.id.compareTo(p1.id) })
//
//    init {
//        for (i in 0 until 8){
//            val item = User("Name $i", "Name $i", "Name $i")
//            addShopItem(item)
//        }
//    }
//
//    override fun addShopItem(shopItem: ShopItem) {
//        if (shopItem.id == ShopItem.UNDEF_ID) {
//            shopItem.id = defId++
//        }
//        shopList.add(shopItem)
//        updateList()
//    }
//
//    override fun deleteShopItem(shopItem: ShopItem) {
//        shopList.remove(shopItem)
//        updateList()
//    }
//
//    override fun editShopItem(shopItem: ShopItem) {
//        val oldShopItem = idShopItem(shopItem.id)
//        shopList.remove(oldShopItem)
//        addShopItem(shopItem)
//    }
//
//    override fun idShopItem(shopItemId: Int): ShopItem {
//        return shopList.find { it.id == shopItemId }
//            ?: throw RuntimeException("Id $shopItemId not found")
//    }
//
//    override fun getShopList(): LiveData<List<ShopItem>> {
//        return shopListLD
//    }
//
//    fun updateList(){
//        shopListLD.value = shopList.toList()
//    }