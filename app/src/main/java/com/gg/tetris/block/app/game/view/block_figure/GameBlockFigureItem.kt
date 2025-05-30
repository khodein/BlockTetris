package com.gg.tetris.block.app.game.view.block_figure

import android.graphics.Bitmap
import com.gg.tetris.block.app.game.states.BlockState

class GameBlockFigureItem {

    interface View {
        fun bindState(state: State)
    }

    data class State(
        val containerWidth: Int,
        val containerHeight: Int,
        val originalWidth: Int,
        val originalHeight: Int,
        val containerBlocks: List<FigureBlockState>,
        val originalBlocks: List<FigureBlockState>,
        val isContainer: Boolean,
    )

    data class FigureBlockState(
        override val bitmap: Bitmap?,
        override val left: Float,
        override val top: Float
    ) : BlockState
}