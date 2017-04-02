(ns ui.view.app
  (:require [om.next :as om :refer-macros [defui]]
            [sablono.core :refer-macros [html]]
            [ui.view.bootstrap :as b]
            [ui.view.home :refer [HomeView]]
            [ui.view.club :refer [ClubView]]
            [ui.view.member :refer [MemberView]]
            [markdown.core :refer [md->html]]))

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
                                                  (b/navItem {:eventKey :app/club :href "#/club/declation"} "Club")
                                                  (b/navItem {:eventKey :app/member :href "#/member/leader/1"} "Member")
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