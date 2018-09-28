(ns website.components
  (:require [quiescent.core :refer [defcomponent]]
            [quiescent.dom :as d]))

(defcomponent site-title
  "Used to display the largest available heading"
  [title]
  (d/div {:className "site-title-div"}
         (d/h2 {:className "site-title"}
               title)))

(defcomponent header
  "Displays a sub title which represents a section of the site."
  [text]
  (d/h4 {:className "header-component"}
        text))

(defcomponent text-section-body
  "Accepts a list or vector of strings and places each string
  in their own paragraph."
  [content]
  (->> content
       (map (partial d/p {})) ;; place each element in it's own paragraph
       (apply d/div {})))

(defcomponent section-layout
  [{:keys [title content section-type]}]
  (d/div {:className "section-body"}
         (header title)
         ((case section-type
            :text text-section-body)
          content)))

(defcomponent vertical-categories
  [db]
  (d/span {}
          (site-title "Matthew Fors") ;; sticky at top?
          (header "Menu") ;; navigation
          (section-layout {:title "About Me"
                           :content (:about-me db)
                           :section-type :text})
          (header "Content") ;; links to my stuff
          (header "Node Creator") ;; little css/cljs demo thing
          (header "CLJS Repl")
          (header "Gists")))

;; Top level div/body of page.
(defcomponent app-view
  [db]
  (vertical-categories db)) ;; i want a clojurescript repl embedded
