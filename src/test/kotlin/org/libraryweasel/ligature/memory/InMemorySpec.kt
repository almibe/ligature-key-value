/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.libraryweasel.ligature.memory

import io.kotlintest.specs.StringSpec
import org.libraryweasel.ligature.test.createSpec

class InMemorySpec: StringSpec({createSpec { InMemoryStore() }()  })