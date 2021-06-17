package id.ac.ubaya.informatika.finaltermproject.view.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.ac.ubaya.informatika.finaltermproject.R
import id.ac.ubaya.informatika.finaltermproject.view.model.FoodLog
import id.ac.ubaya.informatika.finaltermproject.view.model.User
import kotlinx.android.synthetic.main.food_log_item_list.view.*
import kotlinx.android.synthetic.main.fragment_food_log.view.*

class FoodLogAdapter(val foodLogList: ArrayList<FoodLog>, val userList: ArrayList<User>):RecyclerView.Adapter<FoodLogAdapter.FoodLogViewHolder>() {

    class FoodLogViewHolder(val view: View):RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodLogViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.food_log_item_list, parent, false)
        return FoodLogViewHolder(v)
    }

    override fun getItemCount(): Int {
        return foodLogList.size
    }

    fun updateTodoList(newTodoList: List<FoodLog>) {
        foodLogList.clear()
        foodLogList.addAll(newTodoList)
        notifyDataSetChanged()
    }

    fun updateTodoListUser(newTodoListUser: List<User>) {
        userList.clear()
        userList.addAll(newTodoListUser)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: FoodLogViewHolder, position: Int) {
        holder.view.txtName.setText(foodLogList[position].meal.toString())
        holder.view.txtCalories.setText(foodLogList[position].calories.toString())
    }
}