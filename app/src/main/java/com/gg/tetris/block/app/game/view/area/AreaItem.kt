package com.gg.tetris.block.app.game.view.area

import android.graphics.Bitmap
import androidx.annotation.ColorInt
import com.gg.tetris.block.app.game.manager.params.state.ParamsState
import com.gg.tetris.block.app.game.states.owner.OwnerState

class AreaItem {

    interface View {
        fun bindAreaState(areaState: AreaState)
        fun bindBlocksState(list: List<Block>)
        fun bindLocationBlocksState(list: List<Block>)
    }

    data class AreaState(
        val area: ParamsState.Area,
        @ColorInt val strokeColor: Int,
        @ColorInt val backgroundColor: Int,
    )

    data class Block(
        val owner: OwnerState,
        val bitmap: Bitmap?,
        val left: Float,
        val top: Float,
    )
}