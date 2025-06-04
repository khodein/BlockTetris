package com.gg.tetris.block.app.game.states.figure

sealed interface FigureCoordinateState {

    sealed interface I : FigureCoordinateState {

        val leftTop: Float
        val rightTop: Float
        val leftBottom: Float
        val rightBottom: Float

        data class V(
            override val leftBottom: Float,
            override val leftTop: Float,
            override val rightTop: Float,
            override val rightBottom: Float,
        ) : I

        data class H(
            override val leftBottom: Float,
            override val leftTop: Float,
            override val rightTop: Float,
            override val rightBottom: Float,
        ) : I
    }

    sealed class J() : FigureCoordinateState {
        data object R0 : J()
        data object R90 : J()
        data object R180 : J()
        data object R270 : J()
    }

    sealed class L() : FigureCoordinateState {
        data object R0 : L()
        data object R90 : L()
        data object R180 : L()
        data object R270 : L()
    }

    sealed class O() : FigureCoordinateState {
        data object X2X2 : O()
    }

    sealed class S() : FigureCoordinateState {
        data object R0 : S()
        data object R90 : S()
    }

    sealed class Z() : FigureCoordinateState {
        data object R0 : Z()
        data object R90 : Z()
    }

    sealed class T() : FigureCoordinateState {
        data object R0 : T()
        data object R90 : T()
        data object R180 : T()
        data object R270 : T()
    }
}