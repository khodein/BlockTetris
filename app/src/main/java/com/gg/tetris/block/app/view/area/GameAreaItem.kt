package com.gg.tetris.block.app.view.area

import android.graphics.Bitmap
import androidx.annotation.ColorInt

class GameAreaItem {

    interface View {
        fun bindBackground(backgroundState: BackgroundState)
        fun bindBlockList(list: List<BlockState>)
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

    data class BlockState(
        val ownerBlockId: String,
        val bitmap: Bitmap?,
        val left: Float,
        val top: Float,
    )

    enum class CellHorizontal {
        A,
        B,
        C,
        D,
        E,
        F,
        G,
        H
    }

    enum class CellVertical {
        ONE,
        TWO,
        THREE,
        FOUR,
        FIVE,
        SIX,
        SEVEN,
        EIGHT
    }

    data class Cell(
        val vertical: CellVertical,
        val horizontal: CellHorizontal,
    )
}