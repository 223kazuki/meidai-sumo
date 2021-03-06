(ns ui.view.masumeidai
  (:require [om.next :as om :refer-macros [defui]]
            [sablono.core :refer-macros [html]]
            [ui.view.bootstrap :as b]))

(defui MasumeidaiView
  Object
  (render [this]
          (let [{:keys [selected] :as props} (om/props this)]
            (html
              [:div#masumeidai
               [:h1 "Masumeidai"]
               (b/tabs {:activeKey selected :onSelect (fn [tab _] (aset js/window "location" (str "/#/masumeidai/" tab))) :id "masumeidai"}
                       (b/tab {:key "profile" :eventKey "profile" :title "プロフィール"}
                              (html [:div
                                     "四股名：舛名大 周一" [:br]
                                     "本名：田中 周一" [:br]
                                     "あだ名：たなしゅう" [:br]
                                     "出身地：愛知県岡崎市康生町" [:br]
                                     "生年月日：昭和59年3月16日" [:br]
                                     "所属部屋：千賀ノ浦部屋" [:br]
                                     "初土俵：平成18年11月" [:br]
                                     "最高位：東三段目89枚目（2009年5月場所）" [:br]
                                     "生涯戦歴：56勝56敗0休（17場所）" [:br]
                                     "得意技：寝坊" [:br]
                                     "血液型：A型" [:br]
                                     "身長：189㌢" [:br]
                                     "体重：130㌔" [:br]
                                     "好きな味噌：八丁味噌"]))
                       (b/tab {:key "history" :eventKey "history" :title "略歴（Wikipediaより）"}
                              (html [:div
                                     "舛名大 周一（ますめいだい しゅういち、本名：田中 周一（たなか - ） 1984年3月16日 - ）は、愛知県岡崎市出身で千賀ノ浦部屋所属の現役大相撲力士。身長189cm、体重116kg。最高位は東三段目89枚目（2009年5月場所）。あだなは「たなしゅう」。" [:br]
                                     [:br]
                                     "岡崎市立甲山中学校、愛知県立豊田西高等学校を経て、名古屋大学工学部化学・生物工学科卒業（1年留年している）。" [:br]
                                     "高校時代は水泳部に所属し、自由形の選手だった。大学1年の夏に学園祭の企画で行われた相撲大会に参加して初めて相撲を取り、「相撲はシンプルだけど奥深い」と魅力を感じて相撲部に入部。2年からレギュラーとなり、2005年・2006年の全国国公立大学対抗相撲大会団体戦2連覇に貢献。相撲部を見学に来た千賀ノ浦親方から熱心に誘われ、大学院受験に失敗したこともあり、2006年9月に入門を決意。国立大学出身としては一ノ矢（琉球大学理学部卒）、弓の里（高知大学農学部1年中退）に次ぐ史上3人目の大相撲力士となった。" [:br]
                                     "「舛名大」という四股名は、師匠の現役時代の四股名・舛田山と大学名を合わせたもの。名付け親は名大相撲部の細谷辰之師範。" [:br]
                                     "2006年11月場所で初土俵を踏んだ。前相撲は3勝1敗。11月19日に新序出世披露を受け、師匠が現役最後につけた「加賀獅子」が描かれた赤色の化粧まわしで土俵に登場。工学部の学生らしく「重いなあ。単純に、物理的にですよ」と表現した。" [:br]
                                     "初めて番付に名前を載せた2007年1月場所では、まだ卒業要件を満たしていなかったため、名古屋と東京を新幹線で往復し、大学に出席しながら相撲をとる場所となった。一番相撲に敗れたもののそこから6連勝で優勝決定戦に進んだ。惜しくも優勝はならなかったが、相撲でも話題になる活躍を見せた。2007年3月2日に卒論発表（テーマは「サケの卵細胞の構造分析」）を終え、3月場所から相撲に専念できるようになった。しかし翌5月場所は壁に当たり、1勝を挙げた後6連敗。7月場所2日目には一ノ矢と対戦し、史上初の国立大学卒業力士による取組が実現した（引き落としで敗れた）。" [:br]
                                     [:br]
                                     "2006年11月九州場所 前相撲（3勝1敗）" [:br]
                                     "2007年1月初場所 序ノ口（6勝1敗優勝決定巴戦進出）" [:br]
                                     "2007年3月春場所 序二段" [:br]
                                     "2009年5月夏場所 三段目" [:br]
                                     [:br]
                                     "2009年7月場所終了現在" [:br]
                                     "通算成績：56勝56敗（17場所）"]))
                       (b/tab {:key "hoshi" :eventKey "hoshi" :title "星取表"}
                              (html [:div
                                     [:p "平成20年秋場所　（西序二段35枚目）"]
                                     [:p "　　　○－－－－－－－－－－－－－－　　：１勝０杯"]
                                     [:br]
                                     [:p "平成20年名古屋場所　（西序二段60枚目）"]
                                     [:p "　　　－○－●○－●－●－○－－○－　　：４勝３敗"]
                                     [:br]
                                     [:p "平成20年夏場所　（東序二段27枚目）"]
                                     [:p "　　　－●－●－○－●－○－●－－●　　：２勝５敗"]
                                     [:br]
                                     [:p "平成20年大阪場所　（西序二段52枚目）"]
                                     [:p "　　　－●●－－○○－－●○－－○－　　：４勝３敗"]
                                     [:br]
                                     [:p "平成20年初場所　（西序二段80枚目）"]
                                     [:p "　　　○－●－○－－●●－－○－○－　　：４勝３敗"]
                                     [:br]
                                     [:p "平成19年九州場所　（西序二段56枚目）"]
                                     [:p "　　　●－－○●－●－●－○－－－○　　：３勝４敗"]
                                     [:br]
                                     [:p "平成19年秋場所　（東序二段82枚目）"]
                                     [:p "　　　－○－○○－●－－●－○●－－　　：４勝３敗"]
                                     [:br]
                                     [:p "平成19年名古屋場所　（西序二段60枚目）"]
                                     [:p "　　　－●●－●－○－○－○－－●－　　：３勝４敗"]
                                     [:br]
                                     [:p "平成19年夏場所　（西序二段23枚目）"]
                                     [:p "　　　－○－●●－●－●－●－－●－　　：１勝６敗"]
                                     [:br]
                                     [:p "平成19年大阪場所　（東序二段62枚目）"]
                                     [:p "　　　○－○－○－－○○－－●－●－　　：５勝２敗"]
                                     [:br]
                                     [:p "平成19年初場所　（西序ノ口30枚目）"]
                                     [:p "　　　－●○－○－－○○－－●－●－　　：６勝１敗"]
                                     [:br]
                                     [:p "平成18年九州場所　（前相撲）"]
                                     [:p "　　　－－●○○○－－－－－－－－－　　：３勝１敗"]])))]))))
