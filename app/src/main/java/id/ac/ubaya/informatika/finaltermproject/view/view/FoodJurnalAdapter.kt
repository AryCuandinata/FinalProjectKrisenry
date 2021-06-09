package id.ac.ubaya.informatika.finaltermproject.view.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import id.ac.ubaya.informatika.finaltermproject.R
import id.ac.ubaya.informatika.finaltermproject.databinding.FoodJurnalItemListBinding
import id.ac.ubaya.informatika.finaltermproject.view.model.FoodJurnal

class FoodjurnalListAdapter(val foodList:ArrayList<FoodJurnal>):RecyclerView.Adapter<FoodjurnalListAdapter.FoodJurnalViewHolder>() {

    class FoodJurnalViewHolder(var view: FoodJurnalItemListBinding) : RecyclerView.ViewHolder(view.root)

    fun updateFoodJurnalList(newFoodJurnalList:List<FoodJurnal>){
        foodList.clear()
        foodList.addAll(newFoodJurnalList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodJurnalViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = DataBindingUtil.inflate<FoodJurnalItemListBinding>(inflater, R.layout.food_jurnal_item_list, parent, false)
        return FoodJurnalViewHolder(v)
    }

    override fun onBindViewHolder(holder: FoodJurnalViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

}