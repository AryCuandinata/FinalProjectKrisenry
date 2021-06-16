package id.ac.ubaya.informatika.finaltermproject.view.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import id.ac.ubaya.informatika.finaltermproject.R
import id.ac.ubaya.informatika.finaltermproject.view.model.FoodLog
import id.ac.ubaya.informatika.finaltermproject.view.viewmodel.ListFoodLogViewModel
import kotlinx.android.synthetic.main.fragment_log_a_meal.*
import java.sql.Date
import java.text.SimpleDateFormat

class LogAMealFragment : Fragment() {
    private lateinit var viewModel:ListFoodLogViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_log_a_meal, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(ListFoodLogViewModel::class.java)
        btnLogThisMeal.setOnClickListener {
            val sdf = SimpleDateFormat("yyyy.MM.dd")
            var model = FoodLog(txtWhatYouEat.text.toString(), txtCalorieApprox.text.toString().toInt(), sdf.toString())
            val list = listOf(model)
            viewModel.logAMeal(list)
            Toast.makeText(view.context, "Data added", Toast.LENGTH_LONG).show()
            Navigation.findNavController(it).popBackStack()
        }
    }


}