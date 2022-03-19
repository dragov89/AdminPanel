package com.example.adminpanel.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserListDao {
    @Query("SELECT * FROM user_items")
    fun getUserList(): LiveData<List<UserItemsDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addUserItem(userItemsDbModel: UserItemsDbModel)

    @Query("DELETE FROM user_items WHERE id=:userItemId")
    fun deleteUser(userItemId: Int)

    @Query("SELECT * FROM user_items WHERE id=:userItemId")
    fun getUserItem(userItemId: Int): UserItemsDbModel
}