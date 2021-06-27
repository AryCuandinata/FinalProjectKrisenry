package id.ac.ubaya.informatika.finaltermproject.view.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import id.ac.ubaya.informatika.finaltermproject.R
import java.util.*


import id.ac.ubaya.informatika.finaltermproject.databinding.FragmentReportBinding

import id.ac.ubaya.informatika.finaltermproject.view.viewmodel.ListReportViewModel

import kotlinx.android.synthetic.main.fragment_report.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class ReportFragment : Fragment() {
    private lateinit var databinding: FragmentReportBinding
    private lateinit var viewModel: ListReportViewModel
    private var reportAdapter = ReportAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_report, container, false)
        databinding = DataBindingUtil.inflate<FragmentReportBinding>(
            inflater,
            R.layout.fragment_report,
            container,
            false
        )
        return databinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val date = LocalDateTime.now()
        val format = DateTimeFormatter.ofPattern("MMMM yyyy")
        val currentDate = date.format(format)
        txtDate.text = currentDate

        viewModel = ViewModelProvider(this).get(ListReportViewModel::class.java)
        viewModel.refresh()

        recViewReport.layoutManager = LinearLayoutManager(context)
        recViewReport.adapter = reportAdapter

        observeViewModel()
    }
    fun observeViewModel() {
        viewModel.reportLD.observe(
        viewLifecycleOwner, Observer{
            reportAdapter.updateReportList(it)
            })
    }
}