package com.example.davidharrischaschallenge.repository.remote

import com.example.davidharrischaschallenge.model.SchoolModel
import com.example.davidharrischaschallenge.model.ScoreModel

class ApiClient {

    //this is where we can utilize our apis creating an interface for the server
    private val apiService: RestService =
        ApiEndPoint.retrofitInstance.create(
            RestService::class.java)

    //using suspend functions to get our 2 list
    suspend fun getSchoolList(): List<SchoolModel> =
        apiService.getListOfSchools()

    suspend fun getScoreList(): List<ScoreModel> =
        apiService.getListOfScores()

}