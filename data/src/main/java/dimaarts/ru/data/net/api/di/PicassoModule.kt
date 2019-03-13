package dimaarts.ru.data.net.api.di

import android.content.Context
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PicassoModule {
        @Singleton
        @Provides
        fun picasso(context: Context): Picasso {
            return Picasso.Builder(context).build()
        }
}