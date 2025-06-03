package com.gg.tetris.block.app.game.view.block_figure

import android.graphics.Bitmap
import com.gg.tetris.block.app.game.states.BlockState

class GameBlockFigureItem {

    interface View {
        fun bindState(state: State)
    }

    data class State(
        val containerState: ContainerParamsState,
        val originalState: OriginalParamsState,
        val isContainer: Boolean = true,
    ) {
        val originalTouchX: Int
            get() = originalState.width / 2

        val originalTouchY: Int
            get() = originalState.height + originalState.height / 3
    }

    data class ContainerParamsState(
        override val width: Int,
        override val height: Int,
        override val blocks: List<FigureBlockState>
    ) : ParamsState {

        companion object {
            val EMPTY = ContainerParamsState(
                width = 0,
                height = 0,
                blocks = emptyList()
            )
        }
    }

    data class OriginalParamsState(
        override val width: Int,
        override val height: Int,
        override val blocks: List<FigureBlockState>
    ) : ParamsState {

        companion object {
            val EMPTY = OriginalParamsState(
                width = 0,
                height = 0,
                blocks = emptyList()
            )
        }
    }

    interface ParamsState {
        val width: Int
        val height: Int
        val blocks: List<FigureBlockState>
    }

    data class FigureBlockState(
        override val bitmap: Bitmap?,
        override val left: Float,
        override val top: Float
    ) : BlockState

    companion object {
        val EMPTY = State(
            containerState = ContainerParamsState.EMPTY,
            originalState = OriginalParamsState.EMPTY,
            isContainer = true
        )
    }
}