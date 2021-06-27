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
import id.ac.ubaya.informatika.finaltermproject.databinding.FragmentFoodLogBinding
import id.ac.ubaya.informatika.finaltermproject.view.viewmodel.ListEditUser
import id.ac.ubaya.informatika.finaltermproject.view.viewmodel.ListFoodLogViewModel
import id.ac.ubaya.informatika.finaltermproject.view.viewmodel.ListUserViewModel
import kotlinx.android.synthetic.main.fragment_food_log.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.math.roundToInt


class FoodLogFragment : Fragment(),FabListener {

    private lateinit var databinding: FragmentFoodLogBinding
    private lateinit var viewModel: ListFoodLogViewModel
    private lateinit var viewModelUser: ListEditUser
    private val foodLogAdapter = FoodLogAdapter(arrayListOf(), arrayListOf())

    var value = false
    var currCalories = 0
    var bmr = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        databinding = DataBindingUtil.inflate<FragmentFoodLogBinding>(inflater, R.layout.fragment_food_log, container, false)
        return databinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val date = LocalDateTime.now()
        val format = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        val currentDate = date.format(format)

        viewModelUser = ViewModelProvider(this).get(ListEditUser::class.java)
        viewModelUser.fetch(1)

        viewModel = ViewModelProvider(this).get(ListFoodLogViewModel::class.java)
        viewModel.refresh(currentDate)

        recViewFoodLog.layoutManager = LinearLayoutManager(context)
        recViewFoodLog.adapter = foodLogAdapter

        databinding.listener = this


        observeViewModel()
        observeViewModelUser()
    }

    fun observeViewModel() {
        viewModel.foodLD.observe(
            viewLifecycleOwner, Observer {
                foodLogAdapter.updateFoodLogList(it)
                if (it.isEmpty()) {
                    txtError.visibility = View.VISIBLE
                } else {
                    txtError.visibility = View.GONE
                    currCalories = 0
                    for (i in it) {
                        currCalories += i.calories!!.toInt()
                    }
                    textViewCal.text = currCalories.toString()
                    status()

                }

            })
    }

    fun status(){
        Log.d("current",currCalories.toString())
        Log.d("bmr",bmr.toString())
        val calories = (currCalories.toDouble()/bmr.toDouble()) * 100
        Log.d("calories",calories.toString())
        if(calories < 50)
        {
            textViewStatus.text = "Low"
        }
        else if(calories>100)
        {
            textViewStatus.text = "Exceed"
        }
        else{
            textViewStatus.text = "Normal"
        }
    }

    fun observeViewModelUser() {
        viewModelUser.userLD.observe(
            viewLifecycleOwner, Observer {
                databinding.user = it
                if (it == null) {
                    val action = FoodLogFragmentDirections.actionItemFoodLogToWelcomeFragment()
                    Navigation.findNavController(requireView()).navigate(action)
                } else{
                    bmr = it.bmr!!
                    Log.d("bmr",bmr.toString())
                    textViewCal.text = it.bmr.toString()
                }

        })
    }

    override fun onFabListener(v: View) {
        val action = FoodLogFragmentDirections.actionItemFoodLogToLogAMealFragment()
        Navigation.findNavController(v).navigate(action)
    }

}



