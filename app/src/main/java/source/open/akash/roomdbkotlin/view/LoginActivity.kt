package source.open.akash.roomdbkotlin.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_login.*
import source.open.akash.roomdbkotlin.R
import source.open.akash.roomdbkotlin.modelorentity.User
import source.open.akash.roomdbkotlin.viewmodel.LoginViewModel

/**
 * Created by Akash Kumar on 4/17/2020.
 * https://github.com/eduxcellence
 * akkr2017@gmail.com
 */
class LoginActivity : AppCompatActivity() {
    /**
     * @param isExist  bool parameter for check existency of user or not in database
     */
    var isExist = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        //calling view mdioel object
        val userDetailsRepository = ViewModelProvider(this).get(LoginViewModel::class.java)

        btn_register.setOnClickListener {
            val intent = Intent(this@LoginActivity, SignupActivity::class.java)
            // start your next activity
            startActivity(intent)
        }


        btn_login.setOnClickListener {
            if (validation()) {


                userDetailsRepository.getGetAllData().observe(this, object : Observer<List<User>> {
                    override fun onChanged(t: List<User>) {
                        var userObject = t

                        for (i in userObject.indices) {
                            if (userObject[i].mobileno?.equals(et_mobile_no.text.toString())!!) {

                                if (userObject[i].password?.equals(et_password.text.toString())!!) {

                                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                                        .putExtra("UserDetials", userObject[i])
                                    // start your next activity
                                    startActivity(intent)

                                } else {
                                    Toast.makeText(this@LoginActivity, " Password is Incorrect ", Toast.LENGTH_LONG)
                                        .show()
                                }
                                isExist = true
                                break

                            } else {
                                isExist = false
                            }
                        }

                        if (isExist) {

                        } else {

                            Toast.makeText(this@LoginActivity, " User Not Registered ", Toast.LENGTH_LONG).show()
                        }

                    }

                })
            }
        }


    }

    /**
     * Attempts to sign in  the account specified by the login form.
     * If there are form errors (invalid mobile, missing fields, etc.), the
     * errors are presented in form of toast and no actual login attempt is made.
     */
    private fun validation(): Boolean {

        if (et_mobile_no.text.isNullOrEmpty()) {
            Toast.makeText(this@LoginActivity, " Enter Mobile Number ", Toast.LENGTH_LONG).show()
            return false
        }
        if (et_mobile_no.text.toString().length != 10) {
            Toast.makeText(this@LoginActivity, " Enter 10 digit Mobile Number ", Toast.LENGTH_LONG).show()
            return false
        }
        if (et_password.text.isNullOrEmpty()) {
            Toast.makeText(this@LoginActivity, " Enter Password ", Toast.LENGTH_LONG).show()
            return false
        }
        return true
    }


}

