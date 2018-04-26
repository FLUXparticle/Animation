package de.fluxparticle.utils.chain

fun <T> emptyChain(): Chain<T> = EmptyChain()

fun <T> chainOf(vararg data: T): Chain<T> {
    return data.foldRight(EmptyChain()) { head, tail: Chain<T> ->
        EagerChain(head, tail)
    }
}

fun <T> cons(head: T, tail: Chain<T>): Chain<T> = EagerChain(head, tail)

sealed class Chain<T> {

    abstract val head: T

    abstract val tail: Chain<T>

    abstract val length: Int

    open val empty: Boolean
        get() = false

    open infix fun concat(other: Chain<T>): Chain<T> = ConcatChain(this, other)

    fun none(predicate: (T) -> Boolean): Boolean = !any(predicate)

    abstract fun any(predicate: (T) -> Boolean): Boolean

    abstract fun <R> foldl(init: R, op: (R, T) -> R): R

}

private class EmptyChain<T>: Chain<T>() {

    override val head: T
        get() = throw NoSuchElementException()

    override val tail: Chain<T>
        get() = throw NoSuchElementException()

    override val length: Int
        get() = 0

    override val empty: Boolean
        get() = true

    override fun concat(other: Chain<T>): Chain<T> = other

    override fun any(predicate: (T) -> Boolean) = false

    override fun <R> foldl(init: R, op: (R, T) -> R): R = init

}

private class EagerChain<T>(override val head: T, override val tail: Chain<T>) : Chain<T>() {

    override val length: Int = 1 + tail.length

    override fun any(predicate: (T) -> Boolean) = predicate(head) || tail.any(predicate)

    override fun <R> foldl(init: R, op: (R, T) -> R): R = tail.foldl(op(init, head), op)

}

private class ConcatChain<T>(private val left: Chain<T>, private val right: Chain<T>) : Chain<T>() {

    override val head: T
        get() = left.head

    override val tail: Chain<T>
        get() = left.tail.concat(right)

    override val length: Int = left.length + right.length

    override fun any(predicate: (T) -> Boolean): Boolean = left.any(predicate) || right.any(predicate)

    override fun <R> foldl(init: R, op: (R, T) -> R): R = right.foldl(left.foldl(init, op), op)

}
