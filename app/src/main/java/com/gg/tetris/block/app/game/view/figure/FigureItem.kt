package com.gg.tetris.block.app.game.view.figure

import android.graphics.Bitmap

class FigureItem {

    interface View {
        fun bindState(state: State)
    }

    data class State(
        val containerState: ContainerParams,
        val originalState: OriginalParams,
        val isContainer: Boolean = true,
    ) {
        val originalTouchX: Int
            get() = originalState.width / 2

        val originalTouchY: Int
            get() = originalState.height + originalState.height / 3
    }

    data class ContainerParams(
        override val width: Int,
        override val height: Int,
        override val blocks: List<Block>
    ) : Params {

        companion object {
            val EMPTY = ContainerParams(
                width = 0,
                height = 0,
                blocks = emptyList()
            )
        }
    }

    data class OriginalParams(
        override val width: Int,
        override val height: Int,
        override val blocks: List<Block>
    ) : Params {

        companion object {
            val EMPTY = OriginalParams(
                width = 0,
                height = 0,
                blocks = emptyList()
            )
        }
    }

    interface Params {
        val width: Int
        val height: Int
        val blocks: List<Block>
    }

    data class Block(
        val bitmap: Bitmap?,
        val left: Float,
        val top: Float
    )

    companion object {
        val EMPTY = State(
            containerState = ContainerParams.EMPTY,
            originalState = OriginalParams.EMPTY,
            isContainer = true
        )
    }
}