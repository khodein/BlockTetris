package com.gg.tetris.block.app.game.view.area

import android.graphics.Bitmap
import androidx.annotation.ColorInt
import com.gg.tetris.block.app.game.states.BlockState
import com.gg.tetris.block.app.game.states.OwnerState

class GameAreaItem {

    interface View {
        fun bindBackground(backgroundState: BackgroundState)
        fun bindBlockList(list: List<GameAreaItem.CellState>)
    }

    data class BackgroundState(
        val width: Int,
        val height: Int,
        val strokeWidth: Float,
        val strokeHalfWidth: Float,
        val raddi: FloatArray,
        @ColorInt val strokeColor: Int,
        @ColorInt val backgroundColor: Int,
    ) {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as BackgroundState

            if (width != other.width) return false
            if (height != other.height) return false
            if (strokeWidth != other.strokeWidth) return false
            if (!raddi.contentEquals(other.raddi)) return false

            return true
        }

        override fun hashCode(): Int {
            var result = width
            result = 31 * result + height
            result = 31 * result + strokeWidth.hashCode()
            result = 31 * result + raddi.contentHashCode()
            return result
        }
    }

    data class CellState(
        val owner: OwnerState,
        override val bitmap: Bitmap?,
        override val left: Float,
        override val top: Float,
    ) : BlockState
}