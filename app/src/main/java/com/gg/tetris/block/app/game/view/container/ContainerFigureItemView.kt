package com.gg.tetris.block.app.game.view.container

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import android.view.Gravity
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.FrameLayout
import androidx.core.view.children
import androidx.core.view.updateLayoutParams
import com.gg.tetris.block.app.R
import com.gg.tetris.block.app.game.view.figure.FigureItem
import com.gg.tetris.block.app.game.view.figure.FigureItemView
import com.gg.tetris.block.app.utils.corner.BorderType
import com.gg.tetris.block.app.utils.corner.RoundMode
import com.gg.tetris.block.app.utils.corner.ViewCorner
import com.gg.tetris.block.app.utils.dp
import com.gg.tetris.block.app.utils.getColor
import com.gg.tetris.block.app.utils.setOnCustomLongClickListener

class ContainerFigureItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : FrameLayout(context, attrs, defStyleAttr), ContainerFigureItem.View {

    private val cornerStrokeColor by lazy { getColor(R.color.game_container_block_stroke) }
    private val cornerBackgroundColor by lazy { getColor(R.color.game_container_block_surface) }
    private var state: ContainerFigureItem.State? = null

    private val handler = Handler(Looper.getMainLooper())

    private var figureView: FigureItemView? = null

    init {
        layoutParams = ViewGroup.LayoutParams(
            WRAP_CONTENT,
            WRAP_CONTENT,
        )

        setOnCustomLongClickListener {
            onClick()
        }

        ViewCorner.Border(
            radius = 8.dp,
            roundMode = RoundMode.ALL,
            borderType = BorderType.Simple(
                strokeWidth = 2.dp,
                strokeColor = cornerStrokeColor
            )
        ).resolve(this, cornerBackgroundColor)
    }

    private fun onClick() {
        val state = this.state
        val view = figureView
        if (state == null || view == null) {
            return
        }
        val newState = state.figureState.copy(isContainer = false)
        view.bindState(newState)
        handler.post(
            getRunDragAndDrop(
                state = newState,
                view = view,
                tag = state.tag
            )
        )
    }

    private fun getRunDragAndDrop(
        state: FigureItem.State,
        view: FigureItemView,
        tag: ContainerFigureItem.Tag
    ) = Runnable {
        this.state?.provider?.onDragAndDrop(
            view = view,
            tag = tag,
            figureWidth = state.originalState.width,
            figureHeight = state.originalState.height,
            originalTouchX = state.originalTouchX,
            originalTouchY = state.originalTouchY,
        )
    }

    override fun bindState(state: ContainerFigureItem.State) {
        this.state = state
        val size = state.container.size.toInt()
        this.updateLayoutParams {
            width = size
            height = size
        }

        val view = children.find { it.id == FIGURE_VIEW_ID }

        if (state.isDragStarted) {
            if (view != null) {
                removeView(view)
                this.figureView = null
            }
        } else {
            val figureView = figureView ?: FigureItemView(context).apply {
                id = FIGURE_VIEW_ID
                layoutParams = LayoutParams(
                    WRAP_CONTENT,
                    WRAP_CONTENT
                ).apply {
                    gravity = Gravity.CENTER
                }

            }.also { figureView = it }

            figureView.bindState(state.figureState)

            if (view == null) {
                addView(figureView)
            }
        }
    }

    private companion object {
        val FIGURE_VIEW_ID = generateViewId()
    }
}