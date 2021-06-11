package id.ac.unhas.tesdata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager.VERTICAL
import id.ac.unhas.tesdata.db.UserEntity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.jar.Attributes

class MainActivity : AppCompatActivity(), RecyclerViewAdapter.RowClickListener {

    lateinit var recyclerViewAdapter: RecyclerViewAdapter
    lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            recyclerViewAdapter = RecyclerViewAdapter(this@MainActivity)
            adapter=recyclerViewAdapter
            val divider = DividerItemDecoration(applicationContext, VERTICAL)
            addItemDecoration(divider)
        }
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        viewModel.getAllUsersObservers().observe(this, Observer {
            recyclerViewAdapter.setListData(ArrayList(it))
            recyclerViewAdapter.notifyDataSetChanged()
        })

        SaveButton.setOnClickListener{
            val name = NameInput.text.toString()
            val email = EmailInput.text.toString()

            if(SaveButton.text.equals("Save")) {
                val user = UserEntity(0, name, email)
                viewModel.insertUserInfo(user)
            } else {
                val user = UserEntity(NameInput.getTag(NameInput.id).toString().toInt(), name, email)
                viewModel.updateUserInfo(user)
                SaveButton.setText("Save")
            }
            NameInput.setText("")
            EmailInput.setText("")
        }
    }

    override fun onDeleteUserClickListener(user: UserEntity) {
        viewModel.deleteUserInfo(user)
    }

    override fun onItemClickListener(user: UserEntity) {
        NameInput.setText(user.name)
        EmailInput.setText(user.email)
        NameInput.setTag(NameInput.id, user.id)
        SaveButton.setText("Update")
    }
}