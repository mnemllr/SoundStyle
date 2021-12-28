package me.mnemllr.soundstyle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.RequestManager
import dagger.hilt.android.AndroidEntryPoint
import me.mnemllr.soundstyle.util.Logger
import me.mnemllr.soundstyle.util.className
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity
@Inject constructor(
    private val glide: RequestManager
) : AppCompatActivity() {

    companion object {
        private val logger = Logger(this::class.className)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Logger.init()
        logger.info("onCreate")

        setContentView(R.layout.activity_main)
    }
}