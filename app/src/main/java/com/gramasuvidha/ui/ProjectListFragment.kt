package com.gramasuvidha.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import com.gramasuvidha.R
import com.gramasuvidha.viewmodel.ProjectViewModel

class ProjectListFragment : Fragment() {

    private val viewModel: ProjectViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_project_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        
        val adapter = ProjectAdapter({ viewModel.isKannada }) { project ->
            viewModel.selectProject(project.id)
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, ProjectDetailFragment())
                .addToBackStack(null)
                .commit()
        }
        
        recyclerView.adapter = adapter

        viewModel.projects.observe(viewLifecycleOwner) { projects ->
            if (projects != null && projects.isNotEmpty()) {
                adapter.submitList(projects.toList())
            }
        }
    }
}
