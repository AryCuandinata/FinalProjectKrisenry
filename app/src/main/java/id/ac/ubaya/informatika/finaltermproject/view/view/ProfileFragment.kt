package id.ac.ubaya.informatika.finaltermproject.view.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import id.ac.ubaya.informatika.finaltermproject.R
import id.ac.ubaya.informatika.finaltermproject.view.viewmodel.ListUserViewModel
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_profile.radioFemale
import kotlinx.android.synthetic.main.fragment_profile.radioGroupGenderWellcome
import kotlinx.android.synthetic.main.fragment_profile.radioMale
import kotlinx.android.synthetic.main.fragment_profile.textInputAge
import kotlinx.android.synthetic.main.fragment_profile.textInputHeight
import kotlinx.android.synthetic.main.fragment_profile.textInputName
import kotlinx.android.synthetic.main.fragment_profile.textInputWeight

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

class ProfileFragment : Fragment() {
    private lateinit var viewModel: ListUserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ListUserViewModel::class.java)

        viewModel.fetch(1)

        buttonUpdateProfile.setOnClickListener {
            val radio = view.findViewById<RadioButton>(radioGroupGenderWellcome.checkedRadioButtonId)
            viewModel.update(textInputName.text.toString(), textInputAge.text.toString().toInt(),
                    radio.tag.toString(), textInputWeight.text.toString().toInt(), textInputHeight.text.toString().toInt(),1)
            Toast.makeText(view.context, "Todo updated", Toast.LENGTH_SHORT).show()

        }

        observeViewModel()

    }

    fun observeViewModel() {
        viewModel.userLD.observe(viewLifecycleOwner, Observer {
            textInputName.setText(it[0].name.toString())
            textInputAge.setText(it[0].age.toString())
            textInputHeight.setText(it[0].height.toString())
            textInputWeight.setText(it[0].height.toString())

            when (it[0].gender){
                "Male"-> radioMale.isChecked = true
                "Female"-> radioFemale.isChecked = true
            }
        })
    }


}