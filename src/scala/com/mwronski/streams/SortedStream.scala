package com.mwronski.streams

import akka.actor.ActorSystem
import akka.stream.javadsl.FlattenStrategy
import akka.stream.{ ActorFlowMaterializer}
import akka.stream.scaladsl._
import akka.stream.scaladsl.Source

/**
 * Stream merges two sources producing numbers,
 * group them into chunks and orders
 *
 * @author Michal Wronski
 */
object SortedStream {

  def main(args: Array[String]): Unit = {
    implicit val system = ActorSystem("Sys")
    import system.dispatcher
    implicit val materializer = ActorFlowMaterializer()

    val g = FlowGraph { implicit b =>
      import FlowGraphImplicits._

      val merge = Merge[Int]

      val inOdd = Source((1 to 20).filter(_ % 2 == 1))
      val inEven = Source((1 to 20).filter(_ % 2 == 0))

      val sort = Flow[Int].
        grouped(10).
        map(_.sorted.reverse)
      val out = Sink.foreach(println _)

      inOdd ~> merge
      inEven ~> merge
      merge ~> sort ~> out
    }

    g.run()

  }


}
