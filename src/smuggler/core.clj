(ns smuggler.core (:gen-class))

; Setup our main method
(defn get-random-number
  "Generate a random number between 1 and maxsize."
  [maxsize]
  ( inc (rand-int maxsize)))

(defn generate-shipment
  "Generate the shipment, pass in max #"
  [total-dolls]
  (vec (map #(hash-map :name (str "doll-" %) 
                       :weight (get-random-number 10) 
                       :value (get-random-number 20)) (range 1 (inc total-dolls)))))

(declare fill-handbag-cached)

(defn fill-handbag
  "Fill the handbag. Pass in info from generate-shipment, count of dolls and size of handbag"
  [dolls-available index size-of-handbag]
  (cond
    ; if index < 0 or handbag size = 0
    (< index 0) [0 []]
    (zero? size-of-handbag) [0 []]
    :else
    (let [{doll-weight :weight doll-value :value} (get dolls-available index)]
      (if (> doll-weight size-of-handbag)
        ; weights too much run again
        (fill-handbag-cached dolls-available (dec index) size-of-handbag)
        ; else
        (let [ new-handbag-size (- size-of-handbag doll-weight)
              [vn sn :as no] (fill-handbag-cached dolls-available (dec index) size-of-handbag)
              [vy sy :as yes] (fill-handbag-cached dolls-available (dec index) new-handbag-size)]
          (if (> (+ vy doll-value) vn)
            ; keep
            [(+ vy doll-value) (conj sy index)]
            ; skip / shortcut
            no))))))

(def fill-handbag-cached (memoize fill-handbag))

; parse input to integer
(defn parse-int [string]
  (Integer. (re-find #"[0-9]*" string)))

; Setup a prompt to ask questions
(defn prompt-read [prompt]
  (print (format "%s: " prompt))
  (flush)
  (parse-int (read-line)))

; Run the script.
(defn -main []
  ; return the total value and the dolls selected
  (let [size-of-handbag (prompt-read "How much can granny carry?")
        total-dolls-available (prompt-read "How many dolls do you have?")
        dolls-available (generate-shipment total-dolls-available)
        [total-street-value selected-dolls] (fill-handbag-cached dolls-available (-> dolls-available count dec) size-of-handbag)
        ; grab the names from the dolls-available based on selected-dolls
        names (map (comp :name dolls-available) selected-dolls)]
    (println "Street Value:" total-street-value)
    ; add up the weights.
    (println "Total Weight:" (reduce + (map (comp :weight dolls-available) selected-dolls)) "for handbag size of" size-of-handbag)
    (println "\n\n---debug----")
    ; names of the dolls for debug
    (println "\nSelected Dolls:" names)
    (println "\nDoll Data:")
    ; do sequence loop to print the dolls data
    (doseq [doll selected-dolls] (println (dolls-available doll)))))
