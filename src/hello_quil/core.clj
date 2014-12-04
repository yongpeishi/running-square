(ns hello-quil.core
  (:require [quil.core :as q]
            [quil.middleware :as m]))



(defn setup []
  (q/background 200)
  {:x 10
   :y 10
   :x-dir 1
   :y-dir 0})

(defn draw [state]
  (let [x (:x state)
        y (:y state)]
        (q/rect x y 150 100)))

(defn change-dir [coor]
  (case coor
    [10 10] {:new-x-dir 1 :new-y-dir 0}
    [340 10] {:new-x-dir 0 :new-y-dir 1}
    [340 390] {:new-x-dir -1 :new-y-dir 0}
    [10 390] {:new-x-dir 0 :new-y-dir -1}
    nil))

(defn update [state]
  (let [{:keys [x y x-dir y-dir]} state

        {:keys [new-x-dir new-y-dir]} (or (change-dir [x y])
                                        {:new-x-dir x-dir :new-y-dir y-dir})]
    {:x (+ x new-x-dir)
     :y (+ y new-y-dir)
     :x-dir new-x-dir
     :y-dir new-y-dir}))

(q/defsketch my-square
  :title "my sqr"
  :size [500 500]
  :setup setup
  :draw draw
  :update update
  :middleware [m/fun-mode])

