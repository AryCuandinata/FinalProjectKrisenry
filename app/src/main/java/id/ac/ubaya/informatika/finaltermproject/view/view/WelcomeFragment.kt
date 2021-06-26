package id.ac.ubaya.informatika.finaltermproject.view.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import id.ac.ubaya.informatika.finaltermproject.R
import id.ac.ubaya.informatika.finaltermproject.databinding.FragmentWelcomeBinding


import id.ac.ubaya.informatika.finaltermproject.view.model.User
import id.ac.ubaya.informatika.finaltermproject.view.viewmodel.ListUserViewModel
import kotlinx.android.synthetic.main.fragment_welcome.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

class WelcomeFragment : Fragment(),StartJourneyClick{
    private lateinit var viewModel:ListUserViewModel
    private lateinit var databinding:FragmentWelcomeBinding
    var radio1 : Int = 0
    var radio2 : Int = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        databinding = DataBindingUtil.inflate<FragmentWelcomeBinding>(inflater, R.layout.fragment_welcome, container, false)
        return databinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(ListUserViewModel::class.java)
        databinding.listener = this
    }

     override fun onStartJourneyClick(view: View) {

         if (textInputName.text.toString() == "" || textInputAge.text.toString() == "" || textInputWeight.text.toString() == "" || textInputHeight.text.toString() == ""){
             Toast.makeText(view.context, "Please fill the text box ", Toast.LENGTH_LONG).show()
         } else {
             var calories: Double
             if(radioMale.isChecked)
             {
                 radio2 = 1
                 calories =
                     13.397 * textInputWeight.text.toString().toDouble() + 4.799 * textInputHeight.text.toString().toDouble() - 5.677 * textInputAge.text.toString().toDouble() + 88.362
             }
             else
             {
                 radio2 = 2
                 calories =
                     9.247 * textInputWeight.text.toString().toDouble() + textInputHeight.text.toString().toDouble() - 4.330 * textInputAge.text.toString().toDouble() + 447.593
             }
             if(radioButtonGainWeight.isChecked)
             {
                 radio1 = 2
                 calories += (calories * 15 / 100)
             }
             else if(radioButtonLossWeight.isChecked)
             {
                 radio1 = 3
                 calories -= (calories * 15 / 100)
             }
             else
             {
                 radio1 = 1
             }

             var model = User(textInputName.text.toString(), textInputAge.text.toString().toInt(), radio2.toString().toInt(), textInputWeight.text.toString().toInt(), textInputHeight.text.toString().toInt(), radio1.toString().toInt(), calories.toInt())
             val list = listOf(model)
             viewModel.insertUser(list)
             Toast.makeText(view.context, "Data added", Toast.LENGTH_LONG).show()

             val action = WelcomeFragmentDirections.actionWelcomeFragmentToItemFoodLog2()
             Navigation.findNavController(view).navigate(action)
         }


    }




}