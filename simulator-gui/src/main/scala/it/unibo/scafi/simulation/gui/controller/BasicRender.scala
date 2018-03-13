package it.unibo.scafi.simulation.gui.controller

import it.unibo.scafi.simulation.gui.controller.logger.LogManager
import it.unibo.scafi.simulation.gui.model.aggregate.AggregateEvent.{NodesDeviceChanged, NodesMoved}
import it.unibo.scafi.simulation.gui.model.common.world.CommonWorldEvent.NodesRemoved
import it.unibo.scafi.simulation.gui.model.simulation.BasicPlatform
import it.unibo.scafi.simulation.gui.pattern.observer.Source
import it.unibo.scafi.simulation.gui.view.SimulationView

import scala.collection.mutable

/**
  * the render of output to a graphics view (scalafx library)
  * @param world to show
  * @param out where render show thing
  * @param neighbourRender a boolean value to define if show the neighbours or not
  */
class BasicRender(val world : BasicPlatform with Source,
                  val out : SimulationView,
                  val neighbourRender : Boolean) extends Presenter[BasicPlatform with Source]{

  val removed = world.createObserver(Set(NodesRemoved))
  val moved = world.createObserver(Set(NodesMoved))
  val devChanged = world.createObserver(Set(NodesDeviceChanged))
  private var prevNeighbour : Map[world.ID,Set[world.ID]] = world.network.neighbours()
  world <-- removed <-- moved <-- devChanged
  override type OUTPUT = SimulationView
  //RENDER COMPONENT DECIDE WHAT RENDER TO OUTPUT AND HOW
  override def onTick(float: Float): Unit = {
    val nodesRemoved = removed.nodeChanged()
    if (!nodesRemoved.isEmpty) {
      LogManager.log("view erasing..", LogManager.Middle)
      out.removeNode(nodesRemoved)
    }
    //MOVING
    val nodesMoved = moved.nodeChanged()
    if (!nodesMoved.isEmpty) {
      LogManager.log("NODE MOVED : " + nodesMoved, LogManager.Low)
      var toAdd : Map[world.NODE,Set[world.NODE]] = Map()
      var toRemove : Map[world.ID,Set[world.ID]] = Map()
      nodesMoved foreach { x =>
        val node = world(x).get
        if (this.neighbourRender) {
          val oldIds = this.prevNeighbour(x)
          val newIds = world.network.neighbours(x)
          val id: world.ID = world(x).get.id
          val ids: Set[world.ID] = oldIds -- newIds
          val add = newIds -- oldIds
          if (!ids.isEmpty) {
            toRemove += id -> ids
          }
          if (!add.isEmpty) {
            toAdd += node -> world.apply(add)
          }
        }
      }
      this.prevNeighbour = world.network.neighbours()
      out.outNode(world.apply(nodesMoved))
      out.outNeighbour(toAdd)
      out.removeNeighbour(toRemove)
    }
    val deviceToOut = devChanged.nodeChanged()
    if(!deviceToOut.isEmpty) {
      out.outDevice(world.apply(deviceToOut))
    }
  }
}