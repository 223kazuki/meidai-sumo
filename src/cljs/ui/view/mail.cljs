(ns ui.view.mail
  (:require [om.next :as om :refer-macros [defui]]
            [sablono.core :refer-macros [html]]
            [ui.view.bootstrap :as b]))

(defui MailView
  Object
  (render [this]
          (let [{:keys [] :as props} (om/props this)]
            (html
              [:div [:h1 "Mail"]]))))
