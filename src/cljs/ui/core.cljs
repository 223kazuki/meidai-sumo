(ns ui.core
  (:require [om.next :as om :refer-macros [defui]]
            [om.dom :as dom]
            [ui.util :refer [transit-post]]
            [goog.dom :as gdom]
            [secretary.core :as sec :include-macros true]
            [goog.events :as events]
            [goog.history.EventType :as EventType]
            [accountant.core :as accountant])
  (:import goog.History))

(enable-console-print!)

;; STATE
;; =====================================
(defonce app-state {:about {:me "I'm just a poor boy, nobody loves me!" }})

(defmulti read om/dispatch)

(defmethod read :default [_ _ _]
  {:value {}})

(defmethod read :app/about
  [{:keys [state] :as env} key params]
  {:value (get-in @state [:about])})

(defmethod read :example-remote
  [env k params]
  (let [st @(:state env)]
    {:remote true
     :value (get-in st [k :greeting])}))

(def app-parser (om/parser {:read read}))

(def reconciler
  (om/reconciler {:state app-state
                  :parser app-parser
                  :send (transit-post "/api")}))

;; HELPERS
;; =====================================
(def navi
  (dom/ul nil
          (dom/li nil
                  (dom/a #js {:href "/"} "Home"))
          (dom/li nil
                  (dom/a #js {:href "/about"} "About"))
          (dom/li nil
                  (dom/a #js {:href "/contact"} "Contact"))
          (dom/li nil
                  (dom/a #js {:href "/remote"} "Remote"))))

;; VIEWS
;; =====================================
(defui AboutView
  Object
  (render [this]
          (let [{:keys [me] :as props} (om/props this)]
            (dom/h1 nil me))))

(defui ContactView
  Object
  (render [this]
          (dom/h1 nil "Get in touch!")))

(defui StartView
  Object
  (render [this]
          (dom/h1 nil "Welcome!")))

(defui RemoteView
  static om/IQuery
  (query [this]
    '[:example-remote])
  Object
  (render [this]
    (let [{:keys [example-remote] :as props} (om/props this)]
      (println (om/props this))
      (dom/div nil
               (dom/p nil "test")
               (dom/p nil props)))))

;; COMPONENT
;; =====================================
(def query-key->view
  {:app/start StartView
   :app/contact ContactView
   :app/about AboutView
   :app/remote RemoteView})

(defn build-component [props]
  (let [component-key (first (keys props))]
    ((om/factory (query-key->view component-key)) (props component-key))))

;; ROOT
;; =====================================
(defui App
  static om/IQuery
  (query [this]
         '[:app/start])
  Object
  (render [this]
          (let [props (om/props this)]
            (dom/div nil
                     navi
                     (build-component props)))))

(defn init! []
  (accountant/configure-navigation!
    {:nav-handler (fn [path] (sec/dispatch! path))
     :path-exists? (fn [path] (sec/locate-route path))})
  (om/add-root! reconciler App (gdom/getElement "app")))

(init!)

;; HISTORY
;; =====================================
(let [history (History.)
      navigation EventType/NAVIGATE]
  (goog.events/listen history
                     navigation
                     #(-> % .-token sec/dispatch!))
  (doto history (.setEnabled true)))

;; ROUTES
;; =====================================
(sec/set-config! :prefix "")

(defn set-root-query!
  ([query]
   (let [root (om.next/app-root reconciler)]
     (om.next/set-query! root {:query query}))))

(sec/defroute start "/" []
  (set-root-query! '[:app/start]))

(sec/defroute contact "/contact" []
  (set-root-query! '[:app/contact]))

(sec/defroute about "/about" []
  (set-root-query! '[:app/about]))

(sec/defroute remote "/remote" []
  (set-root-query! '[:app/remote]))

(accountant/dispatch-current!)
