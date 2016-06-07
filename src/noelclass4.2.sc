
sealed trait Color {
  def red : Double
  def green: Double
  def blue: Double

  def isLight: Boolean = red + blue + green > 1.5
}
case class ColorInstance(val red: Double = 0.0, val green: Double = 0.0, val blue : Double= 0.0, val description : String= "") extends Color {
  override def toString: String = description match {
    case "" => if (isLight) "light" else "dark"
    case _ => description
  }
}
val Red = ColorInstance(1.0, 0.0, 0.0, "Red")
val Yellow = ColorInstance(1.0, 1.0, 0.0, "Yellow")
val Pink = ColorInstance(1.0, 0.5, 0.5, "Pink")

sealed trait Shape {
  def sides: Int
  def perimeter: Double
  def area: Double
  def color: Color
}

sealed trait Rectangular extends Shape {
  def width: Double
  def length: Double

  val sides = 4
  val perimeter = 2*width + 2*length
  val area = width*length
}
case class Square(val side: Double, val color: Color) extends Rectangular {
  val width = side
  val length = side
}
case class Rectangle(val width: Double, val length: Double, val color: Color) extends Rectangular
case class Circle(val radius: Double, val color: Color) extends Shape {
  val sides = 1
  val perimeter = 2 * Math.PI * radius
  val area = Math.PI * radius*radius
}

object Draw {
  def apply(shape: Shape) : String = {
    shape match {
      case Square(side, color) => s"$color square of side $side"
      case Rectangle(width, length, color) => s"$color rectangle of width $width length $length"
      case Circle(radius, color) => s"$color circle of radius $radius"
    }
  }
}
Rectangle(1.0, 2.0, Yellow)eq Rectangle(1.0, 2.0, Yellow)

Draw(Circle(1.0, Yellow))
Draw(Square(1.0, Yellow))
Draw(Rectangle(1.0, 2.0, ColorInstance()))


