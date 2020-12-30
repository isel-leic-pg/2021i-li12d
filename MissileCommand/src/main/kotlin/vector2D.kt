import kotlin.math.pow
import kotlin.math.sqrt

/**
 *  Represents vectors in a plane (2D space).
 */
data class Vector2D(val x: Double, val y: Double)

/**
 * Operator overload to add [other] vector to this vector instance
 *
 * @param   other the vector to be added to this
 * @return the resulting vector
 */
operator fun Vector2D.plus(other: Vector2D) = Vector2D(this.x + other.x, this.y + other.y)

/**
 * Operator overload to subtract [other] vector from this vector instance
 *
 * @param   other the vector to be subtracted from this
 * @return the resulting vector
 */
operator fun Vector2D.minus(other: Vector2D) = Vector2D(this.x - other.x, this.y - other.y)

/**
 * Extension function used to compute the vector magnitude
 *
 * @return a scalar representing the vector's magnitude
 */
fun Vector2D.magnitude() = sqrt(x.pow(2) + y.pow(2))

/**
 * Extension function used to compute the vector's norm
 *
 * @return the norm vector with the same direction of this
 */
fun Vector2D.norm() = this / magnitude()

/**
 * Operator overload to multiply this vector with a scalar
 *
 * @param value the scalar value
 * @return  the new scaled vector instance
 */
operator fun Vector2D.times(value: Double) = Vector2D(x * value, y * value)

/**
 * Operator overload to divide this vector by a scalar
 *
 * @param value the scalar value
 * @return  the new scaled vector instance
 */
operator fun Vector2D.div(value: Double) = Vector2D(x / value, y / value)

