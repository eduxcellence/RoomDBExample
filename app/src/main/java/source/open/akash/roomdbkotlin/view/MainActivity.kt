package source.open.akash.roomdbkotlin.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import source.open.akash.roomdbkotlin.R
import source.open.akash.roomdbkotlin.modelorentity.User
/**
 * Created by Akash Kumar on 4/17/2020.
 * https://github.com/eduxcellence
 * akkr2017@gmail.com
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //fetching user details from intent object
        val UserDetials = intent.getSerializableExtra("UserDetials") as? User

        //show user details in the mainactivity screen ui
        tv_fullname.text = UserDetials?.name
        tv_mobileno.text = UserDetials?.mobileno
        tv_dob.text = UserDetials?.dob
    }
}
