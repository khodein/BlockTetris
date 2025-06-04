package com.gg.tetris.block.app.game.mapper

import android.graphics.Bitmap
import com.gg.tetris.block.app.R
import com.gg.tetris.block.app.game.GameParams
import com.gg.tetris.block.app.game.states.color.ColorState
import com.gg.tetris.block.app.managers.ResManager

class GameBitmapMapper(
    private val resManager: ResManager,
) {
    private val containerBlockMap = hashMapOf<ColorState, Bitmap?>()
    private val nonContainerBlockMap = hashMapOf<ColorState, Bitmap?>()

    fun getBlockBitmap(
        state: ColorState,
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
        state: ColorState,
        isContainer: Boolean
    ): Bitmap? {
        val size = GameParams.getBlockSize(isContainer)
        return when(state) {
            ColorState.RED -> mapBlockRedBitmap(size)
            ColorState.BLUE -> mapBlockBlueBitmap(size)
            ColorState.CYAN -> mapBlockCyanBitmap(size)
            ColorState.GREEN -> mapBlockGreenBitmap(size)
            ColorState.ORANGE -> mapBlockOrangeBitmap(size)
            ColorState.YELLOW -> mapBlockYellowBitmap(size)
            ColorState.EMPTY -> mapBlockEmptyBitmap(size)
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