package com.example.adminpanel.data

import com.example.adminpanel.domain.User

class UserMapper {
    fun mapEntityToDbModel(user: User) = UserItemsDbModel(
        id = user.id,
        login = user.login,
        password = user.password,
        name = user.name,
        status = user.status,
        yer = user.yer
    )

    fun mapDbModelToEntity(userItemsDbModel: UserItemsDbModel) = User(
        id = userItemsDbModel.id,
        login = userItemsDbModel.login,
        password = userItemsDbModel.password,
        name = userItemsDbModel.name,
        status = userItemsDbModel.status,
        yer = userItemsDbModel.yer
    )
    fun mapListDbModelToListEntity(list: List<UserItemsDbModel>) = list.map {
        mapDbModelToEntity(it)
    }
}