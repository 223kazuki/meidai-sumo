(ns ui.view.club
  (:require [om.next :as om :refer-macros [defui]]
            [sablono.core :refer-macros [html]]
            [ui.view.bootstrap :as b]
            [markdown.core :refer [md->html]]))

(defui ClubView
  Object
  (render [this]
          (let [{:keys [abouts selected] :as props} (om/props this)]
            (html
              [:div [:h1 "Club"]
               (b/tabs {:activeKey selected :onSelect (fn [id _] (aset js/window "location" (str "/#/club/" id))) :id "club"}
                       (map #(b/tab {:key (:about/id %) :eventKey (:about/id %) :title (:about/title %)}
                                    (html [:div {:dangerouslySetInnerHTML {:__html (md->html (:about/content %))}}])) abouts))]))))