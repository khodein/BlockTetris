package com.gg.tetris.block.app.game.manager.params

import com.gg.tetris.block.app.game.manager.params.state.OwnerParamsState
import com.gg.tetris.block.app.game.manager.params.state.ParamsState
import com.gg.tetris.block.app.managers.ResManager
import com.gg.tetris.block.app.utils.dp

class GameParamsManager(
    private val resManager: ResManager,
) {
    private val paramsHashMap = hashMapOf<OwnerParamsState, ParamsState>()

    fun getContainerBlock(): ParamsState.Block {
        return paramsHashMap.getOrPut(OwnerParamsState.CONTAINER_BLOCK) {
            val originalBlock = getOriginalBlock()
            ParamsState.Block(
                size = originalBlock.size / 2,
                offset = originalBlock.offset / 2
            )
        } as ParamsState.Block
    }

    fun getOriginalBlock(): ParamsState.Block {
        return paramsHashMap.getOrPut(OwnerParamsState.ORIGINAL_BLOCK) {
            val area = getArea()
            val padding = 4.dp
            val size = (area.size - (area.strokeWidth * 2) - padding * 7) / 8
            return ParamsState.Block(
                size = size.toFloat(),
                offset = padding.toFloat()
            )
        } as ParamsState.Block
    }

    fun getArea(): ParamsState.Area {
        return paramsHashMap.getOrPut(OwnerParamsState.AREA) {
            val screenSize = getScreenSize()
            val horizontalPadding = 20.dp.toFloat()
            val horizontalPaddings = horizontalPadding * 2f
            val size = screenSize - horizontalPaddings
            val strokeWidth = 4.dp.toFloat()
            val radius = 8.dp.toFloat()
            val raddi = floatArrayOf(
                radius,
                radius,
                radius,
                radius,
                radius,
                radius,
                radius,
                radius,
            )
            ParamsState.Area(
                size = size,
                strokeWidth = strokeWidth,
                radius = radius,
                halfStrokeWidth = strokeWidth / 2f,
                raddi = raddi,
                horizontalPadding = horizontalPadding
            )
        } as ParamsState.Area
    }

    fun getContainer(): ParamsState.Container {
        return paramsHashMap.getOrPut(OwnerParamsState.CONTAINER) {
            val screenSize = getScreenSize()
            val horizontalContainerPadding = 20.dp.toFloat()
            val horizontalContainerPaddings = horizontalContainerPadding * 2f
            val offset = 30.dp.toFloat()
            val size = (screenSize - horizontalContainerPaddings - offset * 2f) / 3f
            val topContainerPadding = 10.dp.toFloat()
            ParamsState.Container(
                horizontalContainerPadding = horizontalContainerPadding,
                topContainerPadding = topContainerPadding,
                offset = offset,
                size = size
            )
        } as ParamsState.Container
    }

    private fun getScreenSize(): Int {
        val widthScreen = resManager.getScreenWidth()
        return widthScreen
    }

    fun reset() {
        paramsHashMap.clear()
    }
}