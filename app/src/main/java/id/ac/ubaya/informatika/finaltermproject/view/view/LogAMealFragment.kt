package id.ac.ubaya.informatika.finaltermproject.view.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import id.ac.ubaya.informatika.finaltermproject.R
import id.ac.ubaya.informatika.finaltermproject.view.model.FoodLog
import id.ac.ubaya.informatika.finaltermproject.view.model.Report
import id.ac.ubaya.informatika.finaltermproject.view.model.User
import id.ac.ubaya.informatika.finaltermproject.view.viewmodel.ListFoodLogViewModel
import id.ac.ubaya.informatika.finaltermproject.view.viewmodel.ListReportViewModel
import id.ac.ubaya.informatika.finaltermproject.view.viewmodel.ListUserViewModel
import kotlinx.android.synthetic.main.fragment_food_log.*
import kotlinx.android.synthetic.main.fragment_log_a_meal.*
import kotlinx.android.synthetic.main.fragment_log_a_meal.txtDate
import kotlinx.android.synthetic.main.fragment_report.*
import java.sql.Date
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.math.roundToInt

class LogAMealFragment : Fragment() {
    private lateinit var viewModel: ListFoodLogViewModel
    private lateinit var viewModelUser: ListUserViewModel
    private lateinit var viewModelReport: ListReportViewModel
    var listReport: ArrayList<Report> = ArrayList()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_log_a_meal, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModelUser = ViewModelProvider(this).get(ListUserViewModel::class.java)
        viewModelUser.refresh()

        viewModel = ViewModelProvider(this).get(ListFoodLogViewModel::class.java)
        viewModel.refresh()

        viewModelReport = ViewModelProvider(this).get(ListReportViewModel::class.java)
        viewModelReport.refresh()

        val neededCalories = LogAMealFragmentArgs.fromBundle(requireArguments()).neededCalories
        txtCal.text = neededCalories.toString() + " Cal needed for today"

        btnLogThisMeal.setOnClickListener {
            val date = LocalDateTime.now()
            val format = DateTimeFormatter.ofPattern("dd-MM-yyyy")
            val currentDate = date.format(format)
            var model = FoodLog(
                txtWhatYouEat.text.toString(),
                txtCalorieApprox.text.toString().toInt(),
                currentDate.toString()
            )
            val listFoodLog = listOf(model)
            viewModel.logAMeal(listFoodLog)

            var model2 = Report(currentDate, txtCalorieApprox.text.toString().toInt())
            Log.d("aaaa", 1.toString())
            if (listReport.count() != 0 )
            {
                Log.d("aaaa", 1.toString())
                for (i in listReport) {
                    Log.d("aaaa", 1.toString())
                    if (i.date.toString() == currentDate) {
                        Log.d("aaaa", 1.toString())
                        var newCalories: Int =
                                i.calories!!.toInt() + txtCalorieApprox.text.toString().toInt()
                        Log.d("aaaa", 1.toString())
                        viewModelReport.update(currentDate, newCalories)
                    } else {
                        val nListReport = listOf(model2)
                        Log.d("aaaa", 0.toString())
                        viewModelReport.insertReport(nListReport)
                    }
                }
            }
            else
            {
                val nListReport = listOf(model2)
                Log.d("aaaa", 0.toString())
                viewModelReport.insertReport(nListReport)
            }


            Toast.makeText(view.context, "Data added", Toast.LENGTH_LONG).show()
            Navigation.findNavController(it).popBackStack()
        }
        val date = LocalDateTime.now()
        val format = DateTimeFormatter.ofPattern("dd MMMM yyyy")
        val currentDate = date.format(format)
        txtDate.text = currentDate

        observeViewModelReport()
    }

    fun observeViewModelReport() {
        viewModelReport.reportLD.observe(
            viewLifecycleOwner, Observer {
                updateReportList(it)
    })
    }
    fun updateReportList(newReportList: List<Report>) {
        listReport.clear()
        listReport.addAll(newReportList)
    }
}
