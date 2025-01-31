package me.mnemllr.soundstyle.common.di

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import me.mnemllr.soundstyle.R
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object ApplicationModule {

    @Singleton
    @Provides
    fun provideGlideInstance(@ApplicationContext context: Context): RequestManager {
        return Glide.with(context)
            .setDefaultRequestOptions(
                RequestOptions()
                    .placeholder(R.drawable.ic_image)
                    .error(R.drawable.ic_image)
                    .diskCacheStrategy(DiskCacheStrategy.DATA)
        )
    }
}
