package com.gg.tetris.block.app.game.manager.params.state

sealed interface ParamsState {

    data class Block(
        val size: Float,
        val offset: Float,
    ) : ParamsState

    data class Container(
        val size: Float,
        val topContainerPadding: Float,
        val horizontalContainerPadding: Float,
        val offset: Float
    ) : ParamsState

    data class Area(
        val size: Float,
        val strokeWidth: Float,
        val halfStrokeWidth: Float,
        val horizontalPadding: Float,
        val radius: Float,
        val raddi: FloatArray,
    ) : ParamsState {

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Area

            if (size != other.size) return false
            if (strokeWidth != other.strokeWidth) return false
            if (halfStrokeWidth != other.halfStrokeWidth) return false
            if (horizontalPadding != other.horizontalPadding) return false
            if (radius != other.radius) return false
            if (!raddi.contentEquals(other.raddi)) return false

            return true
        }

        override fun hashCode(): Int {
            var result = size.hashCode()
            result = 31 * result + strokeWidth.hashCode()
            result = 31 * result + halfStrokeWidth.hashCode()
            result = 31 * result + horizontalPadding.hashCode()
            result = 31 * result + radius.hashCode()
            result = 31 * result + raddi.contentHashCode()
            return result
        }
    }
}