package com.gramasuvidha.data

import android.content.Context
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

data class ProjectResponse(@SerializedName("projects") val projects: List<Project>)

class MockApiService(private val context: Context) {

    fun fetchProjects(): List<Project> {
        return try {
            val json = context.assets.open("projects.json").bufferedReader().use { it.readText() }
            Gson().fromJson(json, ProjectResponse::class.java).projects
        } catch (e: Exception) {
            e.printStackTrace()
            // Return empty list if JSON fails to load
            emptyList()
        }
    }
}
