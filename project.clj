(defproject showyoumine "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :plugins [[lein-ring "0.7.4"]]
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [ring "1.1.3"]
                 [compojure "1.1.1"]]
  :ring {:handler showyoumine.core/app})
