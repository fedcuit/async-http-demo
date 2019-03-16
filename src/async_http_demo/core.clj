(ns async-http-demo.core
  (:require [ring.adapter.jetty :refer [run-jetty]]
            [async-http-demo.handler :refer [app]]))

(defn -main
  [& args]
  (run-jetty app {:async? true}))