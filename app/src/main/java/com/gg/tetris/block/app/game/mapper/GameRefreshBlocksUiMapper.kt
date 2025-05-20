package com.gg.tetris.block.app.game.mapper

import android.graphics.Bitmap
import com.gg.tetris.block.app.R
import com.gg.tetris.block.app.game.view.refresh.GameRefreshItem
import com.gg.tetris.block.app.resource.ResManager
import com.gg.tetris.block.app.utils.dp

class GameRefreshBlocksUiMapper(
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
            width = SIZE,
            height = SIZE,
            count = count,
            refreshBitmap = refreshBitmap,
            backgroundColor = resManager.getColor(R.color.game_refresh_background),
            onClickRefresh = onClickRefresh,
        )
    }

    companion object {
        val SIZE = 62.dp
    }
}