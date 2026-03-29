package it.unibo.pps.tasks.typeclasses

import it.unibo.pps.u03.Sequences.Sequence
import Sequence.*
import it.unibo.pps.u03.extensionmethods.Optionals.Optional
import it.unibo.pps.u03.extensionmethods.Optionals.Optional.Just

/*  Exercise 5: 
 *  - Generalise by ad-hoc polymorphism logAll, such that:
 *  -- it can be called on Sequences but also on Optional, or others... 
 *  -- it does not necessarily call log, but any function with analogous type
 *  - Hint: introduce a type class Traversable[T[_]]], capturing the ability of calling a
 *    "consumer function" on all elements (with type A) of a datastructure T[A] 
 *    Note Traversable is a 2-kinded trait (similar to Filterable, or Monad)
 *  - Write givens for Traversable[Optional] and Traversable[Sequence]
 *  - Show you can use the generalisation of logAll to:
 *  -- log all elements of an Optional, or of a Traversable
 *  -- println(_) all elements of an Optional, or of a Traversable
 */

object Ex5Traversable:

  trait Traversable[T[_]]:
    def consume[A, B](elem: T[A])(f: A => B): Unit

  object Traversable:
    extension [T[_] : Traversable, A](elem: T[A])
      def consumeAll[B](f: A => B): Unit =
        summon[Traversable[T]].consume(elem)(f)

      def logAll()[B]: Unit =
        elem.consumeAll(log)

    given Traversable[Optional] with
      override def consume[A, B](elem: Optional[A])(f: A => B): Unit = elem match
        case Just(a) => f(a)
        case _ => ()

    given Traversable[Sequence] with
      override def consume[A, B](elem: Sequence[A])(f: A => B): Unit = elem match
        case Cons(h, t) =>
          f(h)
          consume(t)(f)
        case _ => ()

  def log[A](a: A): Unit = println("The next element is: " + a)

  @main def tryTraversables =
    import Traversable.{*, given}
    val s = Cons(10, Cons(20, Cons(30, Nil())))
    s.consumeAll(log)
    s.consumeAll(println)
    s.logAll()
    val optional = Just(10)
    optional.logAll()
