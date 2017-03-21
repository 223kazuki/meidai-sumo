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

(sec/defroute start "/" []
  (set-root-query! '[:app/start]))

(sec/defroute contact "/contact" []
  (set-root-query! '[:app/contact]))

(sec/defroute about "/about" []
  (set-root-query! '[:app/about]))

(sec/defroute remote "/remote" []
  (set-root-query! '[:app/remote]))

(om/add-root! reconciler App (gdom/getElement "app"))

;; HISTORY
;; =====================================
(let [history (History.)
      navigation EventType/NAVIGATE]
  (goog.events/listen history
                     navigation
                     #(-> % .-token sec/dispatch!))
  (doto history (.setEnabled true)))
