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
        observeViewModel()
    }
    override fun onRadioClick(v: View, gender: Int, obj: User) {
        obj.gender = gender
    }

    fun observeViewModel() {
        viewModel.userLD.observe(viewLifecycleOwner, Observer {  databinding.profile = it })
    }

    override fun onProfileSaveChangesClick(v: View, obj: User) {
        viewModel.update(obj.name.toString(), obj.age.toString().toInt(), obj.gender.toString().toInt(), obj.weight.toString().toInt(),
                obj.height.toString().toInt() , obj.uuid)
        Toast.makeText(v.context, "Todo Updated", Toast.LENGTH_SHORT).show()
    }
}