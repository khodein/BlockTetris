package com.gg.tetris.block.app.game.view.container_block

import com.gg.tetris.block.app.game.view.block_figure.GameBlockFigureItem


class GameContainerBlockItem {

    interface View {
        fun bindState(state: State)
    }

    class State(
        val figureBlockState: GameBlockFigureItem.State,
    )
}