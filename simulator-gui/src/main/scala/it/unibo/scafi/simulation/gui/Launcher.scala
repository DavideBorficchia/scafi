package it.unibo.scafi.simulation.gui
import it.unibo.scafi.simulation.gui.controller.Controller


/**
  * Created by mirko on 2/9/17.
  */
class Launcher extends App {
    def parseCommandLine(): Unit = SimulationCmdLine.parse(args, Settings)
    def launch(): Unit = Controller.startup
}

object DefaultLauncher extends Launcher{
    parseCommandLine()
    launch()
}
