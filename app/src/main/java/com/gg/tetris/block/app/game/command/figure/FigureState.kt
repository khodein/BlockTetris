package com.gg.tetris.block.app.game.command.figure

sealed interface FigureState {

    val ownerId: String

    val mask: List<Pair<Int, Int>>

    data object None : FigureState {
        override val ownerId: String = "None"
        override val mask: List<Pair<Int, Int>> = emptyList()
    }

    sealed class I(override val ownerId: String) : FigureState {

        // .
        // .
        // .
        // .
        data object V : I(ownerId = "IV") {
            override val mask: List<Pair<Int, Int>> = listOf(0 to 0, 1 to 0, 2 to 0, 3 to 0)
        }

        // . . . .
        data object H : I(ownerId = "IH") {
            override val mask: List<Pair<Int, Int>> = listOf(0 to 0, 0 to 1, 0 to 2, 0 to 3)
        }
    }

    sealed class J(override val ownerId: String) : FigureState {

        // .
        // . . .
        data object R0 : J(ownerId = "JR0") {
            override val mask: List<Pair<Int, Int>> = listOf(0 to 0, 1 to 0, 1 to 1, 1 to 2)
        }


        //  .
        //  .
        //. .
        data object R90 : J(ownerId = "JR90") {
            override val mask: List<Pair<Int, Int>> = listOf(0 to 1, 1 to 1, 2 to 0, 2 to 1)
        }

        // . . .
        //     .
        data object R180 : J(ownerId = "JR180") {
            override val mask: List<Pair<Int, Int>> = listOf(0 to 0, 0 to 1, 0 to 2, 1 to 2)
        }

        // . .
        // .
        // .
        data object R270 : J(ownerId = "JR270") {
            override val mask: List<Pair<Int, Int>> = listOf(0 to 0, 0 to 1, 1 to 0, 2 to 0)
        }
    }

    sealed class L(override val ownerId: String) : FigureState {

        //     .
        // . . .
        data object R0 : L(ownerId = "LR0") {
            override val mask: List<Pair<Int, Int>> = listOf(0 to 2, 1 to 0, 1 to 1, 1 to 2)
        }

        // . .
        //   .
        //   .
        data object R90 : L(ownerId = "LR90") {
            override val mask: List<Pair<Int, Int>> = listOf(0 to 0, 0 to 1, 1 to 1, 2 to 1)
        }

        // . . .
        // .
        data object R180 : L(ownerId = "LR180") {
            override val mask: List<Pair<Int, Int>> = listOf(0 to 0, 0 to 1, 0 to 2, 1 to 0)
        }

        // .
        // .
        // . .
        data object R270 : L(ownerId = "LR270") {
            override val mask: List<Pair<Int, Int>> = listOf(0 to 0, 1 to 0, 2 to 0, 2 to 1)
        }
    }

    sealed class O(override val ownerId: String) : FigureState {

        // . .
        // . .
        data object X2X2 : O(ownerId = "O2X2") {
            override val mask: List<Pair<Int, Int>> = listOf(0 to 0, 0 to 1, 1 to 0, 1 to 1)
        }
    }

    sealed class S(override val ownerId: String) : FigureState {

        //   . .
        // . .
        data object R0 : S(ownerId = "SR0") {
            override val mask: List<Pair<Int, Int>> = listOf(0 to 1, 0 to 2, 1 to 0, 1 to 1)
        }

        // .
        // . .
        //   .
        data object R90 : S(ownerId = "SR90") {
            override val mask: List<Pair<Int, Int>> = listOf(0 to 0, 1 to 0, 1 to 1, 2 to 1)
        }
    }

    sealed class Z(override val ownerId: String) : FigureState {

        // . .
        //   . .
        data object R0 : Z(ownerId = "ZR0") {
            override val mask: List<Pair<Int, Int>> = listOf(0 to 0, 0 to 1, 1 to 1, 1 to 2)
        }

        //   .
        // . .
        // .
        data object R90 : Z(ownerId = "ZR90") {
            override val mask: List<Pair<Int, Int>> = listOf(0 to 1, 1 to 0, 1 to 1, 2 to 0)
        }
    }

    sealed class T(override val ownerId: String) : FigureState {

        //   .
        // . . .
        data object R0 : T(ownerId = "TR0") {
            override val mask: List<Pair<Int, Int>> = listOf(0 to 1, 1 to 0, 1 to 1, 1 to 2)
        }

        //   .
        // . .
        //   .
        data object R90 : T(ownerId = "TR90") {
            override val mask: List<Pair<Int, Int>> = listOf(0 to 1, 1 to 0, 1 to 1, 2 to 1)
        }

        // . . .
        //   .
        data object R180 : T(ownerId = "TR180") {
            override val mask: List<Pair<Int, Int>> = listOf(0 to 0, 0 to 1, 0 to 2, 1 to 1)
        }

        // .
        // . .
        // .
        data object R270 : T(ownerId = "TR270") {
            override val mask: List<Pair<Int, Int>> = listOf(0 to 0, 1 to 0, 1 to 1, 2 to 0)
        }
    }

    companion object {
        val entries: List<FigureState>
            get() = listOf(
                I.H,
                I.V,
                J.R0,
                J.R90,
                J.R180,
                J.R270,
                L.R0,
                L.R90,
                L.R180,
                L.R270,
                O.X2X2,
                S.R0,
                S.R90,
                Z.R0,
                Z.R90,
                T.R0,
                T.R90,
                T.R180,
                T.R270,
            )
    }
}