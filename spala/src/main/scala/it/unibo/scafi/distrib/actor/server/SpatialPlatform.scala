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

package it.unibo.scafi.distrib.actor.server

import akka.actor.{ActorRef, Props}
import it.unibo.scafi.distrib.actor.MsgNeighborhood
import it.unibo.scafi.distrib.{LSensorName, UID}
import it.unibo.scafi.space.MetricSpatialAbstraction

import scala.concurrent.java8.FuturesConvertersImpl.P

/**
 * Specializes an [[it.unibo.scafi.distrib.actor.Platform]] into a "centralized platform" where
 *   - There is a central component in the system to which all the devices
 *     have to register and communicate in order to get info such as
 *     neighborhood state.
 */

trait P

trait LocationSensorProvider {
  val LocationSensorName: LSensorName
}

  case class MsgWithPosition(id: UID, pos: P)

  class SettingsFactorySpatial extends SettingsFactoryServer {
    override def defaultProfileSettings(): ProfileSettings =
      super.defaultProfileSettings().copy(serverActorProps = SpatialServerActor.props(_))
  }

  trait SettingsFactorySpatialProvider {
    val settingsFactory = new SettingsFactorySpatial
  }


  class SpatialServerActor(val space: MutableMetricSpace[UID],
                           val scheduler: Option[ActorRef])
    extends AbstractServerActor
    with ObservableServerActor with LocationSensorProvider {

    override def neighborhood(id: UID): Set[UID] = {
      if(space.contains(id)) space.getNeighbors(id).toSet else Set()
    }

    override def setSensorValue(id: UID, name: LSensorName, value: Any): Unit = {
      super.setSensorValue(id, name, value)
      if(name == LocationSensorName) {
        space.setLocation(id, value.asInstanceOf[P])

        // Notify observers about change of neighborhoods
        // TODO: it is not very efficient (especially in dynamic nets)...
        this.space.getAll().foreach(id => {
          val nbs = this.space.getNeighbors(id)
          notifyObservers(MsgNeighborhood(id,nbs.toSet))
        })
      }
    }
  }

  object SpatialServerActor extends Serializable {
    def props(sched: Option[ActorRef] = None): Props =
      Props(classOf[SpatialServerActor], buildNewSpace(Seq()), sched)
  }
}
