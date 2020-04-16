package source.open.akash.roomdbkotlin.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_signup.*
import source.open.akash.roomdbkotlin.R
import source.open.akash.roomdbkotlin.db.UserDatabase
import source.open.akash.roomdbkotlin.modelorentity.User
import source.open.akash.roomdbkotlin.viewmodel.LoginViewModel
/**
 * Created by Akash Kumar on 4/17/2020.
 * https://github.com/eduxcellence
 * akkr2017@gmail.com
 */
class SignupActivity : AppCompatActivity() {
    /**
     * @param isExist  bool parameter for check existency of user or not in database
     */
    var isExist = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        //calling view mdioel object
        val userDetailsRepository = ViewModelProvider(this@SignupActivity).get(LoginViewModel::class.java)

        btn_login.setOnClickListener {
            val intent = Intent(this@SignupActivity, LoginActivity::class.java)
            // start your next activity
            startActivity(intent)
        }

        btn_register.setOnClickListener {
            if (validation()) {
                userDetailsRepository.getGetAllData().observe(this, object : Observer<List<User>> {
                    override fun onChanged(t: List<User>) {
                        var userObject = t

                        for (i in userObject.indices) {
                            Log.v("data :", userObject[i]?.mobileno)
                            // print("List  $userObject[i].mobileno")

                            if (userObject[i].mobileno?.equals(et_mobile_no.text.toString())!!) {
                                isExist = true
                                //Toast.makeText(this@SignupActivity," User Already Registered ", Toast.LENGTH_LONG).show()
                                break

                            } else {
                                isExist = false
                                continue

                            }
                        }

                        if (isExist) {
                            Toast.makeText(this@SignupActivity, " User Already Registered !!! ", Toast.LENGTH_LONG)
                                .show()

                        } else {

                            val user = User()
                            user.dob = et_dob.text.toString()
                            user.name = et_fullname.text.toString()
                            user.mobileno = et_mobile_no.text.toString()
                            user.password = et_password.text.toString()
                            val userDatabase = UserDatabase
                            userDatabase.getDatabase(this@SignupActivity)?.daoAccess()?.insertUserData(user)
                            Toast.makeText(this@SignupActivity, " User  Registered Successfully", Toast.LENGTH_LONG)
                                .show()


                        }

                    }

                })
            }

        }


    }

    /**
     * Attempts to register in  the account specified by the registration form.
     * If there are form errors (invalid name, missing fields, etc.), the
     * errors are presented in form of toast and no actual login attempt is made.
     */
    private fun validation(): Boolean {
        if (et_fullname.text.isNullOrEmpty()) {
            Toast.makeText(this@SignupActivity, " Enter Full Name ", Toast.LENGTH_LONG).show()
            return false
        }

        if (et_mobile_no.text.isNullOrEmpty()) {
            Toast.makeText(this@SignupActivity, " Enter Mobile Number ", Toast.LENGTH_LONG).show()
            return false
        }
        if (et_mobile_no.text.toString().length != 10) {
            Toast.makeText(this@SignupActivity, " Enter 10 digit Mobile Number ", Toast.LENGTH_LONG).show()
            return false
        }
        if (et_password.text.isNullOrEmpty()) {
            Toast.makeText(this@SignupActivity, " Enter Password ", Toast.LENGTH_LONG).show()
            return false
        }
        return true
    }

}
