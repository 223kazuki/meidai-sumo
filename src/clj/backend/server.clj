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
  (atom {:app/member {:members [{:member/id  1 :member/name "加藤 延夫"}
                                {:member/id  2 :member/name "細谷 辰之"}
                                {:member/id  3 :member/name "小川 光"}
                                {:member/id  4 :member/name "辻 年喜" :member/image "tsuji.jpg"}
                                {:member/id  5 :member/name "岸根 翔" :member/image "kishine.jpg"}
                                {:member/id  6 :member/name "新美 将平" :member/image "niimi.jpg"}
                                {:member/id  7 :member/name "近藤 弘章" :member/image "kondou.jpg"}
                                {:member/id  8 :member/name "堤 一樹" :member/image "tsutsumi.jpg"}
                                {:member/id  9 :member/name "野原 裕史" :member/image "nohara.jpg"}
                                {:member/id 10 :member/name "伊藤 かほり" :member/image "itou.jpg"}
                                {:member/id 11 :member/name "河合 賢吾" :member/image "kawai.jpg"}
                                {:member/id 12 :member/name "門松 春女" :member/image "kadomatsu.jpg"}
                                {:member/id 13 :member/name "岩田 将誉" :member/image "iwata.jpg"}
                                {:member/id 14 :member/name "金城 卓史" :member/image "kanashiro.jpg"}
                                {:member/id 15 :member/name "福田 彩香" :member/image "fukuda.jpg"}
                                {:member/id 16 :member/name "大場 泉帆" :member/image "ooba.jpg"}
                                {:member/id 17 :member/name "長合 誠也" :member/image "nagou.jpg"}
                                {:member/id 18 :member/name "壹岐 英明"}
                                {:member/id 19 :member/name "野澤 大輔"}
                                {:member/id 20 :member/name "西田 拓矢" :member/image "nishida.jpg"}
                                {:member/id 21 :member/name "棚橋 義和" :member/image "tanahashi.jpg"}
                                {:member/id 22 :member/name "右田 雄基" :member/image "migita.jpg"}
                                {:member/id 23 :member/name "木村 光史郎" :member/image "kimura.jpg"}
                                {:member/id 24 :member/name "上島 裕実" :member/image "kamishima.jpg"}
                                {:member/id 25 :member/name "舘 陽太" :member/image "tachi.jpg"}]}
         :app/record {:records [{:record/id 1 :record/year "2014"}
                                {:record/id 2 :record/year "2015"}
                                {:record/id 3 :record/year "2016"}]}}))

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
