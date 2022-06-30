package com.example.davidharrischaschallenge.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController

import com.example.davidharrischaschallenge.R
import com.example.davidharrischaschallenge.databinding.FragmentBaseBinding
import com.example.davidharrischaschallenge.model.SchoolModel
import com.example.davidharrischaschallenge.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_base.*

/**
 * This fragment is for our title screen
 * using binding to interact with our view/xml
 */
class BaseFragment : Fragment() {
    private var schools: List<SchoolModel> = ArrayList()
    private var _bind: FragmentBaseBinding? = null
    private val bind get() = _bind
    private lateinit var viewModel: MainViewModel


    //inflates the view for the user interaction
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _bind = FragmentBaseBinding.inflate(inflater, container, false)
        lazyLoadData()
        return bind?.root
    }

    //on resume and setting up button for navigation
    override fun onResume() {
        super.onResume()
        setupListener()
    }
    //our button for school list
    private fun setupListener() = btn_next.setOnClickListener {launchNavigation()}

    //load lazy to save resources and getting our list of schools
    private fun lazyLoadData(){
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.schoolObservable().observe(viewLifecycleOwner, Observer {schoolList ->
            schools = schoolList
        })
        viewModel.fetchSchools()
    }

    //navigation to our other fragment
    private fun launchNavigation(){
        val bundle = bundleOf("schoolList" to schools)
        this.findNavController().navigate(R.id.next_listviewfragment, bundle)
    }

    override fun onDestroy() {
        super.onDestroy()
        _bind = null
    }
}
