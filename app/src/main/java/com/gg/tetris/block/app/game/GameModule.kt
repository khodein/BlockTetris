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