; Copyright (c) 2019 Alex Michael Berry
;
; This program and the accompanying materials are made
; available under the terms of the Eclipse Public License 2.0
; which is available at https://www.eclipse.org/legal/epl-2.0/
;
; SPDX-License-Identifier: EPL-2.0

(ns org.libraryweasel.ligature.memory.store-test
  (:require [clojure.test :refer :all]
            [org.libraryweasel.ligature.memory.store :refer :all]
            [org.libraryweasel.ligature.core :refer :all]))

(deftest store-test
  (testing "Create new store."
    (is (not (= (ligature-memory-store) nil))))
  (testing "Test empty store.")
    (let [store (ligature-memory-store)]
      (is (not (= (get-dataset store "test") nil)))))
