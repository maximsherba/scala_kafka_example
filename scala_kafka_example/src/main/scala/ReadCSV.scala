//import org.apache.commons.csv
import io.circe.jawn.decode

import java.nio.file.{Files, Path, Paths}
import org.apache.commons.csv.CSVFormat
import io.circe.parser
import io.circe.syntax.EncoderOps
import io.circe._
import io.circe.generic.semiauto._

import java.util.Base64
import scala.util.Try

object ReadCSV extends App {
  sealed trait Foo
  case class Books(
                    name: String,
                    author: String,
                    userRating: String,
                    reviews: String,
                    price: String,
                    year: String,
                    genre: String
                  ) extends Foo

  //TODO: Change to relative path
  val path = "C:\\Users\\mscherba\\Documents\\Git\\scala_kafka_example\\scala_kafka_example\\src\\main\\resources\\source.csv"
  val reader = Files.newBufferedReader(Paths.get(path))
  val records = CSVFormat.DEFAULT.parse(reader)
  //records.forEach(println)
  //println(records.stream().count())

  records.forEach(record => {
    val y = record.get(0)

    val book = Books("","","","","","","")
    //val json = book.asJson.noSpaces
    //val book: Foo = Books.tupled((record.get(0),record.get(1),record.get(2),record.get(3),record.get(4),record.get(5),record.get(6)))
    //val result = parser.decode[List[Books]](record.toString)
  })


}
