package id.ac.ubaya.informatika.finaltermproject.view.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import id.ac.ubaya.informatika.finaltermproject.R
import id.ac.ubaya.informatika.finaltermproject.databinding.FoodLogItemListBinding
import id.ac.ubaya.informatika.finaltermproject.view.viewmodel.ListFoodLogViewModel
import id.ac.ubaya.informatika.finaltermproject.view.viewmodel.ListUserViewModel
import kotlinx.android.synthetic.main.fragment_food_log.*
import kotlinx.android.synthetic.main.fragment_food_log.view.*
import kotlinx.android.synthetic.main.fragment_log_a_meal.view.*
import kotlin.math.roundToInt


class FoodLogFragment : Fragment() {

    private lateinit var viewModel: ListFoodLogViewModel
    private lateinit var viewModelUser: ListUserViewModel
    private val foodLogAdapter = FoodLogAdapter(arrayListOf(), arrayListOf())

    var value = false
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_food_log, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModelUser = ViewModelProvider(this).get(ListUserViewModel::class.java)
        viewModelUser.refresh()

        viewModel = ViewModelProvider(this).get(ListFoodLogViewModel::class.java)
        viewModel.refresh()

        recViewFoodLog.layoutManager = LinearLayoutManager(context)
        recViewFoodLog.adapter = foodLogAdapter

        textViewCal.text = "0 Cal"

        fabButton.setOnClickListener {
            val action = FoodLogFragmentDirections.actionItemFoodLogToLogAMealFragment()
            Navigation.findNavController(it).navigate(action)
        }

        observeViewModel()
        observeViewModelUser()
    }

    fun observeViewModel() {
        viewModel.foodLD.observe(
            viewLifecycleOwner, Observer {
                foodLogAdapter.updateTodoList(it)
                if (it.isEmpty()) {
                    txtError.visibility = View.VISIBLE

                } else {
                    txtError.visibility = View.GONE

                    var currCalories = 0
                    for (i in it) {
                        var index = 0
                        currCalories += it[index].calories!!.toInt()
                        index++
                    }
                    textViewCal.text = currCalories.toString() + " Cal"
                }
            })
    }

    fun observeViewModelUser() {
        viewModelUser.userLD.observe(
            viewLifecycleOwner, Observer {
                foodLogAdapter.updateTodoListUser(it)
                if (it.isEmpty()) {
                    val action = FoodLogFragmentDirections.actionItemFoodLogToWelcomeFragment()
                    Navigation.findNavController(requireView()).navigate(action)
                } else {
                    textViewGender.setText((it[0].gender.toString()))
                    textViewAge.setText((it[0].age.toString()))
                    textViewName.setText((it[0].name.toString()))

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
                    textViewCal2.text = calories.roundToInt().toString() + " Cal"
                }
            })
    }

}



