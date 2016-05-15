package main.scala.periscope

/**
  * Created by danielchao on 5/14/16.
  */

case class Roomba(room: Room, coordinates: Coordinates) {

  /**
    * Note: In a more established project, I'd use a scalaz lens for this kind of stuff.
    */
  def goNorth = moveTo(coordinates.copy(y = coordinates.y + 1))
  def goSouth = moveTo(coordinates.copy(y = coordinates.y - 1))
  def goEast = moveTo(coordinates.copy(x = coordinates.x + 1))
  def goWest = moveTo(coordinates.copy(x = coordinates.x - 1))

  def moveTo(coordinates: Coordinates) = coordinates match {
    case coords if room.includesCoords(coords) =>
      Roomba(room.removeDirt(coords), coords)
    case _ => this
  }

  def go(direction: String) = direction match {
    case "N" => goNorth
    case "S" => goSouth
    case "E" => goEast
    case "W" => goWest
  }
}
