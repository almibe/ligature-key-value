/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package dev.ligature.store.keyvalue.operations

import dev.ligature.{BooleanLiteral, DoubleLiteral, LangLiteral, StringLiteral}
import dev.ligature.store.keyvalue.KeyValueStore
import dev.ligature.store.keyvalue.codec.Encoder

object LiteralOperations {
  def decodeDoubleLiteral(literalId: Long): DoubleLiteral = {
    Decoder.decodeDoubleLiteral(literalId)
  }

  def decodeBooleanLiteral(literalId: Long): BooleanLiteral = {
    Decoder.decodeBooleanLiteral(literalId)
  }

  def handleStringLiteralLookup(store: KeyValueStore, collectionId: Long, literalId: Long): StringLiteral = {
    val res = store.get(Encoder.encodeIdToStringKey(collectionId, literalId))
    StringLiteral(Decoder.decodeStringLiteral(res.get))
  }

  def handleLangLiteralLookup(store: KeyValueStore, collectionId: Long, literalId: Long): LangLiteral = {
    //TODO lookup in IdToLangLiteral
    ???
  }

  def fetchLangLiteralId(store: KeyValueStore, collectionId: Long, langLiteral: LangLiteral): Option[Long] = {
    //TODO look up in LangLiteralToId
    //TODO return accordingly
    ???
  }

  def fetchStringLiteralId(store: KeyValueStore, collectionId: Long, stringLiteral: StringLiteral): Option[Long] = {
    val res = store.get(Encoder.encodeStringToIdKey(collectionId, stringLiteral))
    if (res.nonEmpty) {
      Some(res.get.toLong())
    } else {
      None
    }
  }

}
