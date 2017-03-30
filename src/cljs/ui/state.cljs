(ns ui.state)

(defonce app-state
  {:app/club {:abouts
              [{:about/id 1 :about/title "また名大相撲部宣言" :about/content "我ら名古屋大学体育会相撲部は、経済と情報の急激なグローバリゼーションの進行する今日、人類社会の福祉の向上、国際間の平和、地球環境の保全をその使命とし、2000年4月、日本国愛知県名古屋市に設立された。  
                                                                    
                                                                    我ら名古屋大学体育会相撲部は、地球というこの天体の地表で文化と経済のダイナミズム維持に貢献するために、日本列島が育んできた歴史を継承発展させることをその理想とし、相撲という文化の復興を目論見、研鑽を続けている。  
                                                                    
                                                                    我ら名古屋大学体育会相撲部は、大量生産、大量消費の際限のない増殖から、循環経済への移行期にあり、物質によらない繁栄の一つの形式の発露として、相撲という文化を通じて遊び通すことに全力を傾注する。  
                                                                    
                                                                    我ら名古屋大学体育会相撲部は、簡便であることや、コストパフォーマンスを尊ぶ風潮を下品であると断罪し、不必要なまでに格好をつけることに拘り続ける。  
                                                                    
                                                                    我ら名古屋大学体育会相撲部は、「五層櫓のてっぺんに金のシャチホコ雨ざらし、ああこりゃこりゃ。」の精神を胸に秘め、日々偏見や陋習と戦い続ける。  
                                                                    
                                                                    我ら名古屋大学体育会相撲部は、何をおいても歌舞き、槍が降ろうが、生首が飛ぼうが、歌舞き続けることをここに宣言する、がや。  "}
               {:about/id 2 :about/title "新入部員に向けて" :about/content ""}
               {:about/id 3 :about/title "OBの就職先" :about/content ""}
               {:about/id 4 :about/title "稽古詳細" :about/content ""}
               {:about/id 5 :about/title "ちゃんこ" :about/content ""}
               {:about/id 6 :about/title "年間予定" :about/content ""}
               {:about/id 7 :about/title "第八高等学校寮歌 伊吹おろし" :about/content ""}]}
   :app/member {:members
                [{:member/id  1 :member/name "加藤 延夫"}
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
   :app/record {:records
                [{:record/id 1 :record/year "2014"}
                 {:record/id 2 :record/year "2015"}
                 {:record/id 3 :record/year "2016"}]}})
