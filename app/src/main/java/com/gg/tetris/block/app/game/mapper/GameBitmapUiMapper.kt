package com.gg.tetris.block.app.game.mapper

import android.graphics.Bitmap
import com.gg.tetris.block.app.R
import com.gg.tetris.block.app.game.states.ColorFigureState
import com.gg.tetris.block.app.resource.ResManager
import com.gg.tetris.block.app.utils.Constants

class GameBitmapUiMapper(
    private val resManager: ResManager,
) {
    private val containerBlockMap = hashMapOf<ColorFigureState, Bitmap?>()
    private val nonContainerBlockMap = hashMapOf<ColorFigureState, Bitmap?>()

    fun getBlockBitmap(
        state: ColorFigureState,
        isContainer: Boolean
    ): Bitmap? {
        return if (isContainer) {
            containerBlockMap.getOrPut(state) {
                map(state, true)
            }
        } else {
            nonContainerBlockMap.getOrPut(state) {
                map(state, false)
            }
        }
    }

    private fun map(
        state: ColorFigureState,
        isContainer: Boolean
    ): Bitmap? {
        val size = Constants.getCellSize(isContainer)
        return when(state) {
            ColorFigureState.RED -> mapBlockRedBitmap(size)
            ColorFigureState.BLUE -> mapBlockBlueBitmap(size)
            ColorFigureState.CYAN -> mapBlockCyanBitmap(size)
            ColorFigureState.GREEN -> mapBlockGreenBitmap(size)
            ColorFigureState.ORANGE -> mapBlockOrangeBitmap(size)
            ColorFigureState.YELLOW -> mapBlockYellowBitmap(size)
            ColorFigureState.EMPTY -> mapBlockEmptyBitmap(size)
        }
    }

    private fun mapBlockRedBitmap(
        size: Float,
    ): Bitmap? {
        val drawable = resManager.getDrawable(R.drawable.ic_block_red) ?: return null
        return resManager.getDrawableToBitmap(drawable, width = size, height = size)
    }

    private fun mapBlockBlueBitmap(
        size: Float,
    ): Bitmap? {
        val drawable = resManager.getDrawable(R.drawable.ic_block_blue) ?: return null
        return resManager.getDrawableToBitmap(drawable, width = size, height = size)
    }

    private fun mapBlockCyanBitmap(
        size: Float,
    ): Bitmap? {
        val drawable = resManager.getDrawable(R.drawable.ic_block_cyan) ?: return null
        return resManager.getDrawableToBitmap(drawable, width = size, height = size)
    }

    private fun mapBlockGreenBitmap(
        size: Float,
    ): Bitmap? {
        val drawable = resManager.getDrawable(R.drawable.ic_block_green) ?: return null
        return resManager.getDrawableToBitmap(drawable, width = size, height = size)
    }

    private fun mapBlockOrangeBitmap(
        size: Float,
    ): Bitmap? {
        val drawable = resManager.getDrawable(R.drawable.ic_block_orange) ?: return null
        return resManager.getDrawableToBitmap(drawable, width = size, height = size)
    }

    private fun mapBlockYellowBitmap(
        size: Float,
    ): Bitmap? {
        val drawable = resManager.getDrawable(R.drawable.ic_block_yellow) ?: return null
        return resManager.getDrawableToBitmap(drawable, width = size, height = size)
    }

    private fun mapBlockEmptyBitmap(
        size: Float
    ): Bitmap? {
        val drawable = resManager.getDrawable(R.drawable.ic_block_empty) ?: return null
        return resManager.getDrawableToBitmap(drawable, width = size, height = size)
    }
}