// def unit[Foo] (foo: Foo) : Foo = foo

sealed trait Random[A] {
  def outer = this
  def map[B] (f: A => B): Random[B] = new Random[B] {
    def run(random: scala.util.Random): B = f(outer.run(random))
  }
  def flatMap[B] (f: A => Random[B]) : Random[B] = new Random[B] {
    def run(random: scala.util.Random): B = (f(outer.run(random))).run(random)
  }
  def run(random: scala.util.Random): A
}


case class Point(x: Int, y: Int)

object RandomInt extends Random[Int] {
  def run(random: scala.util.Random): Int = random.nextInt()
}
object RandomDouble extends Random[Double] {
  def run(random: scala.util.Random): Double = random.nextDouble()
}
object Random {
  def int: Random[Int] = RandomInt
  def double: Random[Double] = RandomDouble

  /*
  def point: Random[Point] =
    Random.int.flatMap { x => Random.int.map { y => Point(x, y) } }
    */
  def point: Random[Point] = for {
    x <- Random.int
    y <- Random.int
  } yield Point(x, y)
}