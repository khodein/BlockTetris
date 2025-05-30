package com.gg.tetris.block.app.game.view.container_block

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.ClipDescription
import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.MotionEvent
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.FrameLayout
import com.gg.tetris.block.app.R
import com.gg.tetris.block.app.game.view.block_figure.GameBlockFigureItemView
import com.gg.tetris.block.app.utils.Constants
import com.gg.tetris.block.app.utils.corner.BorderType
import com.gg.tetris.block.app.utils.corner.RoundMode
import com.gg.tetris.block.app.utils.corner.ViewCorner
import com.gg.tetris.block.app.utils.dp
import com.gg.tetris.block.app.utils.getColor

class GameContainerBlockItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : FrameLayout(context, attrs, defStyleAttr), GameContainerBlockItem.View {

    private val cornerStrokeColor by lazy { getColor(R.color.game_container_block_stroke) }
    private val cornerBackgroundColor by lazy { getColor(R.color.game_container_block_surface) }

    private val figureView by lazy {
        GameBlockFigureItemView(context).apply {
            layoutParams = LayoutParams(
                WRAP_CONTENT,
                WRAP_CONTENT
            ).apply {
                gravity = Gravity.CENTER
            }
        }
    }

    init {
        layoutParams = ViewGroup.LayoutParams(
            Constants.GAME_BLOCK_SIZE,
            Constants.GAME_BLOCK_SIZE,
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
        val figureShadow = DragShadowBuilder(figureView)
        val figureClipItem = ClipData.Item(figureView.tag as? CharSequence)
        val figureClipData = ClipData(
            figureView.tag as? CharSequence,
            arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN),
            figureClipItem
        )
        figureView.startDragAndDrop(
            figureClipData,
            figureShadow,
            figureView,
            0
        )
    }

    override fun bindState(state: GameContainerBlockItem.State) {
        figureView.tag = state.tag
        figureView.bindState(state.figureBlockState)
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