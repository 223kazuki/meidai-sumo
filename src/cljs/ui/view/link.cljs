(ns ui.view.link
  (:require [om.next :as om :refer-macros [defui]]
            [sablono.core :refer-macros [html]]
            [ui.view.bootstrap :as b]))

(defui LinkView
  Object
  (render [this]
          (let [{:keys [links] :as props} (om/props this)]
            (html
              [:div [:h1 "Link"
                     [:ul
                      (map #(vec [:li {:key (:link/title %)} [:a {:href (:link/ref %) :target "_blank"} (:link/title %)]]) links)]]]))))
