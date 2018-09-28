(ns website.core
    (:require [reagent.core :as reagent :refer [atom]]
              [figwheel.client :as fw]
              [quiescent.core :as q]
              [quiescent.dom :as d]
              [website.components :as web.c]
              [website.utils :as utils]))

(enable-console-print!)

(println "This text is printed from src/website/core.cljs. Go ahead and edit it and see reloading in action.")

;; define your app data so that it doesn't get over-written on reload

(def app-state (atom {:text "hello world!"
                      :this true
                      :about-me utils/about-me}))

;; Render it!
(defn render
  [data]
  (q/render (web.c/app-view data)
            (.getElementById js/document "app")))

(add-watch app-state ::render
    (fn [_ _ _ data] (render data)))

(fw/watch-and-reload :jsload-callback #(swap! app-state identity))

(defonce root (render @app-state))
