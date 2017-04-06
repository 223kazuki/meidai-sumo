(ns ui.view.member
  (:require [om.next :as om :refer-macros [defui]]
            [sablono.core :refer-macros [html]]
            [cljsjs.react-bootstrap]
            [ui.view.bootstrap :as b]))

(defui MemberView
  Object
  (render [this]
          (let [{:keys [grades member/by-id selected] :as props} (om/props this)]
            (html
              [:div [:h1 "Member"]
               (b/tabContainer {:id "grade-tabs" :defaultActiveKey "leader" :activeKey (:grade selected)
                                :onSelect (fn [id _] (aset js/window "location" (str "/#/member/" id "/"
                                                                                     (->> grades
                                                                                          (filter #(= id (name (:grade/id %))))
                                                                                          first
                                                                                          :grade/members
                                                                                          first
                                                                                          second))))}
                               (html [:div.raw.clearfix
                                      [:div.col-sm-4
                                       (b/nav {:bsStyle "pills" :stacked true}
                                              (map #(b/navItem {:key (name (:grade/id %)) :eventKey (name (:grade/id %))} (:grade/name %)) grades))]
                                      [:div.col-sm-8
                                       (b/tabContent {:animation true}
                                                     (map (fn [grade]
                                                            (b/tabPane {:key (:grade/id grade) :eventKey (name (:grade/id grade))}
                                                                       (b/tabs {:activeKey (js/parseInt (:id selected)) :onSelect (fn [id _] (aset js/window "location" (str "/#/member/" (name (:grade/id grade)) "/" id))) :id "member"}
                                                                               (map
                                                                                 (fn [[_ id]]
                                                                                   (let [member (get-in by-id [id])]
                                                                                     (b/tab {:key id :eventKey id :title (:member/name member)}
                                                                                            (html [:div
                                                                                                   (when-let [image (:member/image member)] [:img.member {:src (str "/img/member/" image)}])
                                                                                                   (when-let [introduction (:member/introduction1 member)] [:div (map #(vec [:div %]) introduction)])
                                                                                                   (when-let [introduction (:member/introduction2 member)] [:div (map #(vec [:div %]) introduction)])
                                                                                                   (when-let [introduction (:member/introduction3 member)] [:div (map #(vec [:div %]) introduction)])]))))
                                                                                 (:grade/members grade)))))
                                                          grades))]]))]))))
