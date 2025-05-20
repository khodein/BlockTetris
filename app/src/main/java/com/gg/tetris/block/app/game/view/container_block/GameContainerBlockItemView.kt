package com.gg.tetris.block.app.game.view.container_block

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.FrameLayout
import com.gg.tetris.block.app.R
import com.gg.tetris.block.app.game.view.block_figure.GameBlockFigureItemView
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
    private val size by lazy { 104.dp }

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
            size,
            size
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

    override fun bindState(state: GameContainerBlockItem.State) {
        figureView.bindState(state.figureBlockState)
    }
}