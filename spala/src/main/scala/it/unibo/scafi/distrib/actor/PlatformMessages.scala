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

package it.unibo.scafi.distrib.actor

import java.util.concurrent.TimeUnit

import akka.actor.{ActorRef, Props}
import it.unibo.scafi.distrib._

import scala.concurrent.duration.FiniteDuration

  // Input/information messages (which provides data to the recipient actor)
  case class MsgLocalSensorValue[T](name: LSensorName, value: T)
  case class MsgSensorValue(id: UID, name: LSensorName, value: Any)
  case class MsgNbrSensorValue(name: NSensorName, values: Map[UID, Any])
  case class MsgExport(from: UID, export: ComputationExport) extends ScafiMessage
  case class MsgExports(exports: Map[UID, ComputationExport])
  case class MsgDeviceLocation(id: UID, ref: ActorRef)
  case class MsgWithDevices(devs: Map[UID, ActorRef])
  case class MsgNeighbor(id: UID, idn: UID)
  case class MsgNeighborhood(id: UID, nbrs: Set[UID])
  case class MsgMyFrequency(delay: FiniteDuration)
  case class MyNameIs(id: UID)
  case class MsgRound(id: UID, n: Long)
  case class MsgProgram(ap: Program, dependencies: Set[Class[_]] = Set())
  case class MsgAddSensor(name: LSensorName, provider: ()=>Any)
  case class MsgAddPushSensor(ref: ActorRef)
  case class MsgAddActuator(name: LSensorName, consumer: Any=>Unit)
  case class DevInfo(nid: UID, ref: ActorRef)

  // Invitation messages (please do "that" for me; the sender expects no reply)
  case class MsgRegistration(id: UID)
  case class MsgSetFrequency(n: Int, unit: TimeUnit)
  case class MsgRemoveNeighbor(idn: UID)
  case class MsgShipProgram(programMsg: MsgProgram)
  case class MsgDeliverTo(id: UID, msg: Any)

  // Command messages (please do "that" for me; the sender *does* expect some reply)
  case class MsgAddDevice(id: UID, props: Props)

  // Request/Response messages (the sender expects a reply from the recipient)
  case class MsgGetNbrSensorValue(sns: NSensorName, idn: UID)
  case class MsgGetSensorValue(sns: LSensorName)
  case class MsgLookup(id: UID)
  case class MsgGetNeighborhood(id: UID)
  case class MsgGetNeighborhoodExports(id: UID)
  case class MsgNeighborhoodExports(id: UID, nbrs: Map[UID,Option[ComputationExport]]) extends ScafiMessage
  object MsgGetIds // = "msg_get_ids".hashCode
  object MsgGetExport // = "msg_get_export".hashCode
  object MsgGetNeighbors // = "msg_get_neighbors".hashCode
  case class Ack(id: UID)
