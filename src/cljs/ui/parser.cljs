(ns ui.parser
  (:require [om.next :as om]))

(defmulti read om/dispatch)
(defmethod read :default
  [env k params]
  (let [st @(:state env)]
    {:value (get-in st [k] {})}))
(defmethod read :app/member
  [env k params]
  (let [st @(:state env)]
    (js/console.log (pr-str st))
    {:remote true
     :value (get-in st [k])}))
(defmethod read :app/record
  [env k params]
  (let [st @(:state env)]
    {:remote true
     :value (get-in st [k])}))

(def app-parser (om/parser {:read read}))
