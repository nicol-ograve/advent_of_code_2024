package shared

enum class TerrainTile {
    Empty, Obstruction;

    override fun toString(): String {
        return if(this == Empty) "." else "#"
    }
}
