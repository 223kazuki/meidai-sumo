(ns ui.view.common
  (:require [om.next :as om :refer-macros [defui]]
            [om.dom :as dom]
            [sablono.core :refer-macros [html]]
            [cljsjs.react-bootstrap]
            [ui.view.bootstrap :as b]))

(def menu
  [{:name "Home" :link "#/"}
   {:name "Club" :link "#/club"}
   {:name "Member" :link "#/member"}
   {:name "Record" :link "#/record"}
   {:name "Blog" :link "#/blog"}
   {:name "Photo" :link "#/photo"}
   {:name "Movie" :link "#/movie"}
   {:name "Masumeidai" :link "#/masumeidai"}
   {:name "Media" :link "#/media"}
   {:name "Link" :link "#/link"}
   {:name "Mail" :link "#/mail"}])

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

(defui ClubView
  Object
  (render [this]
          (let [{:keys [abouts selected] :as props} (om/props this)]
            (html
              [:div [:h1 "Club"]
               (println selected)
               (b/tabs {:activeKey (js/parseInt selected) :onSelect (fn [id _] (aset js/window "location" (str "/#/club/" id))) :id (js/parseInt selected)}
                       (map #(b/tab {:eventKey (:about/id %) :id (:about/id %) :title (:about/title %)}
                                    (:about/content %)) abouts))]))))

(defui MemberView
  Object
  (render [this]
          (let [{:keys [members] :as props} (om/props this)]
            (html
              [:div [:h1 "Member"]
               [:ul
                (map #(vec [:li {:key (:member/id %)} (:member/name %)]) members)]]))))

(defui RecordView
  Object
  (render [this]
          (let [{:keys [records] :as props} (om/props this)]
            (html
              [:div [:h1 "Record"]
               [:ul
                (map #(vec [:li {:key (:record/id %)} (:record/year %)]) records)]]))))

(defui BlogView
  Object
  (render [this]
          (let [{:keys [] :as props} (om/props this)]
            (html
              [:div [:h1 "Blog"]]))))

(defui PhotoView
  Object
  (render [this]
          (let [{:keys [] :as props} (om/props this)]
            (html
              [:div [:h1 "Photo"]]))))

(defui MovieView
  Object
  (render [this]
          (let [{:keys [] :as props} (om/props this)]
            (html
              [:div [:h1 "Movie"]]))))

(defui MasumeidaiView
  Object
  (render [this]
          (let [{:keys [] :as props} (om/props this)]
            (html
              [:div [:h1 "Masumeidai"]]))))

(defui MediaView
  Object
  (render [this]
          (let [{:keys [] :as props} (om/props this)]
            (html
              [:div [:h1 "Media"]]))))

(defui LinkView
  Object
  (render [this]
          (let [{:keys [] :as props} (om/props this)]
            (html
              [:div [:h1 "Link"]]))))

(defui MailView
  Object
  (render [this]
          (let [{:keys [] :as props} (om/props this)]
            (html
              [:div [:h1 "Mail"]]))))

(def query-key->view
  {:app/home HomeView
   :app/club ClubView
   :app/member MemberView
   :app/record RecordView
   :app/blog BlogView
   :app/photo PhotoView
   :app/movie MovieView
   :app/masumeidai MasumeidaiView
   :app/media MediaView
   :app/link LinkView
   :app/mail MailView})

(defn build-component [props]
  (let [component-key (first (keys props))]
    (when component-key
      ((om/factory (query-key->view component-key)) (props component-key)))))

(defui App
  static om/IQuery
  (query [this]
         '[:app/home])
  Object
  (render [this]
          (let [props (om/props this)
                component-key (first (keys props))]
            (html
              [:div.container
               (b/navbar {:fixedTop true :inverse true}
                         (b/navbarHeader {}
                                         (b/navbarBrand {} "名大相撲部")
                                         (b/navbarToggle {}))
                         (b/navbarCollapse {}
                                           (b/nav {:activeKey component-key}
                                                  (b/navItem {:eventKey :app/home :href "#/"} "Home")
                                                  (b/navItem {:eventKey :app/club :href "#/club"} "Club")
                                                  (b/navItem {:eventKey :app/member :href "#/member"} "Member")
                                                  (b/navItem {:eventKey :app/record :href "#/record"} "Record")
                                                  (b/navItem {:eventKey :app/blog :href "#/blog"} "Blog")
                                                  (b/navItem {:eventKey :app/photo :href "#/photo"} "Photo")
                                                  (b/navItem {:eventKey :app/movie :href "#/movie"} "Movie")
                                                  (b/navItem {:eventKey :app/masumeidai :href "#/masumeidai"} "舛名大")
                                                  (b/navItem {:eventKey :app/media :href "#/media"} "Media")
                                                  (b/navItem {:eventKey :app/link :href "#/link"} "Link")
                                                  (b/navItem {:eventKey :app/mail :href "#/mail"} "Mail"))))
               [:div.row
                [:div.hidden-xs.col-md-1]
                [:div.col-xs-12.col-md-10#content
                 (build-component props)
                 [:footer
                  [:small "Copyright 2017-2022 Nagoya University Sumo Club. All Rights Reserved."]]]
                [:div.hidden-xs.col-md-1]]
               [:div#fb-root]]))))
