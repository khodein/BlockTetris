package com.gg.tetris.block.app

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.gg.tetris.block.app.game.GameFragment
import com.gg.tetris.block.app.game.GameViewModel
import com.gg.tetris.block.app.resource.ResManager
import com.gg.tetris.block.app.router.Router
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.fragment.dsl.fragmentOf
import org.koin.androidx.fragment.koin.fragmentFactory
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            fragmentFactory()

            modules(
                routerModule,
                resourceModule,
                fragmentModule,
                viewModelModule,
            )
        }
    }
}

private val fragmentModule = module {
    fragmentOf(::GameFragment)
}

private val viewModelModule = module {
    viewModelOf(::GameViewModel)
}

private val routerModule = module {
    singleOf(::Router)
}

private val resourceModule = module {
    singleOf(::ResManager)
}