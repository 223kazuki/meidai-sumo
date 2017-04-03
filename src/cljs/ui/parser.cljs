(ns ui.parser
  (:require [om.next :as om]))

(defn get-members [state key]
  (let [st @state]
    (into [] (map #(get-in st %)) (get st key))))

(defmulti read om/dispatch)
(defmethod read :default
  [{:keys [state] :as env} key params]
  (let [st @state]
    (if-let [[_ value] (find st key)]
      {:value (merge value params)}
      {:value :not-found})))

(def app-parser (om/parser {:read read}))
