(ns backend.server
  (:gen-class)
  (:require [compojure.core :refer [GET POST defroutes]]
            [compojure.route :as route]
            [om.next.server :as om]
            [ring.util.response :refer [resource-response content-type]]
            [backend.middleware :refer [wrap-transit-params
                                        wrap-transit-response]]
            [ring-aws-lambda-adapter.core :refer [defhandler]]
            [net.cgrand.enlive-html :as html :refer [deftemplate]]))

(defonce app-state
  (atom {}))

(defmulti read-server om/dispatch)
(defmethod read-server :default
  [env k params]
  (let [st @(:state env)]
    {:value (get-in st [k] {})}))

(defmulti mutate-server om/dispatch)

(def server-parser
  (om/parser {:read read-server
              :mutate mutate-server}))

(defn generate-transit-response [data & r]
  {:status  200
   :headers {"Content-Type" "application/transit+json; charset=utf-8"}
   :body    data})

(deftemplate index "index.html"
  []
  [:head [:meta (html/attr-has :name "api-endpoint")]]
  (html/set-attr :content "/api"))

(defroutes main-routes
  (POST "/api" request (generate-transit-response
                         (server-parser {:state app-state} (:transit-params request))))
  (route/files "/" {:root "public"})
  (GET "/" [] (apply str (index)))
  (route/not-found "404 Page not found!"))

(def handler
  (-> main-routes
      wrap-transit-params
      wrap-transit-response))

(defhandler club.meidai-sumo.OmNextHandler handler {})
