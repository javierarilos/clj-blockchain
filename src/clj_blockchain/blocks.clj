(ns clj-blockchain.blocks
  (:require [clj-blockchain.digest :refer :all])
  (:use [clojure.string :only [join]]))

(defn now [] (str (java.util.Date.)))


(defn add-block
  "Add a block to an existing chain. Chain is a pointer to the chain head Block"
  [chain transactions]
  {:previous     chain
   :timestamp    (now)
   :transactions transactions})


(defn new-chain
  "Create a new, empty, BlockChain returning a genesys block."
  []
  (add-block nil nil))


(defn transactions
  "Get all transactions in a chain, new to old"
  ([chain]
   (transactions chain []))
  ([chain acctrans]
   (if-let [tra (:transactions chain)]
     (transactions (:previous chain) (concat acctrans tra))
     acctrans)))


(defn block-digest
  "returns the Hash digest for a given block"
  [block]
  (let [timestamp (:timestamp block)
        transactions (join "" (:transactions block))
        block-str (str timestamp transactions)]
    (sha256 block-str)))
