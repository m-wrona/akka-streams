package com.mwronski.streams

import akka.actor.ActorSystem
import akka.stream.ActorFlowMaterializer
import akka.stream.scaladsl.Source

/**
 * Basic streams handling simple text source
 *
 * @author Michal Wronski
 */
object BasicStream {

  def main(args: Array[String]): Unit = {
    implicit val system = ActorSystem("Sys")
    import system.dispatcher

    implicit val materializer = ActorFlowMaterializer()

    val text =
      """|Lorem Ipsum is simply dummy text of the printing and typesetting industry.
        |Lorem Ipsum has been the industry's standard dummy text ever since the 1500s,
        |when an unknown printer took a galley of type and scrambled it to make a type
        |specimen book.""".stripMargin

    Source(() => text.split("\\s").iterator).
      map(_.toUpperCase).
      runForeach(println).
      onComplete(_ => system.shutdown())
  }


}
