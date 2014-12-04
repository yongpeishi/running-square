(ns hello-quil.core
  (:require [quil.core :as q]
            [quil.middleware :as m]))

;; (defn setup []
;;   ; Set frame rate to 30 frames per second.
;;   (q/frame-rate 30)
;;   ; Set color mode to HSB (HSV) instead of default RGB.
;;   (q/color-mode :hsb)
;;   ; setup function returns initial state. It contains
;;   ; circle color and position.
;;   {:color 0
;;    :angle 0})

;; (defn update [state]
;;   ; Update sketch state by changing circle color and position.
;;   {:color (mod (+ (:color state) 0.7) 255)
;;    :angle (+ (:angle state) 0.1)})

;; (defn draw [state]
;;   ; Clear the sketch by filling it with light-grey color.
;;   (q/background 240)
;;   ; Set circle color.
;;   (q/fill (:color state) 255 255)
;;   ; Calculate x and y coordinates of the circle.
;;   (let [angle (:angle state)
;;         x (* 150 (q/cos angle))
;;         y (* 150 (q/sin angle))]
;;     ; Move origin point to the center of the sketch.
;;     (q/with-translation [(/ (q/width) 2)
;;                          (/ (q/height) 2)]
;;       ; Draw the circle.
;;       (q/ellipse x y 100 100))))

(def frame-height 500)
(def frame-width 500)

(def frame-margin 10)

(def box-height 100)
(def box-width 150)


(defn setup []
  (q/background 200)
  {:x frame-margin
   :x-dir 1
   :y-dir 0
   :y frame-margin})

(defn draw [state]
  (let [x (:x state)
        y (:y state)]
        (q/rect x y 150 100)))


(defn update [state]
  (let [{:keys [x y x-dir y-dir]} state
        [new-x-dir new-y-dir] (if (and (> x (- frame-width box-width frame-margin))
                                        (= y frame-margin))
                                  [0 1]
                                  [x-dir y-dir])]
    {:x (+ x x-dir)
     :x-dir new-x-dir
     :y (+ y y-dir)
     :y-dir new-y-dir}))


;; (defn update [state]
;;   (let [{:keys [x y x-dir y-dir
;; (defn update [state]
;;   (let [{:keys [x y x-dir y-dir]} state
;;         new-x-dir
;;         new-x-dir (if (< x 10) 1 new-x-dir)
;;         ]

;;       {:x (+ x x-dir)
;;        :x-dir new-x-dir
;;        :y y}))


(q/defsketch my-square
  :size [500 500]
  :setup setup
  :draw draw
  :update update
  :middleware [m/fun-mode])

