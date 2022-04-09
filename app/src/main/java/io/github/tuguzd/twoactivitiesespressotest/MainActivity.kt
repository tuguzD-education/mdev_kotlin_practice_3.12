package io.github.tuguzd.twoactivitiesespressotest

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import io.github.tuguzd.twoactivitiesespressotest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    companion object {
        private const val LOG_TAG = "MainActivity"
        const val EXTRA_MESSAGE = "ru.samsung.itacademy.mdev.twoactivitiesespressotest.MESSAGE"
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            // Test to make sure the intent reply result was good.
            if (it.resultCode == RESULT_OK) {
                val reply = it.data?.getStringExtra(SecondActivity.EXTRA_REPLY)

                // Make the reply head visible.
                binding.textHeaderReply.visibility = View.VISIBLE

                // Set the reply and make it visible.
                binding.textMessageReply.text = reply
                binding.textMessageReply.visibility = View.VISIBLE
            }
        }

        binding.buttonMain.setOnClickListener {
            Log.d(LOG_TAG, "Button clicked!")
            val intent = Intent(this, SecondActivity::class.java)
            val message = binding.editTextMain.text.toString()
            intent.putExtra(EXTRA_MESSAGE, message)
            launcher.launch(intent)
        }
    }
}
