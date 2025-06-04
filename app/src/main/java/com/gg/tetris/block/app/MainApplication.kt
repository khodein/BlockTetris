package com.gg.tetris.block.app

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.gg.tetris.block.app.game.GameFragment
import com.gg.tetris.block.app.game.GameViewModel
import com.gg.tetris.block.app.game.command.figure_command.FigureCommand
import com.gg.tetris.block.app.game.command.figure_command.FigureCommandDelegate
import com.gg.tetris.block.app.game.command.figure_command.i_command.FigureIHCommand
import com.gg.tetris.block.app.game.command.figure_command.i_command.FigureIVCommand
import com.gg.tetris.block.app.game.command.figure_command.j_command.FigureJR0Command
import com.gg.tetris.block.app.game.command.figure_command.j_command.FigureJR180Command
import com.gg.tetris.block.app.game.command.figure_command.j_command.FigureJR270Command
import com.gg.tetris.block.app.game.command.figure_command.j_command.FigureJR90Command
import com.gg.tetris.block.app.game.command.figure_command.l_command.FigureLR0Command
import com.gg.tetris.block.app.game.command.figure_command.l_command.FigureLR180Command
import com.gg.tetris.block.app.game.command.figure_command.l_command.FigureLR270Command
import com.gg.tetris.block.app.game.command.figure_command.l_command.FigureLR90Command
import com.gg.tetris.block.app.game.command.figure_command.none_command.FigureNoneCommand
import com.gg.tetris.block.app.game.command.figure_command.o_command.FigureO2X2Command
import com.gg.tetris.block.app.game.command.figure_command.s_command.FigureSR0Command
import com.gg.tetris.block.app.game.command.figure_command.s_command.FigureSR90Command
import com.gg.tetris.block.app.game.command.figure_command.t_command.FigureTR0Command
import com.gg.tetris.block.app.game.command.figure_command.t_command.FigureTR180Command
import com.gg.tetris.block.app.game.command.figure_command.t_command.FigureTR270Command
import com.gg.tetris.block.app.game.command.figure_command.t_command.FigureTR90Command
import com.gg.tetris.block.app.game.command.figure_command.z_command.FigureZR0Command
import com.gg.tetris.block.app.game.command.figure_command.z_command.FigureZR90Command
import com.gg.tetris.block.app.game.mapper.GameAreaMapper
import com.gg.tetris.block.app.game.mapper.GameBitmapMapper
import com.gg.tetris.block.app.game.mapper.GameCoordinateMapper
import com.gg.tetris.block.app.game.mapper.GameRandomizerMapper
import com.gg.tetris.block.app.game.mapper.GameRefreshMapper
import com.gg.tetris.block.app.managers.ResManager
import com.gg.tetris.block.app.managers.RouterManager
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.fragment.dsl.fragmentOf
import org.koin.androidx.fragment.koin.fragmentFactory
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.factoryOf
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
                routerManagerModule,
                resourceModule,
                mappersModule,
                figureCommandModule,
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

private val routerManagerModule = module {
    singleOf(::RouterManager)
}

private val resourceModule = module {
    singleOf(::ResManager)
}

private val figureCommandModule = module {
    factory {
        val commands = buildList<FigureCommand> {
            add(FigureNoneCommand())

            add(FigureIHCommand())
            add(FigureIVCommand())

            add(FigureJR0Command())
            add(FigureJR90Command())
            add(FigureJR180Command())
            add(FigureJR270Command())

            add(FigureLR0Command())
            add(FigureLR90Command())
            add(FigureLR180Command())
            add(FigureLR270Command())

            add(FigureO2X2Command())

            add(FigureSR0Command())
            add(FigureSR90Command())

            add(FigureTR0Command())
            add(FigureTR90Command())
            add(FigureTR180Command())
            add(FigureTR270Command())

            add(FigureZR0Command())
            add(FigureZR90Command())
        }
        FigureCommandDelegate(
            gameBitmapMapper = get<GameBitmapMapper>(),
            commands = commands,
        )
    }
}

private val mappersModule = module {
    singleOf(::GameAreaMapper)
    singleOf(::GameBitmapMapper)
    singleOf(::GameRefreshMapper)
    singleOf(::GameCoordinateMapper )

    factoryOf(::GameRandomizerMapper)
}