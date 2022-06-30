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
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.davidharrischaschallenge.R
import com.example.davidharrischaschallenge.adapter.SchoolAdapter
import com.example.davidharrischaschallenge.databinding.FragmentListViewBinding
import com.example.davidharrischaschallenge.model.SchoolModel
import com.example.davidharrischaschallenge.model.ScoreModel
import com.example.davidharrischaschallenge.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_list_view.*

/**
 * This fragment handles the list of schools
 * using binding to interact with our views/xml
 */
class ListViewFragment : Fragment() {

    //our variables for our list and setting our data bindings
    private var _bind: FragmentListViewBinding? = null
    private val bind get() = _bind
    private var schools: List<SchoolModel> = ArrayList()
    private var scores: List<ScoreModel> = ArrayList()
    private lateinit var schoolAdapter: SchoolAdapter
    private lateinit var viewModel: MainViewModel
    private var dbn: String? = null //dbn district borough number for NYC schools

    //function is inflating the view and grabbing data by position in the list
    //use lazy load to save resources until it is needed
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        lazyLoadData()
        _bind = FragmentListViewBinding.inflate(inflater, container, false)
        @Suppress("UNCHECKED_CAST")
        schools = arguments?.get("schoolList") as List<SchoolModel>
        schoolAdapter = SchoolAdapter {position ->
            dbn = schools[position].dbn
            launchNavigation()
        }

        return bind?.root
    }

    // on resume and the scrollable list using recycler view and loads the data from the adapter
    override fun onResume() {
        super.onResume()
        rv_main.apply{
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            schoolAdapter.loadData(schools)
            schoolAdapter.notifyDataSetChanged()
            adapter = schoolAdapter
        }
    }

    //lazy load to save resources
    private fun lazyLoadData(){
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.scoreObservable().observe(viewLifecycleOwner, Observer {scoreList ->
            scores = scoreList
        })
        viewModel.fetchScores()
    }

    //navigation for our fragments
    private fun launchNavigation(){
        val scoreModel = getScoreModel()
        val scores = mutableListOf<ScoreModel>()
        scores.add(scoreModel)
        val bundle = bundleOf("scores" to scores)
        this.findNavController().navigate(R.id.next_detailviewfragment, bundle)
    }

    //get scores for each school using dbn
    private fun getScoreModel(): ScoreModel{
        var model = ScoreModel()
        scores.forEach { school ->
            if(dbn == school.dbn)
                model = school
        }
        return model
    }

    override fun onDestroy() {
        super.onDestroy()
        _bind = null
    }

}
