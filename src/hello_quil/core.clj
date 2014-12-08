(ns hello-quil.core
  (:require [quil.core :as q]
            [quil.middleware :as m]))

(defn setup []
  (q/background 200)
  {:x 10
   :y 10
   :x-dir 1
   :y-dir 0
   :dir 1})

(defn draw [state]
  (let [x (:x state)
        y (:y state)]
        (q/rect x y 150 100)))

(defn change-xy [dir coor]
  (if (= dir 1)
    (case coor
      [10 10] {:new-x-dir 1 :new-y-dir 0}
      [340 10] {:new-x-dir 0 :new-y-dir 1}
      [340 390] {:new-x-dir -1 :new-y-dir 0}
      [10 390] {:new-x-dir 0 :new-y-dir -1}
      nil)
    (case coor
      [10 10] {:new-x-dir 0 :new-y-dir 1}
      [340 10] {:new-x-dir -1 :new-y-dir 0}
      [340 390] {:new-x-dir 0 :new-y-dir -1}
      [10 390] {:new-x-dir 1 :new-y-dir 0}
      nil)))


(defn update [state]
  (let [{:keys [x y x-dir y-dir dir]} state
        {:keys [new-x-dir new-y-dir]} (or (change-xy dir [x y])
                                        {:new-x-dir x-dir :new-y-dir y-dir})]
    (q/background 200)
    {:x (+ x new-x-dir)
     :y (+ y new-y-dir)
     :x-dir new-x-dir
     :y-dir new-y-dir
     :dir dir}))

(defn key-pressed [state event]
  (println (q/key-code))
  (if (= 32 (q/key-code))
    (-> state
        (update-in [:x-dir] * -1)
        (update-in [:y-dir] * -1)
        (update-in [:dir] * -1))))

(q/defsketch my-square
  :title "my sqr"
  :size [500 500]
  :setup setup
  :draw draw
  :update update
  :key-pressed key-pressed
  :middleware [m/fun-mode])

