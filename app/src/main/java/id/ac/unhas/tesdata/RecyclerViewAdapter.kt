package id.ac.unhas.tesdata

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.ac.unhas.tesdata.db.UserEntity
import kotlinx.android.synthetic.main.recyclerview_row.view.*

class RecyclerViewAdapter(val listener: RowClickListener): RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

    var items = ArrayList<UserEntity>()

    fun setListData(data: ArrayList<UserEntity>){
        this.items = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_row, parent, false)
        return MyViewHolder(inflater,listener)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            listener.onItemClickListener(items[position])
        }
        holder.bind(items[position])
    }


    class MyViewHolder(view: View, val listener: RowClickListener): RecyclerView.ViewHolder(view){
        val tvName = view.tvName
        val tvEmail = view.tvEmail
        val deleteUserID = view.deleteUser
        fun bind( data: UserEntity){
            tvEmail.text = data.email
            tvName.text = data.name
            deleteUserID.setOnClickListener{
                listener.onDeleteUserClickListener(data)
            }
        }
    }
     interface RowClickListener{
         fun onDeleteUserClickListener(user: UserEntity)
         fun onItemClickListener(user: UserEntity)
     }
}