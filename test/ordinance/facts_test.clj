(ns ordinance.facts-test
  (:require [clojure.string :as str]
            [clojure.test :refer [deftest is]]
            [ordinance.facts :as facts]))

(deftest abu-dhabi-has-spec-basis
  (let [sb (facts/spec-basis "abu-dhabi")]
    (is (= 2 (count sb)))
    (is (every? #(str/starts-with? (:ordinance/url %) "https://") sb))
    (is (every? :ordinance/number sb))))

(deftest unknown-municipality-has-no-spec-basis
  (is (nil? (facts/spec-basis "dubai")))
  (is (nil? (facts/spec-basis "zzz"))))

(deftest coverage-is-honest
  (let [c (facts/coverage ["abu-dhabi" "dubai"])]
    (is (= 2 (:requested c)))
    (is (= 1 (:covered c)))
    (is (= ["dubai"] (:missing-municipalities c)))))

(deftest by-topic-filters
  (is (= ["abu-dhabi.law-30-2019-dmt-establishment"]
         (mapv :ordinance/id (facts/by-topic "abu-dhabi" :governance))))
  (is (empty? (facts/by-topic "abu-dhabi" :labor)))
  (is (empty? (facts/by-topic "dubai" :governance))))
