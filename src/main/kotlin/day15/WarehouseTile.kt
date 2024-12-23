package day15

sealed class WarehouseTile {
    object Empty : WarehouseTile() {
        override fun toString(): String {
            return "."
        }
    }

    class Box(val side: BoxSide) : WarehouseTile() {
        override fun toString(): String {
            return "O"
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