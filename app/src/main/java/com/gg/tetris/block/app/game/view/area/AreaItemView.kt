package com.gg.tetris.block.app.game.view.area

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.Rect
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View

class AreaItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : View(context, attrs, defStyleAttr), AreaItem.View {

    private val backgroundPaint by lazy {
        Paint(Paint.ANTI_ALIAS_FLAG).apply {
            style = Paint.Style.FILL
        }
    }

    private val strokePaint by lazy {
        Paint(Paint.ANTI_ALIAS_FLAG).apply {
            style = Paint.Style.STROKE
            strokeCap = Paint.Cap.ROUND
        }
    }

    private val backgroundRoundRectF by lazy { RectF() }

    private var size: Int = 0

    private val backgroundPath by lazy { Path() }
    private val backgroundStrokePath by lazy { Path() }

    private var blocksList = emptyList<AreaItem.Block>()
    private var locationBlocksList = emptyList<AreaItem.Block>()

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(size, size)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        makeBackgroundDraw(canvas)
        makeStrokeBackgroundDraw(canvas)
        makeListBitmap(
            canvas = canvas,
            blocksList = blocksList
        )
        makeListBitmap(
            canvas = canvas,
            blocksList = locationBlocksList
        )
    }

    private fun makeBackgroundDraw(canvas: Canvas) {
        canvas.drawPath(backgroundPath, backgroundPaint)
    }

    private fun makeStrokeBackgroundDraw(canvas: Canvas) {
        canvas.drawPath(backgroundStrokePath, strokePaint)
    }

    private fun makeListBitmap(
        canvas: Canvas,
        blocksList: List<AreaItem.Block>,
    ) {
        blocksList.forEachIndexed { index, gameBlock ->
            val bitmap = gameBlock.bitmap ?: return@forEachIndexed
            canvas.drawBitmap(bitmap, gameBlock.left, gameBlock.top, null)
        }
    }

    override fun bindAreaState(areaState: AreaItem.AreaState) {
        size = areaState.area.size.toInt()

        backgroundPaint.apply {
            color = areaState.backgroundColor
        }

        strokePaint.apply {
            color = areaState.strokeColor
            strokeWidth = areaState.area.strokeWidth
        }

        val backgroundRect = Rect(
            0,
            0,
            size,
            size,
        )

        backgroundRoundRectF.set(backgroundRect)
        backgroundRoundRectF.inset(areaState.area.halfStrokeWidth, areaState.area.halfStrokeWidth)

        backgroundPath.reset()
        backgroundPath.addRoundRect(backgroundRoundRectF, areaState.area.raddi, Path.Direction.CW)

        backgroundStrokePath.reset()
        backgroundStrokePath.addRoundRect(
            backgroundRoundRectF,
            areaState.area.raddi,
            Path.Direction.CW
        )

        requestLayout()
    }

    override fun bindBlocksState(list: List<AreaItem.Block>) {
        blocksList = list
        invalidate()
    }

    override fun bindLocationBlocksState(list: List<AreaItem.Block>) {
        locationBlocksList = list
        postInvalidate()
    }
}