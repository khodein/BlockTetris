package com.gg.tetris.block.app.game.manager.bitmap

import android.graphics.Bitmap
import com.gg.tetris.block.app.R
import com.gg.tetris.block.app.game.manager.bitmap.state.GameBitmapState
import com.gg.tetris.block.app.game.manager.params.GameParamsManager
import com.gg.tetris.block.app.game.states.color.ColorState
import com.gg.tetris.block.app.managers.ResManager

class GameBitmapManager(
    private val resManager: ResManager,
    private val gameParamsManager: GameParamsManager,
) {
    private val colorBlockMap = hashMapOf<ColorState, GameBitmapState>()
    private var locationBitmap: Bitmap? = null

    fun getGameLocationBitmap(): Bitmap? {
        return locationBitmap ?: run {
            val size = gameParamsManager.getOriginalBlock().size
            val drawable = resManager.getDrawable(ColorState.EMPTY.blockColorRes) ?: return null
            resManager.getDrawableToBitmap(
                drawable = drawable,
                colorInt = resManager.getColor(R.color.game_location_block),
                width = size,
                height = size
            ).also {
                locationBitmap = it
            }
        }
    }

    fun getGameBitmapState(
        state: ColorState,
    ): GameBitmapState {
        return colorBlockMap.getOrPut(state) {
            GameBitmapState(
                containerBitmap = mapContainer(state),
                originalBitmap = mapOriginal(state)
            )
        }
    }

    private fun mapOriginal(
        state: ColorState,
    ): Bitmap? {
        return mapBitmap(
            state = state,
            size = gameParamsManager.getOriginalBlock().size
        )
    }

    private fun mapContainer(
        state: ColorState,
    ): Bitmap? {
        return mapBitmap(
            state = state,
            size = gameParamsManager.getContainerBlock().size,
        )
    }

    private fun mapBitmap(
        state: ColorState,
        size: Float
    ): Bitmap? {
        val drawable = resManager.getDrawable(state.blockColorRes) ?: return null
        return resManager.getDrawableToBitmap(drawable = drawable, width = size, height = size)
    }
}