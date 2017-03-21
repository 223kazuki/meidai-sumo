(ns ui.parser
  (:require [om.next :as om]))

(defmulti read om/dispatch)
(defmethod read :default [_ _ _]
  {:value {}})
(defmethod read :app/about
  [{:keys [state] :as env} key params]
  {:value (get-in @state [:about])})
(defmethod read :app/remote
  [env k params]
  (let [st @(:state env)]
    {:remote true
     :value (get-in st [k :greeting])}))

(def app-parser (om/parser {:read read}))
