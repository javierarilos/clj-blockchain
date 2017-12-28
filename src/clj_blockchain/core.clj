(ns clj-blockchain.core
  (:gen-class))

(defn -main
  "Doing nothing... for the moment."
  [& args]
  (println "Hello, World! ... clj-blockchain"))

(defn sha256 [string]
  (let
    [md (java.security.MessageDigest/getInstance "SHA-256")
     byt (. string getBytes)
     digest (. md digest byt)]
    (clojure.string/join (map (fn [_] (Integer/toHexString (bit-and _ 0xFF))) digest))))
