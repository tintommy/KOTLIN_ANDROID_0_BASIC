package com.tommy.viewmodelscope

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tommy.viewmodelscope.model.User
import com.tommy.viewmodelscope.model.UserRepository
import kotlinx.coroutines.*

class MainActivityViewModel : ViewModel() {
    private var userRepository = UserRepository()
    private var users: MutableLiveData<List<User>> = MutableLiveData()

    fun getUserData() //lấy danh sách users từ repository cho list Users
     {
        viewModelScope.launch {
            var result: List<User>? = null
            withContext(Dispatchers.IO) {
                result = userRepository.getUsers()
            }
            users.value = result
        }
    }

    fun getUsers(): MutableLiveData<List<User>> {
        getUserData()
        return users
    }


}