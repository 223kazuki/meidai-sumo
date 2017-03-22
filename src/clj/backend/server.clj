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

(defmulti read-server om/dispatch)
(defmethod read-server :app/remote
  [env dispatch-key params]
  {:value {:greeting "Hello from the backend with some transit love."}})
(defmethod read-server :app/member
  [env dispatch-key params]
  {:value {:members [{:member/id 1 :member/name "Kondo"}
                     {:member/id 2 :member/name "Nohara"}
                     {:member/id 3 :member/name "Tsutsumi"}]}})
(defmethod read-server :app/redord
  [env dispatch-key params]
  {:value {:records [{:record/id 1 :record/year "2014"}
                     {:record/id 2 :record/year "2015"}
                     {:record/id 3 :record/year "2016"}]}})

(defmulti mutate-server om/dispatch)

(def server-parser
  (om/parser {:read read-server
              :mutate mutate-server}))

(defn generate-transit-response [data & r]
  {:status  200
   :headers {"Content-Type" "application/transit+json"}
   :body    data})

(deftemplate index "index.html"
  []
  [:head [:meta (html/attr-has :name "api-endpoint")]]
  (html/set-attr :content "/api"))

(defroutes main-routes
  (POST "/api" request (generate-transit-response
                         (server-parser {} (:transit-params request))))
  (route/files "/" {:root "public"})
  (GET "/" [] (apply str (index)))
  (route/not-found "404 Page not found!"))

(def handler
  (-> main-routes
      wrap-transit-params
      wrap-transit-response))

(defhandler club.meidai-sumo.OmNextHandler handler {})
