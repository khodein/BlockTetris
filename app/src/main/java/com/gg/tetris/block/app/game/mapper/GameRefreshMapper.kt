package com.gg.tetris.block.app.game.mapper

import android.graphics.Bitmap
import com.gg.tetris.block.app.R
import com.gg.tetris.block.app.game.GameParams
import com.gg.tetris.block.app.game.view.refresh.GameRefreshItem
import com.gg.tetris.block.app.managers.ResManager

class GameRefreshMapper(
    private val resManager: ResManager,
) {

    private val refreshBitmap: Bitmap? by lazy {
        val drawable = resManager.getDrawable(R.drawable.ic_refresh) ?: return@lazy null
        resManager.getDrawableToBitmap(drawable)
    }

    fun map(
        count: Int,
        onClickRefresh: () -> Unit
    ): GameRefreshItem.State {
        return GameRefreshItem.State(
            width = GameParams.REFRESH_SIZE,
            height = GameParams.REFRESH_SIZE,
            count = count,
            refreshBitmap = refreshBitmap,
            backgroundColor = resManager.getColor(R.color.game_refresh_background),
            onClickRefresh = onClickRefresh,
        )
    }
}