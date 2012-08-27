(ns showyoumine.git-stats
  (:require [monger.core :as mg])
  (:require
   [monger.collection :as mc])
  (:import [org.bson.types ObjectId]
           [com.mongodb MongoOptions ServerAddress]))

(defn mongo-url []
  (or
   (System/getenv "MONGOHQ_URL")
   "mongodb://127.0.0.1/showyoumine"))

(defn connect [] (mg/connect-via-uri! (mongo-url)))

(defn random-name []
  (let [first
        ["angry" "happy" "purple" "super"
         "green" "waspish" "brilliant" "cheeky"]
        second ["lemur" "monkey" "honeybadger" "ferret"
                "sugar-glider" "molerat" "polecat" "fox" ]]
    (apply str (interpose "-" (map rand-nth [first second])))))

(defn make-temp-stat []
  {:number_of_commits (rand-int 2000)
   :name (random-name)})

(defn seed-database []
  (mc/insert-batch
   "git-stats"
   (take 20 (repeatedly make-temp-stat))))

(defn clear-database [] (mc/remove "git-stats"))
(defn fetch-git-stats []
  (connect)
  (mc/find-maps "git-stats"))
