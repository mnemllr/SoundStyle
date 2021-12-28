package me.mnemllr.soundstyle.musicservice

import android.app.PendingIntent
import android.os.Bundle
import android.support.v4.media.MediaBrowserCompat
import android.support.v4.media.session.MediaSessionCompat
import androidx.media.MediaBrowserServiceCompat
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ext.mediasession.MediaSessionConnector
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import me.mnemllr.soundstyle.util.Logger
import me.mnemllr.soundstyle.util.className
import javax.inject.Inject

class MusicService
    @Inject constructor(
        private val exoPlayer: SimpleExoPlayer,
        private val dataSourceFactory: DefaultDataSourceFactory
    ): MediaBrowserServiceCompat() {

    companion object {
        private val logger = Logger(this::class.className)
    }

    private val SERVICE_TAG = "${this::class.simpleName}"
    private lateinit var mediaSession: MediaSessionCompat
    private lateinit var mediaSessionConnector: MediaSessionConnector
    private val serviceJob = Job()
    private val serviceScope = CoroutineScope(Dispatchers.Main + serviceJob)

    override fun onCreate() {
        super.onCreate()
        logger.info("onCreate")

        val pendingIntent = packageManager.getLaunchIntentForPackage(packageName)?.let {
            PendingIntent.getActivity(this, 0, it, 0)
        }
        mediaSession = MediaSessionCompat(this, SERVICE_TAG).apply {
            setSessionActivity(pendingIntent)
            isActive = true
        }
        sessionToken = mediaSession.sessionToken
        mediaSessionConnector = MediaSessionConnector(mediaSession).apply {
            setPlayer(exoPlayer)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        logger.info("onDestroy")
        serviceScope.cancel()
    }

    override fun onGetRoot(
        clientPackageName: String,
        clientUid: Int,
        rootHints: Bundle?
    ): BrowserRoot? {
        logger.info("onGetRoot")
        TODO("Not yet implemented")
    }

    override fun onLoadChildren(
        parentId: String,
        result: Result<MutableList<MediaBrowserCompat.MediaItem>>
    ) {
        logger.info("onLoadChildren")
        TODO("Not yet implemented")
    }

}
