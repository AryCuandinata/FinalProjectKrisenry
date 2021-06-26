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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        databinding = DataBindingUtil.inflate<FragmentWelcomeBinding>(inflater, R.layout.fragment_welcome, container, false)
        return databinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(ListUserViewModel::class.java)
        databinding.listener = this
        /*
        buttonStart.setOnClickListener {
            val radio = view.findViewById<RadioButton>(radioGroup.checkedRadioButtonId)
            val radioGender = view.findViewById<RadioButton>(radioGroupGenderWellcome.checkedRadioButtonId)

            var model = User(textInputName.text.toString(), textInputAge.text.toString().toInt(), radioGender.tag.toString().toInt(), textInputWeight.text.toString().toInt(), textInputHeight.text.toString().toInt(), radio.tag.toString().toInt())
            val list = listOf(model)
            viewModel.insertUser(list)
            Toast.makeText(view.context, "Data added", Toast.LENGTH_LONG).show()

            val action = WelcomeFragmentDirections.actionWelcomeFragmentToItemFoodLog2()
            Navigation.findNavController(view).navigate(action)
        }*/

    }

    override fun onStartJourneyClick(view: View) {
        val radio = view.findViewById<RadioButton>(radioGroup.checkedRadioButtonId)
        val radioGender = view.findViewById<RadioButton>(radioGroupGenderWellcome.checkedRadioButtonId)

        var model = User(textInputName.text.toString(), textInputAge.text.toString().toInt(), radioGender.tag.toString().toInt(), textInputWeight.text.toString().toInt(), textInputHeight.text.toString().toInt(), radio.tag.toString().toInt())
        val list = listOf(model)
        viewModel.insertUser(list)
        Toast.makeText(view.context, "Data added", Toast.LENGTH_LONG).show()

        val action = WelcomeFragmentDirections.actionWelcomeFragmentToItemFoodLog2()
        Navigation.findNavController(view).navigate(action)
    }



}