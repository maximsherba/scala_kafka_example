import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.common.serialization.StringDeserializer
import scala.jdk.CollectionConverters._
import java.time.Duration
import java.util.Properties

object Consumer_example extends App {

  val props = new Properties()
  props.put("bootstrap.servers", "broker:9092")
  props.put("group.id", "consumer")

  val consumer = new KafkaConsumer(props, new StringDeserializer, new StringDeserializer)

  consumer.subscribe(List("mytopic").asJavaCollection)

  println("TEST-----------------------")
  consumer
    .poll(Duration.ofSeconds(10))
    .asScala
    .foreach { r => println(r.value()) }

  consumer.close()

}
