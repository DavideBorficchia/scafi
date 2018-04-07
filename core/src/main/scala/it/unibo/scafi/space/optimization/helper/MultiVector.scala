/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package it.unibo.scafi.space.optimization.helper

/** Base trait for Vectors
  *
  */
trait MultiVector extends Serializable {

  /** Number of elements in a vector
    *
    * @return The number of elements of the vector
    */
  def size: Int

  /** Element wise access function
    *
    * @param index index of the accessed element
    * @return value of the associated with the index
    */
  def apply(index: Int): Double

  /** Updates the element at the given index with the provided value
    *
    * @param index The index of the element to be updated
    * @param value The new value
    */
  def update(index: Int, value: Double): Unit

  /** Copies the vector instance
    *
    * @return Copy of the vector instance
    */
  def copy: MultiVector

  /** Magnitude of a vector
    *
    * @return The length of the vector
    */
  def magnitude: Double

  def equalsVector(vector: MultiVector): Boolean = {
    if(size == vector.size) {
      (0 until size) forall { idx =>
        this(idx) == vector(idx)
      }
    } else {
      false
    }
  }
}

object MultiVector{

}
