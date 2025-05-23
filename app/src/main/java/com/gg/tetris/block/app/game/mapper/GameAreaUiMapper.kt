package com.gg.tetris.block.app.game.mapper

import com.gg.tetris.block.app.R
import com.gg.tetris.block.app.game.states.AreaOwnerState
import com.gg.tetris.block.app.game.view.area.GameAreaItem
import com.gg.tetris.block.app.game.states.ColorState
import com.gg.tetris.block.app.resource.ResManager
import com.gg.tetris.block.app.utils.Constants

class GameAreaUiMapper(
    private val resManager: ResManager,
    private val gameBitmapUiMapper: GameBitmapUiMapper
) {

    fun mapBackgroundAreaState(): GameAreaItem.BackgroundState {
        return GameAreaItem.BackgroundState(
            width = Constants.GAME_AREA_SIZE,
            height = Constants.GAME_AREA_SIZE,
            strokeWidth = Constants.GAME_AREA_STROKE_WIDTH,
            strokeHalfWidth = Constants.GAME_AREA_HALF_STROKE_WIDTH,
            raddi = Constants.GAME_AREA_RADDI,
            strokeColor = resManager.getColor(R.color.game_stroke),
            backgroundColor = resManager.getColor(R.color.game_surface)
        )
    }

    fun mapBlockAreaList(): List<GameAreaItem.CellState> {
        val emptyBitmap = gameBitmapUiMapper.getBlockBitmap(
            state = ColorState.EMPTY,
            isContainer = false
        )

        var stepLeft = Constants.GAME_AREA_STROKE_WIDTH
        var stepTop = Constants.GAME_AREA_STROKE_WIDTH
        var count = 0

        return AreaOwnerState.areaOwnerListState.map {
            val state = GameAreaItem.CellState(
                owner = it,
                left = stepLeft,
                top = stepTop,
                bitmap = emptyBitmap,
            )

            stepLeft += Constants.CELL_SIZE + Constants.CELL_PADDING
            count++

            if (count == 8) {
                stepLeft = Constants.GAME_AREA_STROKE_WIDTH
                stepTop += Constants.CELL_SIZE + Constants.CELL_PADDING
                count = 0
            }

            state
        }
    }
}