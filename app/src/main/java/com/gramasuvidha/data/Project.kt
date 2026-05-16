package com.gramasuvidha.data

data class ProjectUpdate(
    val date: String,
    val note: String
)

data class Project(
    val id: String,
    val title: String,
    val title_kn: String,
    val description: String,
    val description_kn: String,
    val budget: Long,
    val progress: Int,
    val status: String,
    val status_kn: String,
    val expected_completion: String,
    val contractor: String,
    val before_image: String,
    val after_image: String,
    val updates: List<ProjectUpdate>
)
