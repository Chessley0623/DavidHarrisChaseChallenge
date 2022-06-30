package com.example.davidharrischaschallenge.repository

import com.example.davidharrischaschallenge.repository.remote.ApiClient

//using repository so our api can access the rest of our app
class Repository {
    private object RepositoryInstanceHolder{
        val REPO_INSTANCE = Repository()}
    private val apiClient = ApiClient()

    //using coroutines to get schools and scores
    suspend fun getSchools() = apiClient.getSchoolList()

    suspend fun  getScores() = apiClient.getScoreList()

    //companion object lets us access members of the class
    companion object{val INSTANCE = RepositoryInstanceHolder.REPO_INSTANCE}
}