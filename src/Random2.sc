
case class Random[A] (run: scala.util.Random => A) {
  def map[B] (f: A => B) = Random[B] { (x: scala.util.Random) => f(run(x)) }
  def flatMap[B] (g: A => Random[B]) : Random[B] = Random[B] { (x: scala.util.Random) =>
    val randomB: Random[B] = g(run(x))
    randomB.run(x)
  }
}
case class Point(x: Int, y: Int)

object Random {
  def int: Random[Int] = Random(_.nextInt(100))
  def double: Random[Double] = Random(_.nextDouble)

  // Random.int.flatMap { x => Random.int.map { y => Point(x, y) } }
  // why isn't this = Random( for ...)?
  // I don't understand this code
  /*
  def point: Random[Point] = for {
    x <- Random.int
    y <- Random.int
  } yield Point(x, y)
  */
  def point: Random[Point] = Random {
    (r: scala.util.Random) =>
      val x: Int = Random.int.run(r)
      val y: Int = Random.int.run(r)
      Point(x, y)
  }
}

val r = new scala.util.Random(100)
val randomInt = Random.int
randomInt.run(r)
randomInt.run(r)
randomInt.run(r)

val randomPoint = Random.point
randomPoint.run(r)



