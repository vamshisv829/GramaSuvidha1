package com.gramasuvidha.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gramasuvidha.data.Project
import com.gramasuvidha.data.ProjectRepository

class ProjectViewModel(app: Application) : AndroidViewModel(app) {

    private val repo = ProjectRepository(app)

    private val _projects = MutableLiveData<List<Project>>()
    val projects: LiveData<List<Project>> = _projects

    private val _selected = MutableLiveData<Project?>()
    val selected: LiveData<Project?> = _selected

    var isKannada = false

    init { loadProjects() }

    fun loadProjects() { _projects.value = repo.getProjects() }

    fun selectProject(id: String) { _selected.value = repo.getProjectById(id) }

    fun submitFeedback(projectId: String, rating: Int, issue: String?) =
        repo.submitFeedback(projectId, rating, issue)

    fun toggleLanguage() {
        isKannada = !isKannada
        _projects.value = _projects.value // trigger observers to re-bind labels
    }
}
