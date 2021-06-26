package id.ac.ubaya.informatika.finaltermproject.view.view

import android.os.Bundle
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
import id.ac.ubaya.informatika.finaltermproject.view.model.User
import id.ac.ubaya.informatika.finaltermproject.view.viewmodel.ListFoodLogViewModel
import id.ac.ubaya.informatika.finaltermproject.view.viewmodel.ListReportViewModel
import id.ac.ubaya.informatika.finaltermproject.view.viewmodel.ListUserViewModel
import kotlinx.android.synthetic.main.fragment_log_a_meal.*
import kotlinx.android.synthetic.main.fragment_log_a_meal.txtDate
import kotlinx.android.synthetic.main.fragment_report.*
import java.sql.Date
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class LogAMealFragment : Fragment() {
    private lateinit var viewModel: ListFoodLogViewModel
    private lateinit var viewModelUser: ListUserViewModel
    private lateinit var viewModelReport: ListReportViewModel
    var listUser: ArrayList<User> = ArrayList()
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

        viewModel

        btnLogThisMeal.setOnClickListener {
            val sdf = SimpleDateFormat("yyyy-MM-dd")
            var model = FoodLog(
                txtWhatYouEat.text.toString(),
                txtCalorieApprox.text.toString().toInt(),
                sdf.toString()
            )
            val list = listOf(model)
            viewModel.logAMeal(list)
            Toast.makeText(view.context, "Data added", Toast.LENGTH_LONG).show()
            Navigation.findNavController(it).popBackStack()
        }
        val date = LocalDateTime.now()
        val format = DateTimeFormatter.ofPattern("dd MMMM yyyy")
        val currentDate = date.format(format)
        txtDate.text = currentDate
    }


    fun updateTodoListUser(newTodoListUser: List<User>) {
        listUser.clear()
        listUser.addAll(newTodoListUser)
    }


}