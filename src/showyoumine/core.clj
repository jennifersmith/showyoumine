(ns showyoumine.core
  (:use compojure.core)
  (:use ring.adapter.jetty)
  (:use showyoumine.git-stats)
  (:use [ ring.util.response :only [response]])
  (:require [compojure.route :as route]))


(def app
  (routes
   (GET "/git-stats" [] (response (fetch-git-stats)))))

(defonce my-server (atom nil))

(defn stop-n-start [server]
  (if server (.stop server))
  (run-jetty #'app {:port 3043 :join? false}))

(defn run-repl-server []
  (swap! my-server stop-n-start))