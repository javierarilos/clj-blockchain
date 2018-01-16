(ns clj-blockchain.core
  (:require [clj-blockchain.blocks :as blocks]
            :reload)
  (:gen-class))

(defn -main
  "Doing nothing... for the moment."
  [& args]

  (def chain (blocks/new-chain))
  (println "1. New chain, genesys block:")
  (println "  " chain)
  (println)
  (def two-transactions ["Mickey 10 to Pluto" "Donald 5 to Pluto"])
  (def chain (blocks/add-block chain two-transactions))
  (println "2. Chain with one block, two transactions: " two-transactions)
  (println "  " chain)
  (println)
  (def other-transactions ["Pluto 1 to Mickey" "Donald 57 to Pluto"])
  (def chain (blocks/add-block chain other-transactions))
  (println "3. Chain, two blocks, with other transactions:" other-transactions)
  (println "  " chain)
  (println)
  (def chain (blocks/add-block chain two-transactions))
  (println "4. Adding block with same two transactions as first block results in different hash:"
          (:hash chain)
          " vs "
          (get-in chain [:previous :previous :hash]))
  (println "  " chain)
  (println)
  (println "valid?:" (blocks/valid? chain)))
