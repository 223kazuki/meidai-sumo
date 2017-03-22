(defproject meidai-sumo "v0.1.0"
  :description "名古屋大学相撲部ホームページ"
  :url "https://meidai-sumo.club"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :dependencies [[org.clojure/clojure "1.9.0-alpha14"]
                 [org.clojure/clojurescript "1.9.293"]
                 [ring "1.5.0"]
                 [compojure "1.5.1"]
                 [figwheel-sidecar "0.5.9"]
                 [org.omcljs/om "1.0.0-alpha48"]
                 [com.stuartsierra/component "0.3.1"]
                 [com.cemerick/piggieback "0.2.1"]
                 [secretary "1.2.3"]
                 [ring-aws-lambda-adapter "0.1.1"]
                 [sablono "0.7.7"]
                 [markdown-clj "0.9.98"]]

  :plugins [[lein-cljsbuild "1.1.4"]
            [lein-figwheel "0.5.4-7"]
            [lein-ring "0.9.7"]]

  :ring {:handler backend.server/handler
         :port 8440}

  :resource-paths ["resources"]

  :source-paths ["src/cljs" "src/clj"]

  :clean-targets ^{:protect false} [:target-path
                                    "resources/public/main.js"
                                    "resources/public/out"]

  :uberjar-name "meidai-sumo.jar"

  :profiles {:dev {:dependencies [[com.cemerick/piggieback "0.2.1"]
                                  [org.clojure/tools.nrepl "0.2.12"]]
                   :repl-options {:nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}}
             :uberjar {:aot :all
                       :resource-paths ^:replace []}}

  :cljsbuild
  {:builds
   [{:id "advanced"
     :source-paths ["src/cljs"]
     :compiler {:output-to "resources/public/main.js"
                :output-dir "resources/public/out"
                :optimizations :advanced
                :pretty-print false}}]})
