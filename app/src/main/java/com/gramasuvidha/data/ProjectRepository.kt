package com.gramasuvidha.data

import android.content.Context

class ProjectRepository(context: Context) {

    private val api = MockApiService(context)
    private var cache: List<Project> = emptyList()

    fun getProjects(): List<Project> {
        if (cache.isEmpty()) cache = api.fetchProjects()
        return cache
    }

    fun getProjectById(id: String): Project? = getProjects().find { it.id == id }

    fun submitFeedback(projectId: String, rating: Int, issue: String?) {
        // In a real app this would POST to a backend.
        // For now, log to console as mock submission.
        println("Feedback -> Project: $projectId | Rating: $rating | Issue: $issue")
    }
}
