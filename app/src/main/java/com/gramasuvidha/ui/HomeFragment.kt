package com.gramasuvidha.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.gramasuvidha.R

class HomeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        val prefs = requireActivity().getSharedPreferences("GramaSuvidha", android.content.Context.MODE_PRIVATE)
        val userType = prefs.getString("userType", "citizen")
        
        val tvWelcome = view.findViewById<TextView>(R.id.tvWelcome)
        val tvUserType = view.findViewById<TextView>(R.id.tvUserType)
        
        tvWelcome.text = "Welcome to Grama Suvidha"
        tvUserType.text = "Logged in as: ${userType?.capitalize()}"
    }
}
