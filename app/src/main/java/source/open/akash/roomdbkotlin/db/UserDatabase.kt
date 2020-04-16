package source.open.akash.roomdbkotlin.db

import android.content.Context
import androidx.room.Database
import source.open.akash.roomdbkotlin.dao.DaoAccess
import androidx.room.RoomDatabase
import source.open.akash.roomdbkotlin.modelorentity.User
import androidx.room.Room


/**
 * Created by Akash Kumar on 4/17/2020.
 * https://github.com/eduxcellence
 * akkr2017@gmail.com
 */

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {


    abstract fun daoAccess(): DaoAccess

    companion object {

        private var INSTANCE: UserDatabase? = null
        fun getDatabase(context: Context): UserDatabase? {

            if (INSTANCE == null) synchronized(UserDatabase::class.java) {

                if (INSTANCE == null) {

                    INSTANCE = Room.databaseBuilder(
                        context, UserDatabase::class.java, "USER_DATABASE"
                    ).allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()

                }

            }

            return INSTANCE

        }
    }
}