import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.common.serialization.StringSerializer

import io.circe.generic.auto._
import io.circe.syntax._
import org.apache.commons.csv.CSVFormat

import java.util.Properties
import java.nio.file.{Files, Paths}

object Producer extends App {
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

  val props = new Properties()
  props.put("bootstrap.servers", "broker:9092")

  val producer = new KafkaProducer(props, new StringSerializer, new StringSerializer)
  val topic = "books"

  records.forEach(record => {
    val y = record.get(0)
    val book = Books.tupled((record.get(0),record.get(1),record.get(2),record.get(3),record.get(4),record.get(5),record.get(6)))
    val jsonRecord = book.asJson.noSpaces
    producer.send(new ProducerRecord(topic, "jsonRecord", "123"))
    }
  )

}
