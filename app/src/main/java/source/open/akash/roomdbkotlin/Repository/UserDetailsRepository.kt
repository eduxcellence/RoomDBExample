package source.open.akash.roomdbkotlin.Repository

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import source.open.akash.roomdbkotlin.dao.DaoAccess
import source.open.akash.roomdbkotlin.db.UserDatabase
import source.open.akash.roomdbkotlin.modelorentity.User

/**
 * Created by Akash Kumar on 4/17/2020.
 * https://github.com/eduxcellence
 * akkr2017@gmail.com
 */
class UserDetailsRepository(application: Application) {


    private var daoAccess: DaoAccess? = null
    private var allData: LiveData<List<User>>? = null

    init {
        //fetching user database
        val db = UserDatabase.getDatabase(application)
        daoAccess = db?.daoAccess()
        allData = daoAccess?.getDetails()

    }


    fun getAllData(): LiveData<List<User>>? {
        return allData
    }

    fun insertData(data: User) {
        daoAccess?.let { LoginInsertion(it).execute(data) }
    }

    private class LoginInsertion(private val daoAccess: DaoAccess) :
        AsyncTask<User, Void, Void>() {

        override fun doInBackground(vararg data: User): Void? {

            daoAccess.insertUserData(data[0])
            return null

        }

    }
}