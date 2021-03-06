package id.ac.ubaya.informatika.finaltermproject.view.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import id.ac.ubaya.informatika.finaltermproject.R
import id.ac.ubaya.informatika.finaltermproject.databinding.FragmentLogAMealBinding
import id.ac.ubaya.informatika.finaltermproject.databinding.FragmentProfileBinding
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

class LogAMealFragment : Fragment(),LogAMealClick {
    private lateinit var viewModel: ListFoodLogViewModel
    private lateinit var viewModelUser: ListUserViewModel
    private lateinit var viewModelReport: ListReportViewModel

    private lateinit var databinding: FragmentLogAMealBinding

    var bmr = 0
    var currentCalories = 0
    var neededCalories = 0

    var listReport: ArrayList<Report> = ArrayList()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        databinding = DataBindingUtil.inflate<FragmentLogAMealBinding>(inflater, R.layout.fragment_log_a_meal, container, false)
        return databinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModelUser = ViewModelProvider(this).get(ListUserViewModel::class.java)
        viewModelUser.refresh()

        viewModel = ViewModelProvider(this).get(ListFoodLogViewModel::class.java)
        viewModel.refresh()

        viewModelReport = ViewModelProvider(this).get(ListReportViewModel::class.java)
        viewModelReport.refresh()

        databinding.listenerr = this

        bmr = LogAMealFragmentArgs.fromBundle(requireArguments()).bmr
        currentCalories = LogAMealFragmentArgs.fromBundle(requireArguments()).currentCalories
        neededCalories = bmr - currentCalories

        if (neededCalories < 0) {
            txtCal.text = "0 Cal needed for today"
        } else{
            txtCal.text = neededCalories.toString() + " Cal needed for today "
        }

        Log.d("bmr",bmr.toString())
        Log.d("CDE",currentCalories.toString())

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

    override fun onLogAmealClick(v: View) {
        if (txtWhatYouEat.text.toString() == "" || txtCalorieApprox.text.toString() == "" || txtWhatYouEat.text.toString() == "" && txtCalorieApprox.text.toString() == ""){
            Toast.makeText(v.context, "Please fill the text box", Toast.LENGTH_LONG).show()
        } else {


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

            var status = "Low"
            val calories = ((currentCalories.toDouble() + txtCalorieApprox.text.toString()
                    .toDouble()) / bmr.toDouble()) * 100


            Log.d("approx",txtCalorieApprox.text.toString())

            if (calories < 50) {
                status = "Low"
            } else if (calories > 100) {
                status = "Exceed"
            } else {
                status = "Normal"
            }

            var mealCount = 1
            var check = 0
            var model2 =
                    Report(currentDate, txtCalorieApprox.text.toString().toInt(), status, mealCount)
            if (listReport.count() != 0) {
                for (i in listReport) {
                    if (i.date.toString() == currentDate) {
                        mealCount = i.meal!!.toInt() + 1
                        var newCalories: Int =
                                i.calories!!.toInt() + txtCalorieApprox.text.toString().toInt()
                        viewModelReport.update(currentDate, newCalories, status, mealCount)
                        check = 1
                        break
                    }
                }

                if (check == 0){
                    val nListReport = listOf(model2)
                    viewModelReport.insertReport(nListReport)
                }
            } else {
                val nListReport = listOf(model2)
                viewModelReport.insertReport(nListReport)
            }



            Toast.makeText(v.context, "Data added", Toast.LENGTH_LONG).show()
            Navigation.findNavController(v).popBackStack()
        }
    }
}
