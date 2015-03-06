package com.mwronski.streams

import akka.actor.ActorSystem
import akka.stream.ActorFlowMaterializer
import akka.stream.scaladsl.{Source}
import akka.stream.stage._

/**
 * Sample of custom flow based on akka-stream documentation
 *
 * @author Michal Wronski
 */
object CustomStream {

  def main(args: Array[String]): Unit = {
    implicit val system = ActorSystem("Sys")
    import system.dispatcher

    implicit val materializer = ActorFlowMaterializer()

    val resultFuture = Source(1 to 10).
      transform(() => new Duplicator()).
      runForeach(println _).
      onComplete(_ => system.shutdown())
  }

}

/**
 * Duplicator taken from akka-stream documentation
 * http://doc.akka.io/docs/akka-stream-and-http-experimental/1.0-M4/scala/stream-customize.html
 * @tparam A
 */
class Duplicator[A]() extends PushPullStage[A, A] {
  private var lastElem: A = _
  private var oneLeft = false

  override def onPush(elem: A, ctx: Context[A]): Directive = {
    lastElem = elem
    oneLeft = true
    ctx.push(elem)
  }

  override def onPull(ctx: Context[A]): Directive =
    if (!ctx.isFinishing) {
      // the main pulling logic is below as it is demonstrated on the illustration
      if (oneLeft) {
        oneLeft = false
        ctx.push(lastElem)
      }
      else
        ctx.pull()
    } else {
      // If we need to emit a final element after the upstream
      // finished
      if (oneLeft) ctx.pushAndFinish(lastElem)
      else ctx.finish()
    }

  override def onUpstreamFinish(ctx: Context[A]): TerminationDirective =
    ctx.absorbTermination()

}