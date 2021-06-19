package id.ac.ubaya.informatika.finaltermproject.view.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import id.ac.ubaya.informatika.finaltermproject.R
import kotlinx.android.synthetic.main.fragment_report.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class ReportFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_report, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val date = LocalDateTime.now()
        val format = DateTimeFormatter.ofPattern("MMMM yyyy")
        val currentDate = date.format(format)
        txtDate.text = currentDate
    }

}