import scala.math.Ordering

val absOrdering = Ordering.fromLessThan[Int](Math.abs(_) > Math.abs(_))
List(-4, -3, -2, -1).sorted(absOrdering)

implicit val absAscending = Ordering.fromLessThan[Int](Math.abs(_) < Math.abs(_))
List(-4, -3, -2, -1).sorted

final case class Rational(numerator: Int, denominator: Int)
implicit val descending = Ordering.fromLessThan[Rational] ((x, y) => x.numerator.toFloat/x.denominator > y.numerator.toFloat/y.denominator)
List( Rational(1,2), Rational(2,3), Rational(3,4), Rational(4,5)).sorted(descending)
 val ascending = Ordering.fromLessThan[Rational] ((x, y) => x.numerator.toFloat/x.denominator < y.numerator.toFloat/y.denominator)
List( Rational(1,2), Rational(2,3), Rational(3,4), Rational(4,5)).sorted

case class Person(name: String, email: String)
trait Equal[A] {
  def equal(a: A, b: A) : Boolean
}

object EqualByName extends Equal[Person] {
  def equal(a: Person, b: Person) = a.name == b.name
}
implicit object EqualByNameAndEmail extends Equal[Person] {
  def equal(a: Person, b: Person) = a.name == b.name && a.email == b.email
}

val mad_yahoo = Person("mad", "mthirumala@yahoo.com")
val mad_yahoo2 = Person("mad", "mthirumala@yahoo.com")
val mad_gmail = Person("mad", "mthirumala@gmail.com")

object Comparator {
  def apply[A] (a: A, b: A) (implicit e: Equal[A]) : Boolean = {
    e.equal(a, b)
  }
}
Comparator(mad_yahoo, mad_yahoo2)
Comparator(mad_yahoo, mad_gmail)

Comparator(mad_yahoo, mad_yahoo2)(EqualByName)
Comparator(mad_yahoo, mad_gmail)(EqualByName)
