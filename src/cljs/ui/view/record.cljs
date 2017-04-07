(ns ui.view.record
  (:require [om.next :as om :refer-macros [defui]]
            [sablono.core :refer-macros [html]]
            [ui.view.bootstrap :as b]))

(defui RecordView
  Object
  (render [this]
          (let [{:keys [records selected] :as props} (om/props this)]
            (println selected)
            (html
              [:div#record
               [:h1 "Record"]
               (b/tabs {:activeKey selected :onSelect (fn [year _] (aset js/window "location" (str "/#/record/" year))) :id "record"}
                       (map #(b/tab {:key (:record/year %) :eventKey (:record/year %) :title (str (:record/year %) " 年度")}
                                    (html [:div (:record/content %)])) records))]))))