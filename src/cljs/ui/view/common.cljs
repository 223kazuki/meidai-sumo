(ns ui.view.common
  (:require [om.next :as om :refer-macros [defui]]
            [om.dom :as dom]
            [sablono.core :refer-macros [html]]))

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
  (render [this]
          (let [{:keys [] :as props} (om/props this)]
            (html
              [:div [:h1 "Home"]]))))

(defui ClubView
  Object
  (render [this]
          (let [{:keys [abouts] :as props} (om/props this)]
            (html
              [:div [:h1 "Club"]
               [:ul
                (map #(vec [:li {:key (:about/id %)} (:about/title %)]) abouts)]]))))

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
              [:div
                [:nav.navbar.navbar-default.navbar-fixed-top
                  [:div.container
                    [:div.navbar-header
                      [:button.navbar-toggle.collapsed {:type "button" :data-toggle "collapse" :data-target "#collapse-1" :aria-expanded "false"}
                        [:span.sr-only "Toggle navigation"]
                        [:span.icon-bar]
                        [:span.icon-bar]
                        [:span.icon-bar]]
                      [:a.navbar-brand {:href "#"} "名大相撲部"]]
                    [:div.collapse.navbar-collapse {:id "collapse-1"}
                      [:ul.nav.navbar-nav
                        [:li (when (= component-key :app/home) {:class "active"}) [:a {:href "#/"} "Home"]]
                        [:li (when (= component-key :app/club) {:class "active"}) [:a {:href "#/club"} "Club"]]
                        [:li (when (= component-key :app/member) {:class "active"}) [:a {:href "#/member"} "Member"]]
                        [:li (when (= component-key :app/record) {:class "active"}) [:a {:href "#/record"} "Record"]]
                        [:li (when (= component-key :app/blog) {:class "active"}) [:a {:href "#/blog"} "Blog"]]
                        [:li (when (= component-key :app/photo) {:class "active"}) [:a {:href "#/photo"} "Photo"]]
                        [:li (when (= component-key :app/movie) {:class "active"}) [:a {:href "#/movie"} "Movie"]]
                        [:li (when (= component-key :app/masumeidai) {:class "active"}) [:a {:href "#/masumeidai"} "舛名大"]]
                        [:li (when (= component-key :app/media) {:class "active"}) [:a {:href "#/media"} "Media"]]
                        [:li (when (= component-key :app/link) {:class "active"}) [:a {:href "#/link"} "Link"]]
                        [:li (when (= component-key :app/mail) {:class "active"}) [:a {:href "#/mail"} "Mail"]]]]]]
                (build-component props)]))))
