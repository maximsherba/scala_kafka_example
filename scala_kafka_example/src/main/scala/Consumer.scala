import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.common.TopicPartition
import org.apache.kafka.common.serialization.StringDeserializer

import java.time.Duration
import java.util.Properties
import scala.jdk.CollectionConverters._
import scala.util.control.Breaks.{break, breakable}

object Consumer extends App {

  val props = new Properties()
  props.put("bootstrap.servers", "localhost:29092")
  props.put("group.id", "homework")
  //props.put("enable.auto.commit", true)
  props.put("auto.offset.reset", "latest")
  //props.put("isolation.level", "read_uncommitted")

  //В случае бесконечного цикла в консьюмере в продуктиве как правильно организовать выход
  //из приложения по сигналу и надо ли это ака-то делать?
  val consumer = new KafkaConsumer(props, new StringDeserializer, new StringDeserializer)
  val topic = "books"
  val tp = new TopicPartition(topic,0)
  val partitions = consumer.partitionsFor(topic)
  partitions.forEach{
    partitionInfo => {
      println(partitionInfo.partition())
      val topicPartition = new TopicPartition(topic, partitionInfo.partition())
      consumer.assign(List(topicPartition).asJava)
      val offset = consumer.position(topicPartition) - 5
      consumer.seek(topicPartition, offset)
      consumer
        .poll(Duration.ofMillis(500))
        .asScala
        .foreach{ record => println(record.value()) }
    }
  }

  consumer.close()

}
