package com.gg.tetris.block.app.game

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
import com.gg.tetris.block.app.game.states.figure.FigureState
import com.gg.tetris.block.app.utils.dp
import org.koin.androidx.fragment.dsl.fragmentOf
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

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

        FigureCommandDelegate(
            gameBitmapMapper = get<GameBitmapMapper>(),
            commands = commands,
        )
    }

    singleOf(::GameAreaMapper)
    singleOf(::GameBitmapMapper)
    singleOf(::GameRefreshMapper)
    singleOf(::GameCoordinateMapper)

    factoryOf(::GameRandomizerMapper)
}

object GameParams {

    val REFRESH_SIZE = 62.dp

    val CONTAINER_SIZE = 104.dp

    val AREA_SIZE = 388.dp
    val AREA_STROKE_WIDTH = 4.dp.toFloat()
    val AREA_RADIUS = 8.dp.toFloat()
    val AREA_HALF_STROKE_WIDTH = AREA_STROKE_WIDTH / 2f
    val AREA_RADDI = floatArrayOf(
        AREA_RADIUS,
        AREA_RADIUS,
        AREA_RADIUS,
        AREA_RADIUS,
        AREA_RADIUS,
        AREA_RADIUS,
        AREA_RADIUS,
        AREA_RADIUS,
    )

    val BLOCK_PADDING_DELIMITER = 4.dp.toFloat()
    val BLOCK_SIZE = (AREA_SIZE - (BLOCK_PADDING_DELIMITER * 2) - BLOCK_PADDING_DELIMITER * 7) / 8f

    val BLOCK_CONTAINER_SIZE = BLOCK_SIZE / 2f
    val BLOCK_CONTAINER_PADDING_DELIMITER = BLOCK_PADDING_DELIMITER / 2f

    fun getBlockSize(
        isContainer: Boolean
    ): Float {
        return if (isContainer) {
            BLOCK_CONTAINER_SIZE
        } else {
            BLOCK_SIZE
        }
    }

    fun getBlockPaddingDelimiter(
        isContainer: Boolean
    ): Float {
        return if (isContainer) {
            BLOCK_CONTAINER_PADDING_DELIMITER
        } else {
            BLOCK_PADDING_DELIMITER
        }
    }
}