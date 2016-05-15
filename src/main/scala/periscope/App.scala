package main.scala.periscope

import scala.io.Source

case class Instructions(
  roomDimensions: Coordinates,
  roombaPosition: Coordinates,
  patchesOfDirt: Set[Coordinates],
  directions: Seq[String]
)

object App {
  /**
    * Given a series of lines, return the instructions.
    */
  def parseInput(lines: Seq[String]) = {
    val roomDimensions = Coordinates.parse(lines.head)
    val initialRoombaPosition = Coordinates.parse(lines(1))
    val dirtPatches = lines.drop(2).dropRight(1).map(Coordinates.parse).toSet
    val directions = lines.last.split("")
    Instructions(roomDimensions, initialRoombaPosition, dirtPatches, directions)
  }

  /**
    * Helper method to get the initial roomba and its room.
    */
  def initialRoomba(instructions: Instructions) = {
    val initialRoom = Room(
      width = instructions.roomDimensions.x,
      height = instructions.roomDimensions.y,
      dirtPatches = instructions.patchesOfDirt
    )
    Roomba(initialRoom, instructions.roombaPosition)
  }

  /**
    * The task runner that runs through all the directions and collects dirt along the way.
    */
  def execute(instructions: Instructions) = {
    val initial = initialRoomba(instructions)
    instructions.directions.foldLeft(initial) {
      case (roomba, direction) => roomba.go(direction)
    }
  }

  def main(args: Array[String]) = {
    val file = "./input.txt"
    val lines = Source.fromFile(file).getLines().toSeq
    val instructions = parseInput(lines)
    val endingRoomba = execute(instructions)
    println(endingRoomba.coordinates)
    println(instructions.patchesOfDirt.size - endingRoomba.room.dirtPatches.size)
  }
}
