import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.common.serialization.StringDeserializer

import java.time.Duration
import java.util.Properties
import scala.jdk.CollectionConverters._

object Consumer extends App {

  val props = new Properties()
  props.put("bootstrap.servers", "broker:9092")
  props.put("group.id", "homework")
  //props.put("enable.auto.commit", true)
  //props.put("auto.offset.reset", "latest")
  //props.put("isolation.level", "read_uncommitted")

  val consumer = new KafkaConsumer(props, new StringDeserializer, new StringDeserializer)
  val topic = "mytopic"
  consumer.subscribe(List(topic).asJavaCollection)

  consumer
    .poll(Duration.ofSeconds(10))
    .asScala
    .foreach { record => println(record.value()) }

  consumer.close()

}
