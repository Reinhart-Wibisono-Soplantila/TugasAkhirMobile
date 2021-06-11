package id.ac.unhas.tesdata

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import id.ac.unhas.tesdata.db.RoomDbApp
import id.ac.unhas.tesdata.db.UserEntity

class MainActivityViewModel(app:Application): AndroidViewModel(app) {
    lateinit var allusers: MutableLiveData<List<UserEntity>>
    init{
        allusers = MutableLiveData()
        getAllUser()
    }

    fun getAllUsersObservers(): MutableLiveData<List<UserEntity>> {
        return allusers
    }
    fun getAllUser() {
        val userDao= RoomDbApp.getAppDatabase((getApplication()))?.userDao()
        val list = userDao?.getAllUserInfo()
        allusers.postValue(list)
    }

    fun insertUserInfo(entity: UserEntity){
        val userDao = RoomDbApp.getAppDatabase(getApplication())?.userDao()
        userDao?.insertUser(entity)
        getAllUser()
    }

    fun updateUserInfo(entity: UserEntity){
        val userDao = RoomDbApp.getAppDatabase(getApplication())?.userDao()
        userDao?.updateUser(entity)
        getAllUser()
    }

    fun deleteUserInfo(entity: UserEntity){
        val userDao = RoomDbApp.getAppDatabase(getApplication())?.userDao()
        userDao?.deleteUser(entity)
        getAllUser()
    }
}