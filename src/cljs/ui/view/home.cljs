(ns ui.view.home
  (:require [om.next :as om :refer-macros [defui]]
            [sablono.core :refer-macros [html]]
            [ui.view.bootstrap :as b]))

(defui HomeView
  Object
  (componentDidMount [this]
                     (if (aget js/window "twttr")
                       (. (aget (aget js/window "twttr") "widgets") load)
                       (aset js/window "twttr"
                             ((fn [d s id]
                                (let [fjs (aget (.getElementsByTagName d s) 0)
                                      t (or (aget js/window "twttr") (clj->js {}))
                                      js (.createElement d s)]
                                  (aset js "id" id)
                                  (aset js "src" "https://platform.twitter.com/widgets.js")
                                  (.insertBefore (aget fjs "parentNode") js fjs)
                                  (aset t "_e" (clj->js []))
                                  (aset t "ready" (fn [f] (.. t _e (push f))))))
                              js/document "script" "twitter-wjs")))
                     (if (aget js/window "facebook")
                       (. (aget (aget js/window "twttr") "widgets") load)
                       (aset js/window "facebook"
                             ((fn [d s id]
                                (let [fjs (aget (.getElementsByTagName d s) 0)
                                      t (or (aget js/window "facebook") (clj->js {}))
                                      js (.createElement d s)]
                                  (aset js "id" id)
                                  (aset js "src" "//connect.facebook.net/ja_JP/sdk.js#xfbml=1&version=v2.8")
                                  (.insertBefore (aget fjs "parentNode") js fjs)
                                  (aset t "_e" (clj->js []))
                                  (aset t "ready" (fn [f] (.. t _e (push f))))))
                              js/document "script" "facebook-jssdk"))))
  (render [this]
          (let [{:keys [] :as props} (om/props this)]
            (html
              [:div#home
               [:div.jumbotron
                [:h1 "名古屋大学相撲部"]
                [:p "Nagoya University Sumo Club"]
                [:p.lead
                 [:a.btn.btn-primary.btn-lg {:href "https://twitter.com/nu_sumo" :role "button"} "Twitter"]]
                [:p.lead
                 [:a.btn.btn-primary.btn-lg {:href "https://www.facebook.com/NUSUMOCLUB/" :role "button"} "Facebook"]]]
               [:div.row
                [:div.col-xs-12.col-md-6
                 [:div
                  [:a {:class "twitter-timeline", :data-chrome "nofooter", :href "https://twitter.com/nu_sumo", :data-widget-id "345543580846280704"} "@nu_sumo からのツイート"]]]
                [:div.col-xs-12.col-md-6
                 [:div.fb-page {:data-href "https://www.facebook.com/NUSUMOCLUB/" :data-tabs "timeline" :data-small-header false :data-adapt-container-width true data-hide-cover false data-show-facepile true} [:blockquote.fb-xfbml-parse-ignore {:cite "https://www.facebook.com/NUSUMOCLUB/"} [:a {:href "https://www.facebook.com/NUSUMOCLUB/"} "名古屋大学相撲部"]]]]]]))))
