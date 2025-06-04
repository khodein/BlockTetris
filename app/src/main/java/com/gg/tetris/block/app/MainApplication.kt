package com.gg.tetris.block.app

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.gg.tetris.block.app.game.gameModule
import com.gg.tetris.block.app.managers.managersModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.fragment.koin.fragmentFactory
import org.koin.core.context.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            fragmentFactory()

            modules(
                managersModule,
                gameModule,
            )
        }
    }
}