(ns clj-blockchain.core
  (:require [clj-blockchain.blocks :as blocks]
            :reload)
  (:gen-class))

(defn -main
  "Doing nothing... for the moment."
  [& args]

  (def chain (blocks/new-chain))
  (println "New chain digest:" (blocks/block-digest chain))
  (def chain (blocks/add-block chain ["Mickey 10 to Pluto" "Donald 5 to Pluto"]))
  (println "One transaction block chain digest:" (blocks/block-digest chain))
  (def chain (blocks/add-block chain ["Pluto 1 to Mickey" "Donald 57 to Pluto"]))
  (println "Two transaction blocks chain digest:" (blocks/block-digest chain))
  (def chain (blocks/add-block chain ["Mickey 10 to Pluto" "Donald 5 to Pluto"]))
  (println "Adding third block with same transactions is different signature because of timestamp:" (blocks/block-digest chain)))
