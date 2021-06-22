package id.ac.ubaya.informatika.finaltermproject.view.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import id.ac.ubaya.informatika.finaltermproject.R
import id.ac.ubaya.informatika.finaltermproject.databinding.FoodLogItemListBinding.inflate
import id.ac.ubaya.informatika.finaltermproject.databinding.FragmentProfileBinding
import id.ac.ubaya.informatika.finaltermproject.view.viewmodel.ListEditUser
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
    private lateinit var viewModel: ListEditUser
    private lateinit var databinding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        databinding = DataBindingUtil.inflate<FragmentProfileBinding>(inflater, R.layout.fragment_profile, container, false)
        return databinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ListEditUser::class.java)

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
        viewModel.userLD.observe(viewLifecycleOwner, Observer {  databinding.profile = it })
    }
}