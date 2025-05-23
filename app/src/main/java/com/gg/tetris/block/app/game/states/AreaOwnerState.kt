package com.gg.tetris.block.app.game.states

data class AreaOwnerState(
    val id: Int,
    val vertical: Vertical,
    val horizontal: Horizontal,
) {

    enum class Horizontal {
        A,
        B,
        C,
        D,
        E,
        F,
        G,
        H,
    }

    enum class Vertical {
        ONE,
        TWO,
        THREE,
        FOUR,
        FIVE,
        SIX,
        SEVEN,
        EIGHT,
    }

    companion object {
        val areaOwnerListState: List<AreaOwnerState> = buildList(64) {
            var verticalOwnerState = Vertical.ONE
            var horizontalOwnerState = Horizontal.A

            repeat(64) { count ->
                add(
                    AreaOwnerState(
                        vertical = verticalOwnerState,
                        horizontal = horizontalOwnerState,
                        id = count
                    )
                )
                verticalOwnerState = when (count) {
                    0, 8, 16, 24, 32, 40, 48, 56 -> Vertical.TWO
                    1, 9, 17, 25, 33, 41, 49, 57 -> Vertical.THREE
                    2, 10, 18, 26, 34, 42, 50, 58 -> Vertical.FOUR
                    3, 11, 19, 27, 35, 43, 51, 59 -> Vertical.FIVE
                    4, 12, 20, 28, 36, 44, 52, 60 -> Vertical.SIX
                    5, 13, 21, 29, 37, 45, 53, 61 -> Vertical.SEVEN
                    6, 14, 22, 30, 38, 46, 54, 62 -> Vertical.EIGHT
                    7, 15, 23, 31, 39, 47, 55, 63 -> {
                        horizontalOwnerState = when (count) {
                            7 -> Horizontal.B
                            15 -> Horizontal.C
                            23 -> Horizontal.D
                            31 -> Horizontal.E
                            39 -> Horizontal.F
                            47 -> Horizontal.G
                            55 -> Horizontal.H
                            63 ->Horizontal.A
                            else -> Horizontal.A
                        }
                        Vertical.ONE
                    }

                    else -> Vertical.ONE
                }
            }
        }

        val groupVertical = areaOwnerListState.groupBy { it.vertical }
        val groupHorizontal = areaOwnerListState.groupBy { it.horizontal }
    }
}