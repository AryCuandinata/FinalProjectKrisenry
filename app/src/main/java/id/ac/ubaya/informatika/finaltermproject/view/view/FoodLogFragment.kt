package id.ac.ubaya.informatika.finaltermproject.view.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import id.ac.ubaya.informatika.finaltermproject.R
import id.ac.ubaya.informatika.finaltermproject.view.viewmodel.ListFoodLogViewModel
import kotlinx.android.synthetic.main.fragment_food_log.*


class FoodLogFragment : Fragment() {
    private lateinit var viewModel: ListFoodLogViewModel
    private val foodLogAdapter  = FoodLogAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_food_log, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel  = ViewModelProvider(this).get(ListFoodLogViewModel::class.java)
        viewModel.refresh()
        recViewFoodLog.layoutManager = LinearLayoutManager(context)
        recViewFoodLog.adapter = foodLogAdapter

        fabButton.setOnClickListener {
            val action = FoodLogFragmentDirections.actionItemFoodLogToLogAMealFragment()
            Navigation.findNavController(it).navigate(action)
        }

        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.foodLD.observe(
                viewLifecycleOwner, Observer {
                    foodLogAdapter.updateTodoList(it)
                    if(it.isEmpty()) {
                        txtError.visibility = View.VISIBLE
                    } else { txtError.visibility = View.GONE }
                })
    }

}