package id.ac.ubaya.informatika.finaltermproject.view.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import id.ac.ubaya.informatika.finaltermproject.R
import id.ac.ubaya.informatika.finaltermproject.databinding.ReportItemListBinding
import id.ac.ubaya.informatika.finaltermproject.view.model.Report

class ReportAdapter(val reportList: ArrayList<Report>):RecyclerView.Adapter<ReportAdapter.ReportViewHolder>() {
    class ReportViewHolder(val view: ReportItemListBinding):RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReportViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = DataBindingUtil.inflate<ReportItemListBinding>(inflater,
            R.layout.report_item_list,parent,false)
        return ReportAdapter.ReportViewHolder(v)
    }

    override fun onBindViewHolder(holder: ReportViewHolder, position: Int) {
        holder.view.report = reportList[position]
    }

    fun updateReportList(newReportlist: List<Report>) {
        reportList.clear()
        reportList.addAll(newReportlist)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return reportList.size
    }

}