(ns aardvark.4clojure.problems)

(defn rev [coll]
  (loop [coll coll
          out '()]
    (if (nil? (first coll))
      out
      (recur (rest coll) (conj out (first coll))))))

(defn fibs [x]
  (drop 1
  (loop [fibs [0 1]
         i (- x 1)]
    (if (< x 3)
      fibs
      (if (= i 0)
        fibs
        (recur
          (conj fibs (reduce + (vector
                                 (first (reverse fibs))
                                 (second (reverse fibs)))))
          (dec i)))))))
;;27
#(= (reduce str (reverse %)) (reduce str %))
;;28
;;29
(fn [x] (apply str (filter #(Character/isUpperCase %) x)))
;;30
(map first (partition-by identity "Leeeeeerrrrrooooy"))
;;32
;; mapcat #(list % %)
((fn double-element [coll times]
   (when-let [[x & xs] (not-empty coll)]
     (conj (double-element xs) (repeat times x) ))) [[1 2] [3 4]] 2)
;;33
;; (fn [x t] (mapcat (partial repeat t) x))
((fn double-element
   [coll times]
   (when-let [[x & xs] (not-empty coll)]
     (concat (repeat times x)
             (double-element xs times)))) [:a :b] 4)
;;34
;; implement range
(fn my-range [f t]
  (loop [f f
         t t
         out []]
    (if (= f t)
      out
      (recur (inc f) t (conj out f)))))
;;38
;; maximum
(last (sort coll))
(fn [& coll]
  (reduce (fn [a b] (if (> a b) a b)) coll)
  )
(fn my-max [& args]
   (let [local-max (first args)
         rest (rest args)]
     (if (empty? rest)
       local-max
       (if (> local-max next)
         (recur local-max rest)
         (recur (first rest) rest)))))
;;39
;;interleave two seqs
#(mapcat (list %1 %2))
(fn [x y] (flatten (map #(list %1 %2) x y)))

;;40
;;interpose a seq
(fn interp [x y] (drop-last (mapcat #(list %2 %1) (repeat (count y) x) y)))
;;41
;;drop every nth
((fn drop-nth [coll nth]
   (if (>= (count coll) nth)
     (concat (take (dec nth) coll)
             (drop-nth (drop nth coll) nth))
     coll)) [:a :b :c :d :e :f] 2)
;;42
;;factorials
((fn [x]
    (reduce *
            (range 1 (inc x)))) 5)
;;43
;;reverse interleave
((fn rev-int
   [coll n]
   (let [xs (split-at n coll)
         x (first xs)
         y (rest xs)]
     ()
     )))
#(apply map list (partition %2 %1))
;;44
;;rotate sequence
(reverse (split-at 2 [1 2 3 4 5])) ;;=> [(1 2) (3 4 5)]
(split-at (- 5 2)  [1 2 3 4 5]) ;;=> [(1 2 3) (4 5)]


(flatten (reverse (split-at 2 [1 2 3 4 5])))
(flatten (reverse (split-at 1 '(:a :b :c))))
((fn [n coll]
   (let [cnt (count coll)
         n (mod n cnt)
         f (comp flatten reverse split-at)]
     (if (< n 0 )
       (f (+ cnt n) coll)
       (f n coll)))) 2  [1 2 3 4 5])
((comp flatten reverse) [1 2 3 4 5])
(take 5 (iterate #(+ 3 %) 1))
;;46
;;flipping out
(> 7 8)
(((fn [f]
    #(f %2 %1)) >) 7 8)
;;47
;;contain yourself
(contains? #{4 5 6} 4)
(some #{2 7 6} [5 6 7 8])
(whe)
;;49
;;split sequence
((fn [n coll]
   [(take n coll) (drop n coll)])3 [1 2 3 4 5 6])
;;50
;;split by type
((fn [coll]
   (partition-by #(type %)
                 (sort
                  (comparator
                   (fn [x y]
                     (let [x (.length (.toString (type x)))
                           y (.length (.toString (type y)))]
                       (< x y ))))
                  coll))
   ) [1 :a 2 :b 3 :c])
