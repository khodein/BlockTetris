package com.gg.tetris.block.app.game

import android.view.DragEvent
import android.view.View
import android.view.View.OnDragListener
import androidx.lifecycle.ViewModel
import com.gg.tetris.block.app.game.mapper.GameAreaUiMapper
import com.gg.tetris.block.app.game.mapper.GameCoordinateMapper
import com.gg.tetris.block.app.game.mapper.GameRandomizerMapper
import com.gg.tetris.block.app.game.mapper.GameRefreshBlocksUiMapper
import com.gg.tetris.block.app.game.mapper.figure_mapper.FigureUiMapper
import com.gg.tetris.block.app.game.states.GameCoordinateState
import com.gg.tetris.block.app.game.states.GameFigureState
import com.gg.tetris.block.app.game.states.GameState
import com.gg.tetris.block.app.game.view.area.GameAreaItem
import com.gg.tetris.block.app.game.view.container_block.GameContainerBlockItem
import com.gg.tetris.block.app.game.view.refresh.GameRefreshItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class GameViewModel(
    private val gameAreaUiMapper: GameAreaUiMapper,
    private val gameRefreshBlocksUiMapper: GameRefreshBlocksUiMapper,
    private val gameRandomizerMapper: GameRandomizerMapper,
    private val gameCoordinateMapper: GameCoordinateMapper,
    private val figureUiMapper: FigureUiMapper,
) : ViewModel(), OnDragListener {

    private val _cellListFlow = MutableStateFlow<List<GameAreaItem.CellState>>(emptyList())
    val cellListFlow = _cellListFlow.asStateFlow()

    private val _backgroundStateGameAreaFlow = MutableStateFlow<GameAreaItem.BackgroundState?>(null)
    val backgroundGameAreaFlow = _backgroundStateGameAreaFlow.asStateFlow()

    private val _leftContainerBlockFlow = MutableStateFlow<GameContainerBlockItem.State?>(null)
    val leftContainerBlockFlow = _leftContainerBlockFlow.asStateFlow()

    private val _centerContainerBlockFlow = MutableStateFlow<GameContainerBlockItem.State?>(null)
    val centerContainerBlockFlow = _centerContainerBlockFlow.asStateFlow()

    private val _rightContainerBlockFlow = MutableStateFlow<GameContainerBlockItem.State?>(null)
    val rightContainerBlockFlow = _rightContainerBlockFlow.asStateFlow()

    private val _refreshBlocksFlow = MutableStateFlow<GameRefreshItem.State?>(null)
    val refreshBlocksFlow = _refreshBlocksFlow.asStateFlow()

    private val _coordinateStateFlow = MutableStateFlow<GameCoordinateState?>(null)
    val coordinateStateFlow = _coordinateStateFlow.asStateFlow()

    private var dragFigureState: GameFigureState = GameFigureState.EMPTY

    private var gameList: List<GameState> = emptyList()
        set(value) {
            _cellListFlow.value = gameAreaUiMapper.mapCellList(value)
            field = value
        }

    private var leftFigureState: GameFigureState = GameFigureState.EMPTY
        set(value) {
            _leftContainerBlockFlow.value = GameContainerBlockItem.State(
                tag = GameContainerBlockItem.Tag.LEFT,
                figureBlockState = figureUiMapper.map(value)
            )
            field = value
        }

    private var centerFigureState: GameFigureState = GameFigureState.EMPTY
        set(value) {
            _centerContainerBlockFlow.value = GameContainerBlockItem.State(
                tag = GameContainerBlockItem.Tag.CENTER,
                figureBlockState = figureUiMapper.map(value)
            )
            field = value
        }

    private var rightFigureState: GameFigureState = GameFigureState.EMPTY
        set(value) {
            _rightContainerBlockFlow.value = GameContainerBlockItem.State(
                tag = GameContainerBlockItem.Tag.RIGHT,
                figureBlockState = figureUiMapper.map(value)
            )
            field = value
        }

    init {
        updateCoordinateState()
        updateBackgroundStateGameArea()
        updateRefreshState()

        refreshBlocks()
    }

    private fun updateCoordinateState() {
        gameCoordinateMapper.map().let { coordinate ->
            _coordinateStateFlow.value = coordinate
            gameList = gameAreaUiMapper.mapGameState(coordinate.areaCoordinate)
        }
    }

    private fun updateBackgroundStateGameArea() {
        _backgroundStateGameAreaFlow.value = gameAreaUiMapper.mapBackgroundAreaState()
    }

    private fun updateRefreshState() {
        _refreshBlocksFlow.value = gameRefreshBlocksUiMapper.map(
            count = 2,
            onClickRefresh = ::refreshBlocks
        )
    }

    private fun refreshBlocks() {
        gameRandomizerMapper.getRandomFigure().forEachIndexed { index, figure ->
            when (index) {
                0 -> leftFigureState = GameFigureState(
                    colorState = figure.colorState,
                    figureState = figure.figureState,
                )

                1 -> centerFigureState = GameFigureState(
                    colorState = figure.colorState,
                    figureState = figure.figureState,
                )

                2 -> rightFigureState = GameFigureState(
                    colorState = figure.colorState,
                    figureState = figure.figureState,
                )
            }
        }
    }

    override fun onDrag(view: View?, event: DragEvent?): Boolean {
        val figureTag = event?.clipDescription?.label?.let {
            GameContainerBlockItem.Tag.valueOf(it.toString())
        }

        when (event?.action) {
            DragEvent.ACTION_DRAG_STARTED -> onDragStarted(figureTag)

            DragEvent.ACTION_DROP -> {
                val coordinate = coordinateStateFlow.value
                if (coordinate == null) {
                    return false
                }

                val rectFigureState = figureUiMapper.mapRectFigureState(
                    eventX = event.x,
                    eventY = event.y,
                    gameFigureState = dragFigureState
                )

                val match = gameList.filter { gameState ->
                    isPointInsideObject(
                        touchX = gameState.coordinate.x,
                        touchY = gameState.coordinate.y,
                        left = rectFigureState.left,
                        top = rectFigureState.top,
                        right = rectFigureState.right,
                        bottom = rectFigureState.bottom
                    )
                }

                var list = gameList.toMutableList()
                match.forEach { gameState ->
                    val index = list.indexOf(gameState)
                    list[index] = gameState.copy(colorState = dragFigureState.colorState)
                }
                gameList = list

                dragFigureState = GameFigureState.EMPTY
            }

            DragEvent.ACTION_DRAG_LOCATION -> {

            }

            DragEvent.ACTION_DRAG_EXITED -> {

            }

            else -> {

            }
        }
        return true
    }

    private fun onDragStarted(
        figureTag: GameContainerBlockItem.Tag?
    ) {
        dragFigureState = when (figureTag) {
            GameContainerBlockItem.Tag.LEFT -> {
                val state = leftFigureState
                leftFigureState = GameFigureState.EMPTY
                state
            }

            GameContainerBlockItem.Tag.CENTER -> {
                val state = centerFigureState
                centerFigureState = GameFigureState.EMPTY
                state
            }

            GameContainerBlockItem.Tag.RIGHT -> {
                val state = rightFigureState
                rightFigureState = GameFigureState.EMPTY
                state

            }

            else -> GameFigureState.EMPTY
        }
    }

    private fun isPointInsideObject(
        touchX: Float,
        touchY: Float,
        left: Float,
        right: Float,
        top: Float,
        bottom: Float
    ): Boolean {
        return touchX in left..right && touchY in top..bottom
    }
}