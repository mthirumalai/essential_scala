sealed trait JsValue {
  def stringify: String
}
final case class JSObject() extends JsValue {
  def stringify = "JSObject stringify"
}
final case class JSString() extends JsValue {
  def stringify = "JSString stringify"
}
trait JSWriter[A] {
  def write(a: A) : JsValue
}
object JsUtil {
  def toJson[A] (a: A) (implicit writer: JSWriter[A]) : JsValue =
    writer write a
}

import java.util.Date
sealed trait Visitor {
  def id: String
  def createdAt: Date
  def age: Long = new Date().getTime() - createdAt.getTime()
}

final case class Anonymous(
                            val id: String,
                            val createdAt: Date = new Date()) extends Visitor


final case class User (
                            val id: String,
                          val email: String,
                            val createdAt: Date = new Date()) extends Visitor

implicit object AnonymousJsWriter extends JSWriter[Anonymous] {
  def write(a: Anonymous) : JsValue = new JsValue {
    def stringify: String = {
      s"AnoynmousJSWriter ${a.toString}"
    }
  }
}
implicit object UserJsWriter extends JSWriter[User] {
  def write(a: User) : JsValue = new JsValue {
    def stringify: String = {
      s"UserJSWriter ${a.toString}"
    }
  }
}



