(ns smuggler.test.core
  (:use [smuggler.core])
  (:use [clojure.test]))


(deftest parse-int-returns-int
         ; test to make sure it returns an integer
         (is (= 5 (parse-int "5")))
         (is (instance? Integer (parse-int "5")))
         )

; make sure it doesn't return a number > maxsize 
(deftest get-random-number-doesnt-exceed-max
         (not (> 10 (get-random-number 10)))
         )

; kinda useless, but not sure how to test read-line atm
(deftest prompt-read-exists
         (is (function? prompt-read))
         )
; just make sure it exists as well
(deftest main-exists
         (is (function? -main))
         )

(deftest generate-shipment-returns-proper-data
         (def shipment-data (generate-shipment 1))
         (let [{
                doll-weight :weight
                doll-value :value
                doll-name :name
                } (get shipment-data 0)]
           ; name should be correct
           (is (= doll-name "doll-1"))
           ; doll weight should be > 0 and not a string etc.
           (is (> doll-weight 0))
           ; doll value should be > 0 and not a string etc.
           (is (> doll-value 0))
           )
         )


; make sure the fill handbag works properly
(deftest fill-handbag-calculates-properly
         (let [dolls-available 
           [
            {:name "doll-1", :weight 7, :value 3}
            {:name "doll-2", :weight 6, :value 18}
            {:name "doll-3", :weight 6, :value 14}]]
            (let [[street-value dolls-selected] (fill-handbag dolls-available 2 12)]
              (is (= 32 street-value))
              (is (= 2 (count dolls-selected))))
           )
         )


; just to make sure we have a memoized function
(deftest fill-handbag-cached-exists
         (is (function? fill-handbag-cached))
         )
