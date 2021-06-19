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
        btnLogThisMeal.setOnClickListener {
            val sdf = SimpleDateFormat("yyyy.MM.dd")
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
        calculateCalories()
    }

    fun calculateCalories() {
        viewModelUser.userLD.observe(viewLifecycleOwner, Observer {
            updateTodoListUser(it)
            var calories: Double
            if (it[0].gender.toString() == "Male") {
                calories =
                    13.397 * it[0].weight!!.toDouble() + 4.799 * it[0].height!!.toDouble() - 5.677 * it[0].age!!.toDouble() + 88.362
            } else {
                calories =
                    9.247 * it[0].weight!!.toDouble() + 3.098 * it[0].height!!.toDouble() - 4.330 * it[0].age!!.toDouble() + 447.593
            }

            if (it[0].personalGoal == 2) {
                calories += (calories * 15 / 100)
            } else if (it[0].personalGoal == 3) {
                calories -= (calories * 15 / 100)
            }
            txtCal.text = "<b>"+Math.round(calories).toString()+"cal </b>" + "needed today"
        })
    }

    fun updateTodoListUser(newTodoListUser: List<User>) {
        listUser.clear()
        listUser.addAll(newTodoListUser)
    }


}