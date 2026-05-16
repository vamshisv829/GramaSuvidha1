package com.gramasuvidha.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import coil.load
import com.gramasuvidha.R
import com.gramasuvidha.viewmodel.ProjectViewModel

class ProjectDetailFragment : Fragment() {

    private val viewModel: ProjectViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_project_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val tvTitle = view.findViewById<TextView>(R.id.tvTitle)
        val tvDescription = view.findViewById<TextView>(R.id.tvDescription)
        val tvStatus = view.findViewById<TextView>(R.id.tvStatus)
        val tvBudget = view.findViewById<TextView>(R.id.tvBudget)
        val tvCompletion = view.findViewById<TextView>(R.id.tvCompletion)
        val tvContractor = view.findViewById<TextView>(R.id.tvContractor)
        val progressBar = view.findViewById<ProgressBar>(R.id.progressBar)
        val tvProgress = view.findViewById<TextView>(R.id.tvProgress)
        val ivBefore = view.findViewById<ImageView>(R.id.ivBefore)
        val ivAfter = view.findViewById<ImageView>(R.id.ivAfter)
        val tvUpdates = view.findViewById<TextView>(R.id.tvUpdates)
        val ratingBar = view.findViewById<RatingBar>(R.id.ratingBar)
        val btnSubmitFeedback = view.findViewById<Button>(R.id.btnSubmitFeedback)
        val etIssue = view.findViewById<EditText>(R.id.etIssue)
        val btnReportIssue = view.findViewById<Button>(R.id.btnReportIssue)

        viewModel.selected.observe(viewLifecycleOwner) { project ->
            project ?: return@observe
            val kn = viewModel.isKannada
            
            tvTitle.text = if (kn) project.title_kn else project.title
            tvDescription.text = if (kn) project.description_kn else project.description
            tvStatus.text = if (kn) project.status_kn else project.status
            tvBudget.text = "₹${"%,d".format(project.budget)}"
            tvCompletion.text = project.expected_completion
            tvContractor.text = project.contractor
            progressBar.progress = project.progress
            tvProgress.text = "${project.progress}%"
            ivBefore.load(project.before_image) { crossfade(true) }
            ivAfter.load(project.after_image) { crossfade(true) }

            val updatesText = project.updates.joinToString("\n") { "• ${it.date}: ${it.note}" }
            tvUpdates.text = updatesText.ifEmpty {
                if (kn) "ಯಾವುದೇ ನವೀಕರಣಗಳಿಲ್ಲ" else "No updates yet"
            }

            btnSubmitFeedback.setOnClickListener {
                val rating = ratingBar.rating.toInt()
                viewModel.submitFeedback(project.id, rating, null)
                Toast.makeText(requireContext(),
                    if (kn) "ಪ್ರತಿಕ್ರಿಯೆ ಸಲ್ಲಿಸಲಾಗಿದೆ!" else "Feedback submitted!",
                    Toast.LENGTH_SHORT).show()
            }

            btnReportIssue.setOnClickListener {
                val issue = etIssue.text.toString().trim()
                if (issue.isEmpty()) {
                    etIssue.error = if (kn) "ಸಮಸ್ಯೆ ವಿವರಿಸಿ" else "Please describe the issue"
                    return@setOnClickListener
                }
                viewModel.submitFeedback(project.id, 0, issue)
                etIssue.setText("")
                Toast.makeText(requireContext(),
                    if (kn) "ಸಮಸ್ಯೆ ವರದಿ ಮಾಡಲಾಗಿದೆ!" else "Issue reported!",
                    Toast.LENGTH_SHORT).show()
            }
        }
    }
}
