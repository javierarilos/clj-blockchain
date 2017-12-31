(ns clj-blockchain.digest-test
  (:require [clojure.test :refer :all]
            [clj-blockchain.digest :refer :all]
            [clj-fuzzy.metrics :refer :all]))

(deftest hash-is-deterministic-same-twice
  (testing "Hashing is deterministic, hashing a message twice produces same digest"
    (is (= (sha256 "jajaja") (sha256 "jajaja")))))


(deftest hash-is-deterministic-always-the-same
  (testing "Hashing a message produces always the same digest"
    (is (= (sha256 "jajaja") "a887c3cb3de97b3968be5c7fb81ed9e452928db71d66620b7766bf7c5413878"))))

(deftest hash-is-a-signature
  (testing "Hashing a message produces digest that's different to the message"
    (is (not= (sha256 "jajaja") "jajaja"))))

(deftest hash-is-not-reversible
  (testing "The digest of a digest does not produce the original message"
    (let
      [jajaja-hash (sha256 "jajaja")
       jajaja-hash-hash (sha256 jajaja-hash)]
      (is (not= "jajaja" jajaja-hash-hash)))))

(deftest hash-has-low-collision
  (testing "Hashing similar messages produce very different digests, according to Levenshtein distance"
    (is (> (levenshtein (sha256 "jajaja") (sha256 "jajajE")) 50))
    (is (> (levenshtein (sha256 "jajaja") (sha256 "jajajI")) 50))
    (is (> (levenshtein (sha256 "jajaja") (sha256 "jajajI")) 50))))
