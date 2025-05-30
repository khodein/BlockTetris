package com.gg.tetris.block.app.game.view.container_block

import com.gg.tetris.block.app.game.view.block_figure.GameBlockFigureItem


class GameContainerBlockItem {

    interface View {
        fun bindState(state: State)
    }

    class State(
        val tag: String,
        val figureBlockState: GameBlockFigureItem.State,
    )

    companion object {
        const val LEFT_TAG = "LEFT_TAG"
        const val CENTER_TAG = "CENTER_TAG"
        const val RIGHT_TAG = "RIGHT_TAG"
    }
}