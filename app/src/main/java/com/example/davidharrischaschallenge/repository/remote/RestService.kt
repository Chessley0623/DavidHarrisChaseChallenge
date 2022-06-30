package com.example.davidharrischaschallenge.repository.remote

import com.example.davidharrischaschallenge.model.SchoolModel
import com.example.davidharrischaschallenge.model.ScoreModel
import com.example.davidharrischaschallenge.repository.remote.Constants.URL_PATH_SAT_SCORES
import com.example.davidharrischaschallenge.repository.remote.Constants.URL_PATH_SCHOOLS
import retrofit2.http.GET

//using get function in our rest service to retrieve data for schools and scores
interface RestService {

    //using suspend functions for our two list so we do not block the main thread
    @GET(URL_PATH_SCHOOLS)
    suspend fun getListOfSchools(): List<SchoolModel>

    @GET(URL_PATH_SAT_SCORES)
    suspend fun getListOfScores(): List<ScoreModel>

}