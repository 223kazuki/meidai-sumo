(ns ui.view.mail
  (:require [om.next :as om :refer-macros [defui]]
            [sablono.core :refer-macros [html]]
            [ui.view.bootstrap :as b]))

(defui MailView
  Object
  (render [this]
          (let [{:keys [] :as props} (om/props this)]
            (html
              [:div#mail [:h1 "Mail"]
               [:p "名古屋大学相撲部に関するお問い合わせは下記のアドレスまでお願いします。"]
               [:img {:src "/img/mail.jpg"}]
               [:p "（スパム対策のためアドレスは画像で表示させてあります。）"]]))))
