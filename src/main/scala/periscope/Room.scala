package main.scala.periscope

/**
  * Created by danielchao on 5/14/16.
  */

case class Room(width: Int, height: Int, dirtPatches: Set[Coordinates]) {

  def includesCoords(coordinates: Coordinates) =
    coordinates.x >= 0 && coordinates.y >= 0 && coordinates.x < width && coordinates.y < width

  def removeDirt(coordinates: Coordinates) = Room(width, height, dirtPatches - coordinates)

}