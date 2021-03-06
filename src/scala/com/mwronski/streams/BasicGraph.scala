package com.mwronski.streams

import akka.actor.ActorSystem
import akka.stream.ActorFlowMaterializer
import akka.stream.scaladsl.{Source, _}

/**
 * Basic graph operating on numbers
 *
 * @author Michal Wronski
 */
object BasicGraph {

  def main(args: Array[String]): Unit = {
    implicit val system = ActorSystem("Sys")

    implicit val materializer = ActorFlowMaterializer()

    val g = FlowGraph { implicit b =>
      import akka.stream.scaladsl.FlowGraphImplicits._
      val in = Source(1 to 10)
      val out = Sink.foreach(println _)

      val bcast = Broadcast[Int]
      val merge = Merge[Int]

      val f1, f2, f3, f4 = Flow[Int].map(_ + 10)

      in ~> f1 ~> bcast ~> f2 ~> merge ~> f3 ~> out
      bcast ~> f4 ~> merge
    }

    g.run()

  }


}
