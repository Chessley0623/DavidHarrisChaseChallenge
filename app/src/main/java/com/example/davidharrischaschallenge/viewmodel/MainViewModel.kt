package com.example.davidharrischaschallenge.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.davidharrischaschallenge.model.SchoolModel
import com.example.davidharrischaschallenge.model.ScoreModel
import com.example.davidharrischaschallenge.repository.Repository
import kotlinx.coroutines.launch
//using mutable live data so we can send data to our active observers
//our viewmodel helps manage and prepare data for our fragments and activities
//we have our list of schools and scores below
class MainViewModel(application: Application): AndroidViewModel(application) {
    private val schools = MutableLiveData<List<SchoolModel>>()
    private val scores = MutableLiveData<List<ScoreModel>>()
    private val repo = Repository.INSTANCE

    //live data for schools and scores list
    fun schoolObservable(): LiveData<List<SchoolModel>> = schools

    fun scoreObservable(): LiveData<List<ScoreModel>> = scores

    //below are the coroutines for our list of schools and scores data using launch to start them
    //using viewmodel scope so the lifecycle matches the lifecycle of the viewmodel
    fun fetchSchools(){
        viewModelScope.launch{
            schools.value = repo.getSchools()
        }
    }

    fun fetchScores(){
        viewModelScope.launch{
            scores.value = repo.getScores()
        }
    }


}