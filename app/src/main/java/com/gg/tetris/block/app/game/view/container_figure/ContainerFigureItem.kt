package com.gg.tetris.block.app.game.view.container_figure

import com.gg.tetris.block.app.game.view.figure.FigureItem

class ContainerFigureItem {

    interface View {
        fun bindState(state: State)
    }

    class State(
        val tag: Tag,
        val figureState: FigureItem.State,
        val provider: Provider,
    )

    interface Provider {
        fun onDragAndDrop(
            tag: Tag,
            figureWidth: Int,
            figureHeight: Int,
            originalTouchX: Int,
            originalTouchY: Int,
            view: android.view.View,
        )
    }

    enum class Tag {
        LEFT,
        CENTER,
        RIGHT,
    }
}