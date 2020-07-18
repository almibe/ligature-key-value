/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package dev.ligature.store.inmemory

import java.util.concurrent.atomic.AtomicReference

import dev.ligature.store.keyvalue.KeyValueStore
import scodec.bits.ByteVector

import scala.collection.immutable.TreeMap
import scala.util.{Success, Try}

/**
 * An implementation of KeyValueStore that uses an AtomicReference to a TreeMap to store its data.
 */
private final class InMemoryKeyValueStore(private val data: AtomicReference[TreeMap[ByteVector, ByteVector]])
  extends KeyValueStore {

  override def put(key: ByteVector, value: ByteVector): Try[(ByteVector, ByteVector)] = {
    val current = data.get()
    val newValue = current.updated(key, value)
    data.set(newValue)
    Success((key, value))
  }

  override def delete(key: ByteVector): Try[ByteVector] = {
    val current = data.get()
    val newValue = current.removed(key)
    data.set(newValue)
    Success(key)
  }

  override def scan(start: ByteVector, end: ByteVector): Iterable[(ByteVector, ByteVector)] =
    data.get().range(start, end)

  def copy(): InMemoryKeyValueStore = {
    val ref = new AtomicReference(this.data.get())
    new InMemoryKeyValueStore(ref)
  }

  def clear(): Unit = data.set(null)
}

private object InMemoryKeyValueStore {
  private object ByteVectorOrdering extends Ordering[ByteVector] {
    def compare(a:ByteVector, b:ByteVector): Int = b.length compare a.length
  }

  def newStore(): InMemoryKeyValueStore = {
    new InMemoryKeyValueStore(new AtomicReference(
      TreeMap[ByteVector, ByteVector]()(ByteVectorOrdering)))
  }
}
