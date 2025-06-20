package com.gg.tetris.block.app

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.gg.tetris.block.app.game.GameFragment
import com.gg.tetris.block.app.game.GameViewModel
import com.gg.tetris.block.app.game.command.figure.FigureCommand
import com.gg.tetris.block.app.game.command.figure.FigureState
import com.gg.tetris.block.app.game.command.figure.i_command.FigureIHCommand
import com.gg.tetris.block.app.game.command.figure.i_command.FigureIVCommand
import com.gg.tetris.block.app.game.command.figure.j_command.FigureJR0Command
import com.gg.tetris.block.app.game.command.figure.j_command.FigureJR180Command
import com.gg.tetris.block.app.game.command.figure.j_command.FigureJR270Command
import com.gg.tetris.block.app.game.command.figure.j_command.FigureJR90Command
import com.gg.tetris.block.app.game.command.figure.l_command.FigureLR0Command
import com.gg.tetris.block.app.game.command.figure.l_command.FigureLR180Command
import com.gg.tetris.block.app.game.command.figure.l_command.FigureLR270Command
import com.gg.tetris.block.app.game.command.figure.l_command.FigureLR90Command
import com.gg.tetris.block.app.game.command.figure.none_command.FigureNoneCommand
import com.gg.tetris.block.app.game.command.figure.o_command.FigureO2X2Command
import com.gg.tetris.block.app.game.command.figure.s_command.FigureSR0Command
import com.gg.tetris.block.app.game.command.figure.s_command.FigureSR90Command
import com.gg.tetris.block.app.game.command.figure.t_command.FigureTR0Command
import com.gg.tetris.block.app.game.command.figure.t_command.FigureTR180Command
import com.gg.tetris.block.app.game.command.figure.t_command.FigureTR270Command
import com.gg.tetris.block.app.game.command.figure.t_command.FigureTR90Command
import com.gg.tetris.block.app.game.command.figure.z_command.FigureZR0Command
import com.gg.tetris.block.app.game.command.figure.z_command.FigureZR90Command
import com.gg.tetris.block.app.game.manager.bitmap.GameBitmapManager
import com.gg.tetris.block.app.game.manager.figure.GameFigureManager
import com.gg.tetris.block.app.game.manager.params.GameParamsManager
import com.gg.tetris.block.app.game.manager.random.GameRandomizerManager
import com.gg.tetris.block.app.game.mapper.GameAreaMapper
import com.gg.tetris.block.app.game.mapper.GameCoordinateMapper
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

            val managersModule = module {
                singleOf(::RouterManager)
                singleOf(::ResManager)
            }

            val gameModule = module {
                fragmentOf(::GameFragment)
                viewModelOf(::GameViewModel)

                factory {
                    val commands = hashMapOf<String, FigureCommand>()

                    val newListEntries = FigureState.entries.toMutableList()
                    newListEntries.add(FigureState.None)

                    newListEntries.forEach {
                        val command = when (it) {
                            is FigureState.None -> FigureNoneCommand()
                            is FigureState.I.H -> FigureIHCommand()
                            is FigureState.I.V -> FigureIVCommand()

                            is FigureState.J.R0 -> FigureJR0Command()
                            is FigureState.J.R90 -> FigureJR90Command()
                            is FigureState.J.R180 -> FigureJR180Command()
                            is FigureState.J.R270 -> FigureJR270Command()

                            is FigureState.L.R0 -> FigureLR0Command()
                            is FigureState.L.R90 -> FigureLR90Command()
                            is FigureState.L.R180 -> FigureLR180Command()
                            is FigureState.L.R270 -> FigureLR270Command()

                            is FigureState.O.X2X2 -> FigureO2X2Command()

                            is FigureState.S.R0 -> FigureSR0Command()
                            is FigureState.S.R90 -> FigureSR90Command()

                            is FigureState.T.R0 -> FigureTR0Command()
                            is FigureState.T.R90 -> FigureTR90Command()
                            is FigureState.T.R180 -> FigureTR180Command()
                            is FigureState.T.R270 -> FigureTR270Command()

                            is FigureState.Z.R0 -> FigureZR0Command()
                            is FigureState.Z.R90 -> FigureZR90Command()
                        }

                        commands[it.ownerId] = command
                    }

                    GameFigureManager(
                        gameBitmapManager = get<GameBitmapManager>(),
                        gameParamsManager = get<GameParamsManager>(),
                        commands = commands,
                    )
                }

                factoryOf(::GameRefreshMapper)
                factoryOf(::GameCoordinateMapper)
                factoryOf(::GameAreaMapper)

                factoryOf(::GameRandomizerManager)
                singleOf(::GameBitmapManager)
                singleOf(::GameParamsManager)
            }

            modules(
                managersModule,
                gameModule,
            )
        }
    }
}