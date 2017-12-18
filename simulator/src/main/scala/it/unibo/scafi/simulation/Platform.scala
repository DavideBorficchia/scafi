package it.unibo.scafi.simulation

/**
  * a plaform for a generic simulation
  */
trait Platform {
  type ID;
  type LSNS;
  type NSNS;
  type Context <:SimulationContext;
  type Nextwork <: SimulationNetwork;
  type Export <: SimulationExport;
  type Program <: SimulationProgram;
  trait SimulationContext {

  }

  trait SimulationNetwork {
    def ids: Set[ID]
    def neighbourhood(id: ID): Set[ID]
    def localSensor[A](name: LSNS)(id: ID): A
    def nbrSensor[A](name: NSNS)(id: ID)(idn: ID): A
    def export(id: ID): Option[Export]
    def exports(): Map[ID, Option[Export]]
  }

  trait SimulationExport {
    def root[E] : E
  }

  trait SimulationProgram {
    def run(context: Context): Export
  }
}
