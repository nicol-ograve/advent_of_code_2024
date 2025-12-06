package year2024.day15

sealed class WarehouseTile {
    object Empty : WarehouseTile() {
        override fun toString(): String {
            return "."
        }
    }

    class Box(val side: BoxSide) : WarehouseTile() {
        override fun toString(): String {
            return if (side == BoxSide.Left) "[" else "]"
        }
    }

    class Wall() : WarehouseTile() {
        override fun toString(): String {
            return "#"
        }
    }

}

enum class BoxSide {
    Left, Right
}