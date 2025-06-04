package com.gg.tetris.block.app.game.view.container_figure

import android.annotation.SuppressLint
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import android.view.Gravity
import android.view.MotionEvent
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.FrameLayout
import com.gg.tetris.block.app.R
import com.gg.tetris.block.app.game.GameParams
import com.gg.tetris.block.app.game.view.figure.FigureItem
import com.gg.tetris.block.app.game.view.figure.FigureItemView
import com.gg.tetris.block.app.utils.corner.BorderType
import com.gg.tetris.block.app.utils.corner.RoundMode
import com.gg.tetris.block.app.utils.corner.ViewCorner
import com.gg.tetris.block.app.utils.dp
import com.gg.tetris.block.app.utils.getColor

class ContainerFigureItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : FrameLayout(context, attrs, defStyleAttr), ContainerFigureItem.View {

    private val cornerStrokeColor by lazy { getColor(R.color.game_container_block_stroke) }
    private val cornerBackgroundColor by lazy { getColor(R.color.game_container_block_surface) }
    private var state: ContainerFigureItem.State? = null

    private val figureView by lazy {
        FigureItemView(context).apply {
            layoutParams = LayoutParams(
                WRAP_CONTENT,
                WRAP_CONTENT
            ).apply {
                gravity = Gravity.CENTER
            }
        }
    }

    private val handler = Handler(Looper.getMainLooper())

    init {
        layoutParams = ViewGroup.LayoutParams(
            GameParams.CONTAINER_SIZE,
            GameParams.CONTAINER_SIZE,
        )

        ViewCorner.Border(
            radius = 8.dp,
            roundMode = RoundMode.ALL,
            borderType = BorderType.Simple(
                strokeWidth = 2.dp,
                strokeColor = cornerStrokeColor
            )
        ).resolve(this, cornerBackgroundColor)

        addView(figureView)
    }

    private fun onClick() {
        val state = this.state
        if (state == null) {
            return
        }
        val newState = state.figureState.copy(isContainer = false)
        figureView.bindState(newState)
        handler.post(
            getRunDragAndDrop(
                state = newState,
                tag = state.tag
            )
        )
    }

    private fun getRunDragAndDrop(
        state: FigureItem.State,
        tag: ContainerFigureItem.Tag
    ) = Runnable {
        this.state?.provider?.dragAndDrop(
            figureView = figureView,
            tag = tag,
            figureWidth = state.originalState.width,
            figureHeight = state.originalState.height,
            originalTouchX = state.originalTouchX,
            originalTouchY = state.originalTouchY,
        )
    }

    override fun bindState(state: ContainerFigureItem.State) {
        this.state = state
        figureView.bindState(state.figureState)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        return when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                onClick()
                true
            }

            else -> false
        }
    }
}