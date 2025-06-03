package com.gg.tetris.block.app.game.view.block_figure

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.gg.tetris.block.app.game.view.block_figure.GameBlockFigureItem.FigureBlockState

class GameBlockFigureItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : View(context, attrs, defStyleAttr), GameBlockFigureItem.View {

    var state: GameBlockFigureItem.State? = null
        private set

    private val isContainer: Boolean
        get() = state?.isContainer != false

    private val bitmapPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    private val figureWidth: Int
        get() = if (isContainer) {
            state?.containerState?.width
        } else {
            state?.originalState?.width
        } ?: 0

    private val figureHeight: Int
        get() = if (isContainer) {
            state?.containerState?.height
        } else {
            state?.originalState?.height
        } ?: 0

    private val blocks: List<FigureBlockState>
        get() = if (isContainer) {
            state?.containerState?.blocks
        } else {
            state?.originalState?.blocks
        } ?: emptyList()

    override fun bindState(state: GameBlockFigureItem.State) {
        this.state = state
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
            canvas.drawBitmap(bitmap, block.left, block.top, bitmapPaint)
        }
    }
}