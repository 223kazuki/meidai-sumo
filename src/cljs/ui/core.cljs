(ns ui.core
  (:require [secretary.core :as sec :include-macros true]
            [goog.events :as events]
            [goog.history.EventType :as EventType]
            [goog.dom :as gdom]
            [om.next :as om]
            [ui.util :refer [transit-post]]
            [ui.state :refer [app-state]]
            [ui.parser :refer [app-parser]]
            [ui.view.app :refer [App]])
  (:import goog.History))

(enable-console-print!)

(def api-endpoint (.. js/document
                      (querySelector "meta[name=api-endpoint]")
                      (getAttribute "content")))

(def reconciler (om/reconciler {:state app-state
                                :parser app-parser
                                :send (transit-post api-endpoint)}))

;; ROUTES
;; =====================================
(sec/set-config! :prefix "#")

(defn set-root-query!
  ([query]
   (let [root (om/app-root reconciler)]
     (om/set-query! root {:query query}))))

(sec/defroute home "/" []
              (set-root-query! '[:app/home]))

(sec/defroute club "/club/:id" {:keys [id]}
              (set-root-query! `[(:app/club {:selected ~id})]))

(sec/defroute member "/member/:grade/:id" {:keys [grade id]}
              (set-root-query! `[(:app/member {:selected {:grade ~grade :id ~id}})]))

(sec/defroute record "/record/:year" {:keys [year]}
              (set-root-query! `[(:app/record {:selected ~year})]))

(sec/defroute blog "/blog" []
              (set-root-query! '[:app/blog]))

(sec/defroute photo "/photo" []
              (set-root-query! '[:app/photo]))

(sec/defroute movie "/movie" []
              (set-root-query! '[:app/movie]))

(sec/defroute masumeidai "/masumeidai/:tab" {:keys [tab]}
              (set-root-query! `[(:app/masumeidai {:selected ~tab})]))

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
