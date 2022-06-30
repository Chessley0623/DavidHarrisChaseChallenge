package com.example.davidharrischaschallenge.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.davidharrischaschallenge.databinding.FragmentDetailViewBinding
import com.example.davidharrischaschallenge.model.ScoreModel

/**
 * This fragment will handle the data of the selected school's scores
 * using binding to interact with our view/xml
 */
class DetailViewFragment : Fragment() {

    //variables for our list and data binding
    private var _bind: FragmentDetailViewBinding? = null
    private val bind get() = _bind
    private lateinit var scores: List<ScoreModel>
    private var score = ScoreModel()

    //inflating the view to display the scores of the school
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _bind = FragmentDetailViewBinding.inflate(inflater, container, false)
        @Suppress("UNCHECKED_CAST")
        scores = arguments?.get("scores") as List<ScoreModel>
        bind?.scoreModel = scores[0]
        return bind?.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _bind = null
    }

}
