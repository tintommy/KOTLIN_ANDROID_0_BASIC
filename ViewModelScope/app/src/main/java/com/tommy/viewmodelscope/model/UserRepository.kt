package com.tommy.viewmodelscope.model

import kotlinx.coroutines.delay

class UserRepository {
    suspend fun getUsers():List<User>{
        delay(8000)
        val users:List<User> = listOf(
        User(1,"Sam"),
        User(2,"Tony"),
        User(3,"Tin"),
        User(4,"Jonh"),
        User(5,"Harry"))
        return users
    }

}