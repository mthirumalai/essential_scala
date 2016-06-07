
final case object RedLight extends TrafficLight
final case object YellowLight extends TrafficLight
final case object GreenLight extends TrafficLight
sealed trait TrafficLight {
  def next: TrafficLight =
    this match {
      case RedLight => GreenLight
      case GreenLight => YellowLight
      case YellowLight => RedLight
    }
}



sealed trait Source
final case object Well extends Source
final case object Spring extends Source
final case object Tap extends Source

final case class BottledWater(size: Int, source: Source, carbonated: Boolean)

