package me.mnemllr.soundstyle.common.di

import android.content.Context
import com.google.android.exoplayer2.C.CONTENT_TYPE_MUSIC
import com.google.android.exoplayer2.C.USAGE_MEDIA
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.audio.AudioAttributes
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ServiceComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ServiceScoped

@Module
@InstallIn(ServiceComponent::class)
object ServiceModule {

    @ServiceScoped
    @Provides
    fun provideAudioAttributes(): AudioAttributes {
        return AudioAttributes.Builder()
            .setContentType(CONTENT_TYPE_MUSIC)
            .setUsage(USAGE_MEDIA)
            .build()
    }

    @ServiceScoped
    @Provides
    fun provideSimpleExoPlayer(
        @ApplicationContext context: Context,
        audioAttributes: AudioAttributes
    ): SimpleExoPlayer {
        return SimpleExoPlayer.Builder(context).build().apply {
            setAudioAttributes(audioAttributes, true)
            setHandleAudioBecomingNoisy(true)
        }
    }

    fun provideExoPlayerDataSourceFactory(
        @ApplicationContext context: Context
    ): DefaultDataSourceFactory {
        return DefaultDataSourceFactory(
            context,
            Util.getUserAgent(context, "SoundStyleApp")
        )
    }
}
