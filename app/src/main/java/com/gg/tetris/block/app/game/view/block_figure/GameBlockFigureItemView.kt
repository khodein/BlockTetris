package com.gg.tetris.block.app.game.view.block_figure

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View
import com.gg.tetris.block.app.game.view.block_figure.GameBlockFigureItem.FigureBlockState

class GameBlockFigureItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : View(context, attrs, defStyleAttr), GameBlockFigureItem.View {

    private var figureWidth: Int = 0
    private var figureHeight: Int = 0
    private var blocks: List<FigureBlockState> = emptyList()

    override fun bindState(state: GameBlockFigureItem.State) {
        figureWidth = state.width
        figureHeight = state.height
        blocks = state.blocks

        requestLayout()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(figureWidth, figureHeight)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        blocks.forEachIndexed { index, block ->
            val bitmap = block.bitmap ?: return@forEachIndexed
            canvas.drawBitmap(bitmap, block.left, block.top, null)
        }
    }
}