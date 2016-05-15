package main.scala.periscope

object Coordinates {
  def parse(str: String) = str.split(" ").toSeq match {
    case Seq(x, y) => Coordinates(x.toInt, y.toInt)
    case coordinates => throw new Error(s"Unable to parse coordinates $coordinates")
  }
}

case class Coordinates(x: Int, y: Int) {
  override def toString = s"$x $y"
}
