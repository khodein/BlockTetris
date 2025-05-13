package com.gg.tetris.block.app.game

import androidx.lifecycle.ViewModel
import com.gg.tetris.block.app.R
import com.gg.tetris.block.app.resource.ResManager
import com.gg.tetris.block.app.utils.dp
import com.gg.tetris.block.app.view.area.GameAreaItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class GameViewModel(
    private val resManager: ResManager,
) : ViewModel() {

    private val _blockStateListFlow = MutableStateFlow<List<GameAreaItem.BlockState>>(emptyList())
    val blockListFlow = _blockStateListFlow.asStateFlow()

    private val _backgroundStateGameAreaFlow = MutableStateFlow<GameAreaItem.BackgroundState?>(null)
    val backgroundGameAreaFlow = _backgroundStateGameAreaFlow.asStateFlow()

    private val emptyBitmap by lazy {
        val drawable = resManager.getDrawable(R.drawable.ic_block_empty) ?: return@lazy null
        resManager.getDrawableToBitmap(drawable)
    }

    init {
        _backgroundStateGameAreaFlow.value = GameAreaItem.BackgroundState(
            width = GAME_AREA_SIZE,
            height = GAME_AREA_SIZE,
            strokeWidth = GAME_AREA_STROKE_WIDTH,
            strokeHalfWidth = GAME_AREA_HALF_STROKE_WIDTH,
            raddi = GAME_AREA_RADDI,
            strokeColor = resManager.getColor(R.color.game_stroke_surface),
            backgroundColor = resManager.getColor(R.color.game_surface)
        )

        _blockStateListFlow.value = buildList(64) {
            var stepLeft = GAME_AREA_STROKE_WIDTH
            var stepTop = GAME_AREA_STROKE_WIDTH
            var count = 0

            repeat(64) {
                GameAreaItem.BlockState(
                    ownerBlockId = "",
                    left = stepLeft,
                    top = stepTop,
                    bitmap = emptyBitmap,
                ).let(::add)

                stepLeft += CELL_SIZE + CELL_PADDING
                count++

                if (count == 8) {
                    stepLeft = GAME_AREA_STROKE_WIDTH
                    stepTop += CELL_SIZE + CELL_PADDING
                    count = 0
                }
            }
        }
    }

    companion object {
        val GAME_AREA_SIZE = 388.dp
        val CELL_PADDING = 4.dp.toFloat()
        val GAME_AREA_STROKE_WIDTH = 4.dp.toFloat()
        val GAME_AREA_HALF_STROKE_WIDTH = GAME_AREA_STROKE_WIDTH / 2f
        val CELL_SIZE = (GAME_AREA_SIZE - (CELL_PADDING * 2) - CELL_PADDING * 7) / 8
        private val GAME_AREA_RADIUS = 8.dp.toFloat()
        val GAME_AREA_RADDI = floatArrayOf(
            GAME_AREA_RADIUS,
            GAME_AREA_RADIUS,
            GAME_AREA_RADIUS,
            GAME_AREA_RADIUS,
            GAME_AREA_RADIUS,
            GAME_AREA_RADIUS,
            GAME_AREA_RADIUS,
            GAME_AREA_RADIUS,
        )
    }
}