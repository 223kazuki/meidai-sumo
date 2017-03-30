(ns ui.parser
  (:require [om.next :as om]))

(defmulti read om/dispatch)
(defmethod read :default
  [env k params]
  (let [st @(:state env)]
    {:value (get-in st [k] {})}))
(defmethod read :app/club
  [env k params]
  (let [st @(:state env)]
    {:value (merge (get-in st [k] {}) params)}))

(def app-parser (om/parser {:read read}))
