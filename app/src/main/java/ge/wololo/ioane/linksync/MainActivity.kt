package ge.wololo.ioane.linksync

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Access a Cloud Firestore instance from your Activity
        val db = FirebaseFirestore.getInstance()
        db.collection("links").get().addOnSuccessListener { result ->
            result.documents
                .mapNotNull { it.data?.get("url") }
                .joinToString(separator = "\n")
                .let { urlView.text = it }
        }
    }
}
