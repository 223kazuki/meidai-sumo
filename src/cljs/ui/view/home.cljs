(ns ui.view.home
  (:require [om.next :as om :refer-macros [defui]]
            [sablono.core :refer-macros [html]]
            [ui.view.bootstrap :as b]))

(defui HomeView
  Object
  (componentDidMount [this]
                     (when (aget js/window "twttr")
                       (. (aget (aget js/window "twttr") "widgets") load))
                     (when (aget js/window "facebook")
                       (.parse (aget js/FB "XFBML"))))
  (render [this]
          (let [{:keys [] :as props} (om/props this)]
            (html
              [:div#home
               [:div.jumbotron
                [:h1 "名古屋大学相撲部"]
                [:p "Nagoya University Sumo Club"]]
               [:div.row
                [:div.col-xs-12.col-md-6
                 [:div
                  [:a {:class "twitter-timeline", :data-chrome "nofooter", :href "https://twitter.com/nu_sumo", :data-widget-id "345543580846280704"} "@nu_sumo からのツイート"]]]
                [:div.col-xs-12.col-md-6
                 [:div.fb-page {:data-href "https://www.facebook.com/NUSUMOCLUB/" :data-tabs "timeline" :data-small-header false :data-adapt-container-width true data-hide-cover false data-show-facepile true} [:blockquote.fb-xfbml-parse-ignore {:cite "https://www.facebook.com/NUSUMOCLUB/"} [:a {:href "https://www.facebook.com/NUSUMOCLUB/"} "名古屋大学相撲部"]]]]]]))))
