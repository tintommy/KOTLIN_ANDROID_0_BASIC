package com.tommy.studentregister

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tommy.studentregister.db.StudentDAO

class StudentViewModelFactory (
    private val dao:StudentDAO
):ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(StudentViewModel::class.java)){
            return StudentViewModel(dao) as T
        }
        throw IllegalAccessException("Unknown View Model Class")
    }
}