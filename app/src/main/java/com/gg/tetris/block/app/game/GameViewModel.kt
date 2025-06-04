package com.gg.tetris.block.app.game

import android.content.ClipData
import android.content.ClipDescription
import android.view.DragEvent
import android.view.View
import android.view.View.DRAG_FLAG_OPAQUE
import android.view.View.OnDragListener
import androidx.lifecycle.ViewModel
import com.gg.tetris.block.app.game.builders.GameFigureDragShadowBuilder
import com.gg.tetris.block.app.game.command.figure_command.FigureCommandDelegate
import com.gg.tetris.block.app.game.mapper.GameAreaMapper
import com.gg.tetris.block.app.game.mapper.GameCoordinateMapper
import com.gg.tetris.block.app.game.mapper.GameRandomizerMapper
import com.gg.tetris.block.app.game.mapper.GameRefreshMapper
import com.gg.tetris.block.app.game.states.game.GameCoordinateState
import com.gg.tetris.block.app.game.states.game.GameFigureState
import com.gg.tetris.block.app.game.states.game.GameState
import com.gg.tetris.block.app.game.view.area.GameAreaItem
import com.gg.tetris.block.app.game.view.container_figure.ContainerFigureItem
import com.gg.tetris.block.app.game.view.refresh.GameRefreshItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class GameViewModel(
    private val gameAreaMapper: GameAreaMapper,
    private val gameRefreshMapper: GameRefreshMapper,
    private val gameRandomizerMapper: GameRandomizerMapper,
    private val gameCoordinateMapper: GameCoordinateMapper,
    private val figureCommandDelegate: FigureCommandDelegate,
) : ViewModel(), OnDragListener, ContainerFigureItem.Provider {

    private val _blocksFlow = MutableStateFlow<List<GameAreaItem.Block>>(emptyList())
    val blocksFlow = _blocksFlow.asStateFlow()

    private val _backgroundAreaFlow = MutableStateFlow<GameAreaItem.Background?>(null)
    val backgroundAreaFlow = _backgroundAreaFlow.asStateFlow()

    private val _leftContainerFigureFlow = MutableStateFlow<ContainerFigureItem.State?>(null)
    val leftContainerFigureFlow = _leftContainerFigureFlow.asStateFlow()

    private val _centerContainerFigureFlow = MutableStateFlow<ContainerFigureItem.State?>(null)
    val centerContainerFigureFlow = _centerContainerFigureFlow.asStateFlow()

    private val _rightContainerFigureFlow = MutableStateFlow<ContainerFigureItem.State?>(null)
    val rightContainerFigureFlow = _rightContainerFigureFlow.asStateFlow()

    private val _refreshBlocksFlow = MutableStateFlow<GameRefreshItem.State?>(null)
    val refreshBlocksFlow = _refreshBlocksFlow.asStateFlow()

    private val _coordinateStateFlow = MutableStateFlow<GameCoordinateState?>(null)
    val coordinateStateFlow = _coordinateStateFlow.asStateFlow()

    private var dragFigureState: GameFigureState = GameFigureState.EMPTY

    private var gameList: List<GameState> = emptyList()
        set(value) {
            _blocksFlow.value = gameAreaMapper.mapBlocksList(value)
            field = value
        }

    private var leftFigureState: GameFigureState = GameFigureState.EMPTY
        set(value) {
            _leftContainerFigureFlow.value = ContainerFigureItem.State(
                tag = ContainerFigureItem.Tag.LEFT,
                figureState = figureCommandDelegate.mapState(value),
                provider = this
            )
            field = value
        }

    private var centerFigureState: GameFigureState = GameFigureState.EMPTY
        set(value) {
            _centerContainerFigureFlow.value = ContainerFigureItem.State(
                tag = ContainerFigureItem.Tag.CENTER,
                figureState = figureCommandDelegate.mapState(value),
                provider = this,
            )
            field = value
        }

    private var rightFigureState: GameFigureState = GameFigureState.EMPTY
        set(value) {
            _rightContainerFigureFlow.value = ContainerFigureItem.State(
                tag = ContainerFigureItem.Tag.RIGHT,
                figureState = figureCommandDelegate.mapState(value),
                provider = this
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
            gameList = gameAreaMapper.mapGameState(coordinate.areaCoordinate)
        }
    }

    private fun updateBackgroundStateGameArea() {
        _backgroundAreaFlow.value = gameAreaMapper.mapAreaBackground()
    }

    private fun updateRefreshState() {
        _refreshBlocksFlow.value = gameRefreshMapper.map(
            count = 2,
            onClickRefresh = ::refreshBlocks
        )
    }

    private fun refreshBlocksAllEmpty() {
        if (
            leftFigureState == GameFigureState.EMPTY &&
            centerFigureState == GameFigureState.EMPTY &&
            rightFigureState == GameFigureState.EMPTY
        ) {
            refreshBlocks()
        }
    }

    private fun refreshBlocks() {
        gameRandomizerMapper.getRandomFigure().forEachIndexed { index, figure ->
            when (index) {
                0 -> {
                    if (leftFigureState == GameFigureState.EMPTY) {
                        leftFigureState = GameFigureState(
                            colorState = figure.colorState,
                            figureState = figure.figureState,
                        )
                    }
                }

                1 -> {
                    if (centerFigureState == GameFigureState.EMPTY) {
                        centerFigureState = GameFigureState(
                            colorState = figure.colorState,
                            figureState = figure.figureState,
                        )
                    }
                }

                2 -> {
                    if (rightFigureState == GameFigureState.EMPTY) {
                        rightFigureState = GameFigureState(
                            colorState = figure.colorState,
                            figureState = figure.figureState,
                        )
                    }
                }
            }
        }
    }

    override fun onDrag(view: View?, event: DragEvent?): Boolean {
        val figureTag = event?.clipDescription?.label?.let {
            ContainerFigureItem.Tag.valueOf(it.toString())
        }

        when (event?.action) {
            DragEvent.ACTION_DRAG_STARTED -> onDragStarted(figureTag)

            DragEvent.ACTION_DROP -> {
                val coordinate = coordinateStateFlow.value
                if (coordinate == null) {
                    return false
                }

                val rectFigureState = figureCommandDelegate.mapRectFigureState(
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

                refreshBlocksAllEmpty()
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
        figureTag: ContainerFigureItem.Tag?
    ) {
        dragFigureState = when (figureTag) {
            ContainerFigureItem.Tag.LEFT -> {
                val state = leftFigureState
                leftFigureState = GameFigureState.EMPTY
                state
            }

            ContainerFigureItem.Tag.CENTER -> {
                val state = centerFigureState
                centerFigureState = GameFigureState.EMPTY
                state
            }

            ContainerFigureItem.Tag.RIGHT -> {
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

    override fun dragAndDrop(
        tag: ContainerFigureItem.Tag,
        figureWidth: Int,
        figureHeight: Int,
        originalTouchX: Int,
        originalTouchY: Int,
        figureView: View
    ) {
        val figureShadow = GameFigureDragShadowBuilder(
            view = figureView,
            width = figureWidth,
            height = figureHeight,
            originalTouchX = originalTouchX,
            originalTouchY = originalTouchY,
        )

        val figureClipItem = ClipData.Item(tag.name as? CharSequence)

        val figureClipData = ClipData(
            tag.name as? CharSequence,
            arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN),
            figureClipItem
        )

        figureView.startDragAndDrop(
            figureClipData,
            figureShadow,
            figureView,
            DRAG_FLAG_OPAQUE,
        )
    }
}