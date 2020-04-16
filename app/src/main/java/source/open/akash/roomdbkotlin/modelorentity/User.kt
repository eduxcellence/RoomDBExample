package source.open.akash.roomdbkotlin.modelorentity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
/**
 * Created by Akash Kumar on 4/17/2020.
 * https://github.com/eduxcellence
 * akkr2017@gmail.com
 */
@Entity
class User : Serializable {
    //declaration of user table columns
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    var name: String? = null

    var mobileno: String? = null

    var dob: String? = null

    var password: String? = null


}