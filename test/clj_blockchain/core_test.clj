(ns clj-blockchain.core-test
  (:require [clojure.test :refer :all]
            [clj-blockchain.core :refer :all]))

(deftest a-test
  (testing "Testing about the truth of life."
    (is (= 1 1))))

(deftest hash-is-repeatable
  (testing "Hashing same thing twice produces same results"
    (is (= (sha256 "jajaja") (sha256 "jajaja")))))

(deftest hash-is-a-signature
  (testing "Hashing something produces a signature different to the original thing"
    (is (not= (sha256 "jajaja") "jajaja"))))

(deftest hash-is-always-the-same
  (testing "Hashing something produces always the same result"
    (is (= (sha256 "jajaja") "a887c3cb3de97b3968be5c7fb81ed9e452928db71d66620b7766bf7c5413878"))))

(deftest hash-is-not-reversible
  (testing "The hash of a hash does not produce the originally hashed thing"
    (let
      [jajaja-hash (sha256 "jajaja")
       jajaja-hash-hash (sha256 jajaja-hash)]
      (is (not= "jajaja" jajaja-hash-hash)))))
