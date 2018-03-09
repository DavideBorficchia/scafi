package it.unibo.scafi.simulation.gui.view

import it.unibo.scafi.simulation.gui.model.core.World

trait View

trait Container {
  type OUTPUT <: View

  def output : Set[OUTPUT]
}
/**
  * describe a generic output of a simulation
  */
trait SimulationView extends View{
  /**
    * out a set of node that are added or moved
    * @param node the nodes
    * @tparam N the type of nodes
    */
  def outNode[N<: World#Node] (node : Set[N])

  /**
    * remove a node into the output
    * @param node the node
    * @tparam ID the id of node
    */
  def removeNode[ID <: World#ID](node : Set[ID])

  /**
    * out a neighbour of a node
    * @tparam N the type of node
    */
  def outNeighbour[N <: World#Node] (nodes : Map[N,Set[N]])

  /**
    * remove a set of neighbour of a node
    * @tparam ID the id of node
    */
  def removeNeighbour[ID <: World#ID](nodes : Map[ID,Set[ID]])

  /**
    * output the device associated to the node
    * @param node the node
    * @tparam N the type of node
    */
  def outDevice[N <: World#Node](node : Set[N])

  /**
    * remove all devices associated to a node
    * @param node the node
    * @tparam ID the type of ID
    */
  def clearDevice[ID <: World#ID](node : Set[ID])
}