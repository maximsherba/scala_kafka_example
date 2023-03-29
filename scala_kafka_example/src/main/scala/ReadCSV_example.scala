object ReadCSV_example extends App {
  import io.circe._, io.circe.generic.auto._, io.circe.parser._, io.circe.syntax._
  import java.nio.file.{Files, Path, Paths}
  import org.apache.commons.csv.CSVFormat

  //case class Books(name: String, author: String, userRating: Double, reviews: Int, price: Double, year: Int, genre: String)
  case class Books(
                    name: String,
                    author: String,
                    userRating: String,
                    reviews: String,
                    price: String,
                    year: String,
                    genre: String
                  )

  //TODO: Change to relative path
  val path = "C:\\Users\\mscherba\\Documents\\Git\\scala_kafka_example\\scala_kafka_example\\src\\main\\resources\\source.csv"
  val reader = Files.newBufferedReader(Paths.get(path))
  val records = CSVFormat.DEFAULT.parse(reader)

  records.forEach(record => {
    val y = record.get(0)
    val book = Books.tupled((record.get(0),record.get(1),record.get(2),record.get(3),record.get(4),record.get(5),record.get(6)))
    val jsonRecord = book.asJson.noSpaces
    println(jsonRecord)
    }
  )

}
