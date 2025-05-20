package com.gg.tetris.block.app.game.states

sealed interface FigureState {
    val ownerId: String

    data object None : FigureState {
        override val ownerId: String = "None"
    }

    sealed class I(override val ownerId: String) : FigureState {
        data object V : I(ownerId = "IV")
        data object H : I(ownerId = "IH")
    }

    sealed class J(override val ownerId: String) : FigureState {
        data object R0 : J(ownerId = "JR0")
        data object R90 : J(ownerId = "JR90")
        data object R180 : J(ownerId = "JR180")
        data object R270 : J(ownerId = "JR270")
    }

    sealed class L(override val ownerId: String) : FigureState {
        data object R0 : L(ownerId = "LR0")
        data object R90 : L(ownerId = "LR90")
        data object R180 : L(ownerId = "LR180")
        data object R270 : L(ownerId = "LR270")
    }

    sealed class O(override val ownerId: String) : FigureState {
        data object X2X2 : O(ownerId = "O2X2")
    }

    sealed class S(override val ownerId: String) : FigureState {
        data object R0 : S("SR0")
        data object R90 : S("SR90")
    }

    sealed class Z(override val ownerId: String) : FigureState {
        data object R0 : Z(ownerId = "ZR0")
        data object R90 : Z(ownerId = "ZR90")
    }

    sealed class T(override val ownerId: String) : FigureState {
        data object R0 : T(ownerId = "TR0")
        data object R90 : T(ownerId = "TR90")
        data object R180 : T(ownerId = "TR180")
        data object R270 : T(ownerId = "TR270")
    }

    companion object {
        val all = listOf(
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