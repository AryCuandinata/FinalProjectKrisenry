package id.ac.ubaya.informatika.finaltermproject.view.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.google.android.material.bottomnavigation.BottomNavigationView
import id.ac.ubaya.informatika.finaltermproject.R
import id.ac.ubaya.informatika.finaltermproject.view.model.FoodLog
import id.ac.ubaya.informatika.finaltermproject.view.model.User
import id.ac.ubaya.informatika.finaltermproject.view.viewmodel.ListFoodLogViewModel
import id.ac.ubaya.informatika.finaltermproject.view.viewmodel.ListUserViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_welcome.*
import kotlinx.android.synthetic.main.fragment_log_a_meal.*
import kotlinx.android.synthetic.main.fragment_welcome.*
import kotlinx.android.synthetic.main.fragment_welcome.radioGroup
import kotlinx.android.synthetic.main.fragment_welcome.textInputAge
import kotlinx.android.synthetic.main.fragment_welcome.textInputGender
import kotlinx.android.synthetic.main.fragment_welcome.textInputHeight
import kotlinx.android.synthetic.main.fragment_welcome.textInputName
import kotlinx.android.synthetic.main.fragment_welcome.textInputWeight
import java.text.SimpleDateFormat

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

class WelcomeFragment : Fragment() {
    private lateinit var viewModel:ListUserViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_welcome, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(ListUserViewModel::class.java)
        val radio = view.findViewById<RadioButton>(radioGroup.checkedRadioButtonId)
        buttonStart.setOnClickListener {
            var model = User(textInputName.text.toString(), textInputAge.text.toString().toInt(), textInputGender.text.toString(), textInputWeight.text.toString().toInt(), textInputHeight.text.toString().toInt(), radio.tag.toString().toInt())
            val list = listOf(model)
            viewModel.insertUser(list)
            Toast.makeText(view.context, "Data added", Toast.LENGTH_LONG).show()
            //Navigation.findNavController(it).popBackStack()

            val action = WelcomeFragmentDirections.actionWelcomeFragmentToItemFoodLog2()
            Navigation.findNavController(view).navigate(action)
        }
    }
}