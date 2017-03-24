(ns ui.view.common
  (:require [om.next :as om :refer-macros [defui]]
            [om.dom :as dom]
            [sablono.core :refer-macros [html]]
            [cljsjs.react-bootstrap]
            [secretary.core :as sec]))

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
              [:div#home
               [:div.jumbotron
                [:h1 "名古屋大学相撲部"]
                [:p "Nagoya University Sumo Club"]
                [:p.lead
                 [:a.btn.btn-primary.btn-lg {:href "https://twitter.com/nu_sumo" :role "button"} "Twitter"]]
                [:p.lead
                 [:a.btn.btn-primary.btn-lg {:href "https://www.facebook.com/NUSUMOCLUB/" :role "button"} "Facebook"]]]]))))

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

(defn navbar [props & children] (apply js/React.createElement (aget js/ReactBootstrap "Navbar") (clj->js props) children))
(defn navbarHeader [props & children] (apply js/React.createElement (aget js/ReactBootstrap "Navbar" "Header") (clj->js props) children))
(defn navbarBrand [props & children] (apply js/React.createElement (aget js/ReactBootstrap "Navbar" "Brand") (clj->js props) children))
(defn navbarCollapse [props & children] (apply js/React.createElement (aget js/ReactBootstrap "Navbar" "Collapse") (clj->js props) children))
(defn navbarToggle [props & children] (apply js/React.createElement (aget js/ReactBootstrap "Navbar" "Toggle") (clj->js props) children))
(defn nav [props & children] (apply js/React.createElement (aget js/ReactBootstrap "Nav") (clj->js props) children))
(defn navItem [props & children] (apply js/React.createElement (aget js/ReactBootstrap "NavItem") (clj->js props) children))
(defn navDropdown [props & children] (apply js/React.createElement (aget js/ReactBootstrap "NavDropdown") (clj->js props) children))
(defn menuItem [props & children] (apply js/React.createElement (aget js/ReactBootstrap "MenuItem") (clj->js props) children))

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
                (navbar {:fixedTop true :collapseOnSelect true :inverse true}
                  (navbarHeader {}
                    (navbarBrand {} "名大相撲部")
                    (navbarToggle {}))
                  (navbarCollapse {}
                    (nav {:activeKey component-key}
                      (navItem {:eventKey :app/home} "Home")
                      (navItem {:eventKey :app/club} "Club")
                      (navItem {:eventKey :app/member} "Member")
                      (navItem {:eventKey :app/record} "Record")
                      (navItem {:eventKey :app/blog} "Blog")
                      (navItem {:eventKey :app/photo} "Photo")
                      (navItem {:eventKey :app/movie} "Movie")
                      (navItem {:eventKey :app/masumeidai} "舛名大")
                      (navItem {:eventKey :app/media} "Media")
                      (navItem {:eventKey :app/link} "Link")
                      (navItem {:eventKey :app/mail} "Mail"))))
               [:div.row
                [:div.hidden-xs.col-md-2]
                [:div.col-xs-12.col-md-8#content
                 (build-component props)
                 [:footer
                  [:small "Copyright 2017-2022 Nagoya University Sumo Club. All Rights Reserved."]]]
                [:div.hidden-xs.col-md-2]]]))))
