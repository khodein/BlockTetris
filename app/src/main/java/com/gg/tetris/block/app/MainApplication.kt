package com.gg.tetris.block.app

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.gg.tetris.block.app.game.GameFragment
import com.gg.tetris.block.app.game.GameViewModel
import com.gg.tetris.block.app.game.mapper.GameAreaUiMapper
import com.gg.tetris.block.app.game.mapper.GameBitmapUiMapper
import com.gg.tetris.block.app.game.mapper.GameCoordinateMapper
import com.gg.tetris.block.app.game.mapper.figure_mapper.FigureUiMapper
import com.gg.tetris.block.app.game.mapper.GameRefreshBlocksUiMapper
import com.gg.tetris.block.app.game.mapper.figure_mapper.i_mapper.FigureICommandMapper
import com.gg.tetris.block.app.game.mapper.figure_mapper.j_mapper.FigureJCommandMapper
import com.gg.tetris.block.app.game.mapper.figure_mapper.l_mapper.FigureLCommandMapper
import com.gg.tetris.block.app.game.mapper.figure_mapper.o_mapper.FigureOCommandMapper
import com.gg.tetris.block.app.game.mapper.figure_mapper.s_mapper.FigureSCommandMapper
import com.gg.tetris.block.app.game.mapper.figure_mapper.t_mapper.FigureTCommandMapper
import com.gg.tetris.block.app.game.mapper.figure_mapper.z_mapper.FigureZCommandMapper
import com.gg.tetris.block.app.game.mapper.GameRandomizerMapper
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
                mappersModule,
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

private val mappersModule = module {
    singleOf(::GameAreaUiMapper)
    singleOf(::GameBitmapUiMapper)
    singleOf(::GameRefreshBlocksUiMapper)
    singleOf(::GameRandomizerMapper)
    singleOf(::GameCoordinateMapper )

    singleOf(::FigureICommandMapper)
    singleOf(::FigureJCommandMapper)
    singleOf(::FigureLCommandMapper)
    singleOf(::FigureOCommandMapper)
    singleOf(::FigureSCommandMapper)
    singleOf(::FigureZCommandMapper)
    singleOf(::FigureTCommandMapper)
    singleOf(::FigureUiMapper)
}