package id.ac.ubaya.informatika.finaltermproject.view.view

import android.os.Bundle
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
import id.ac.ubaya.informatika.finaltermproject.view.viewmodel.ListFoodLogViewModel
import id.ac.ubaya.informatika.finaltermproject.view.viewmodel.ListUserViewModel
import kotlinx.android.synthetic.main.fragment_food_log.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.math.roundToInt


class FoodLogFragment : Fragment(),FabListener {

    private lateinit var databinding: FragmentFoodLogBinding
    private lateinit var viewModel: ListFoodLogViewModel
    private lateinit var viewModelUser: ListUserViewModel
    private val foodLogAdapter = FoodLogAdapter(arrayListOf(), arrayListOf())

    var value = false
    var currCalories = 0
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
        textViewCal.text = "0"
        textViewCal2.text = "0"
        viewModelUser = ViewModelProvider(this).get(ListUserViewModel::class.java)
        viewModelUser.refresh()

        viewModel = ViewModelProvider(this).get(ListFoodLogViewModel::class.java)
        viewModel.refresh(currentDate)

        recViewFoodLog.layoutManager = LinearLayoutManager(context)
        recViewFoodLog.adapter = foodLogAdapter

        databinding.listener = this

        observeViewModel()
        observeViewModelUser()
    }

    fun status(){
        val calories = (textViewCal.text.toString().toDouble() + textViewCal2.text.toString().toDouble()) * 100
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
                        var index = 0
                        currCalories += i.calories!!.toInt()
                        index++
                    }
                    textViewCal.text = currCalories.toString()
                }
                status()
            })

    }

    fun observeViewModelUser() {
        viewModelUser.userLD.observe(
            viewLifecycleOwner, Observer {
                foodLogAdapter.updateFoodLogListUser(it)
                if (it.isEmpty()) {
                    val action = FoodLogFragmentDirections.actionItemFoodLogToWelcomeFragment()
                    Navigation.findNavController(requireView()).navigate(action)
                } else {

                    if (it[0].gender.toString() == "1"){
                        textViewGender.text = "Male"
                    } else
                    {
                        textViewGender.text = "Female"
                    }
                }
            })
    }

    override fun onFabListener(v: View) {

        val action = FoodLogFragmentDirections.actionItemFoodLogToLogAMealFragment()
        Navigation.findNavController(v).navigate(action)
    }

}



