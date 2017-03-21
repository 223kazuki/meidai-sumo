(ns ui.core
  (:require [secretary.core :as sec :include-macros true]
            [goog.events :as events]
            [goog.history.EventType :as EventType]
            [goog.dom :as gdom]
            [om.next :as om]
            [ui.util :refer [transit-post]]
            [ui.state :refer [app-state]]
            [ui.parser :refer [app-parser]]
            [ui.view.common :refer [App]])
  (:import goog.History))

(def reconciler (om/reconciler {:state app-state
                                :parser app-parser
                                :send (transit-post "/api")}))

;; ROUTES
;; =====================================
(sec/set-config! :prefix "#")

(defn set-root-query!
  ([query]
   (let [root (om/app-root reconciler)]
     (om/set-query! root {:query query}))))

(sec/defroute home "/" []
  (set-root-query! '[:app/home]))

(sec/defroute club "/club" []
  (set-root-query! '[:app/club]))

(sec/defroute member "/member" []
  (set-root-query! '[:app/member]))

(sec/defroute record "/record" []
  (set-root-query! '[:app/record]))

(sec/defroute blog "/blog" []
  (set-root-query! '[:app/blog]))

(sec/defroute photo "/photo" []
  (set-root-query! '[:app/photo]))

(sec/defroute movie "/movie" []
  (set-root-query! '[:app/movie]))

(sec/defroute masumeidai "/masumeidai" []
  (set-root-query! '[:app/masumeidai]))

(sec/defroute media "/media" []
  (set-root-query! '[:app/media]))

(sec/defroute link "/link" []
  (set-root-query! '[:app/link]))

(sec/defroute mail "/mail" []
  (set-root-query! '[:app/mail]))

(om/add-root! reconciler App (gdom/getElement "app"))

;; HISTORY
;; =====================================
(let [history (History.)
      navigation EventType/NAVIGATE]
  (goog.events/listen history
                     navigation
                     #(-> % .-token sec/dispatch!))
  (doto history (.setEnabled true)))
