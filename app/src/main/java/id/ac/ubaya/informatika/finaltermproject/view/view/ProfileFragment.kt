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
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import id.ac.ubaya.informatika.finaltermproject.R
import id.ac.ubaya.informatika.finaltermproject.databinding.FoodLogItemListBinding.inflate
import id.ac.ubaya.informatika.finaltermproject.databinding.FragmentProfileBinding
import id.ac.ubaya.informatika.finaltermproject.view.model.User
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
import kotlinx.android.synthetic.main.fragment_welcome.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

class ProfileFragment : Fragment(), ProfileSaveChangesClick,RadioClick{
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

        databinding.radioListener = this
        databinding.listener = this

        observeViewModel()
    }

    override fun onRadioClick(v: View, gender: Int, obj: User) {
        obj.gender = gender
    }

    fun observeViewModel() {
        viewModel.userLD.observe(viewLifecycleOwner, Observer {  databinding.profile = it })
    }

    override fun onProfileSaveChangesClick(v: View, obj: User) {
        if(textInputAge.text.toString() == "" || textInputName.text.toString() == "" || textInputWeight.text.toString() == "" || textInputHeight.text.toString() == ""){
            Toast.makeText(v.context, "Cannot Update Profile", Toast.LENGTH_SHORT).show()
        } else {
            var calories:Double
            Log.d("weight2",obj.weight.toString())
            if(obj.gender.toString().toInt() == 1)
            {
                calories =
                    13.397 * obj.weight.toString().toDouble() + 4.799 * obj.height.toString().toDouble() - 5.677 * obj.age.toString().toDouble() + 88.362
            }
            else
            {
                calories =
                    9.247 * obj.weight.toString().toDouble() + obj.height.toString().toDouble() - 4.330 * obj.age.toString().toDouble() + 447.593
            }
            viewModel.update(obj.name.toString(), obj.age.toString().toInt(), obj.gender.toString().toInt(), obj.weight.toString().toInt(),
                   obj.height.toString().toInt() , calories.toInt(), obj.uuid)
            Log.d("weight1",obj.weight.toString())
            Toast.makeText(v.context, "Profile Updated", Toast.LENGTH_SHORT).show()
        }
    }
}