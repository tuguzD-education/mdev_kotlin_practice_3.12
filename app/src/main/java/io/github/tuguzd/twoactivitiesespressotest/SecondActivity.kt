package io.github.tuguzd.twoactivitiesespressotest

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.github.tuguzd.twoactivitiesespressotest.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_REPLY = "ru.samsung.itacademy.mdev.twoactivitiesespressotest.REPLY"
    }

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE)
        binding.textMessage.text = message

        binding.buttonSecond.setOnClickListener {
            val reply = binding.editTextSecond.text.toString()
            val replyIntent = Intent().apply {
                putExtra(EXTRA_REPLY, reply)
            }
            setResult(RESULT_OK, replyIntent)
            finish()
        }
    }
}
