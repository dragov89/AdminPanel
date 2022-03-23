package com.example.adminpanel.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_items")
data class UserItemsDbModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val login: String,
    val password: String,
    val name: String,
    var status: Boolean = true,
    var yer: Int = DEF_YER

) {
    companion object {
        const val DEF_YER = 200
    }
}
