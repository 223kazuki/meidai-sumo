(ns ui.view.common
  (:require [om.next :as om :refer-macros [defui]]
            [om.dom :as dom]))

(def navi
  (dom/ul nil
          (dom/li nil
                  (dom/a #js {:href "#/"} "Home"))
          (dom/li nil
                  (dom/a #js {:href "#/about"} "About"))
          (dom/li nil
                  (dom/a #js {:href "#/contact"} "Contact"))
          (dom/li nil
                  (dom/a #js {:href "#/remote"} "Remote"))))

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
  Object
  (render [this]
    (let [{:keys [app/remote] :as props} (om/props this)]
      (dom/div nil
               (dom/p nil "test")
               (dom/p nil props)))))

(def query-key->view
  {:app/start StartView
   :app/contact ContactView
   :app/about AboutView
   :app/remote RemoteView})

(defn build-component [props]
  (let [component-key (first (keys props))]
    (when component-key
      ((om/factory (query-key->view component-key)) (props component-key)))))

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
