(ns clj-blockchain.blocks
  (:require [clj-blockchain.digest :refer :all])
  (:use [clojure.string :only [join]]))

(defn now [] (str (java.util.Date.)))


(defn block-digest
  "returns the Hash digest for a given block"
  [block]
  (let [id        (:id block)
        timestamp (:timestamp block)
        prev-hash (:prev-hash block)
        transactions (join "" (:transactions block))
        block-str (str id timestamp prev-hash transactions)]
    (sha256 block-str)))


(defn assoc-digest
  [block]
  (assoc block :hash (block-digest block)))


(defn add-block
  "Add a block to an existing chain. Chain is a pointer to the chain head Block"
  [previous transactions]
  (let [
    block-no-hash {
      :id           (inc (:id previous))
      :timestamp    (now)
      :prev-hash    (:hash previous)
      :previous     previous
      :transactions transactions}]
    (assoc-digest block-no-hash)))


(defn new-chain
  "Create a new, empty, BlockChain returning a genesys block."
  []
  (let [
    block-no-hash {
      :id           0
      :timestamp    (now)
      :prev-hash    nil
      :previous     nil
      :transactions []}]
    (assoc-digest block-no-hash)))


(defn transactions
  "Get all transactions in a chain, new to old"
  ([chain]
   (transactions chain []))
  ([chain acctrans]
   (if-let [tra (:transactions chain)]
     (transactions (:previous chain) (concat acctrans tra))
     acctrans)))
