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
      {:value value}
      {:value :not-found})))
(defmethod read :app/club
  [env k params]
  (let [st @(:state env)]
    {:value (merge (get-in st [k] {}) params)}))
(defmethod read :grade/leader
  [env k params]
  (let [st @(:state env)]
    {:value (get-in st [k] {})}))
(defmethod read :app/member
  [env k params]
  (let [st @(:state env)]
    {:value (merge (get-in st [k] {}) params)}))

(def app-parser (om/parser {:read read}))
