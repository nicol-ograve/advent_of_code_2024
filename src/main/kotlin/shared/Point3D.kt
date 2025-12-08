package shared

import kotlin.math.pow
import kotlin.math.sqrt


data class Point3D(val x: Int, val y: Int, val z: Int) {
    override fun toString(): String {
        return "($x,$y,$z)"
    }

    override operator fun equals(other: Any?): Boolean {
        if (other == null || other !is Point3D) return false
        return other.x == x && other.y == y && other.z == z
    }

    override fun hashCode(): Int {
        var result = x
        result = 31 * result + y
        result = 1031 * result + z
        return result
    }

    operator fun plus(offset: Point3D): Point3D = Point3D(offset.x + x, offset.y + y, offset.z + z)

    fun distanceTo(otherPoint: Point3D): Double {
        return sqrt(
            (otherPoint.x - x).toDouble().pow(2) +
                    (otherPoint.y - y).toDouble().pow(2) +
                    (otherPoint.z - z).toDouble().pow(2)
        )
    }
}