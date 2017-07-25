/*
 * Copyright (C) 2016-2017, Roberto Casadei, Mirko Viroli, and contributors.
 * See the LICENCE.txt file distributed with this work for additional
 * information regarding copyright ownership.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
*/

package it.unibo.scafi.simulation.gui

import java.awt.Color

object Settings {
  import SettingsSpace._

  var Sim_Policy_Nbrhood = NbrHoodPolicies.Euclidean

  var Sim_ExecStrategy = ExecStrategies.Random

  var Sim_Topology = Topologies.Random
  var Sim_Sensors = "someSensor bool true\nanotherSensor int 77"
  var Sim_ProgramClass = ""
  var Sim_NbrRadius = 0.15
  var Sim_DeltaRound = 0
  var Sim_NumNodes = 100
  var ShowConfigPanel = true

  var RandomSeed = System.nanoTime()
  var Grid_HiVar_Eps = 0.16
  var Grid_MedVar_Eps = 0.09
  var Grid_LoVar_Eps = 0.02

  var Size_Device_Relative = 100

  var Color_background = java.awt.Color.white
  var Color_selection = new java.awt.Color(30,30,30,30)
  var Color_device = java.awt.Color.black
  var Color_device1 = java.awt.Color.red
  var Color_device2 = java.awt.Color.green
  var Color_device3 = java.awt.Color.blue
  var Color_link = new Color(240,240,240)
  var Color_actuator = java.awt.Color.yellow
  val Color_observation = new Color(200,0,0)

  var Led_Activator: Any=>Boolean = (_)=>false
  var To_String: Any=>String = null

}