(ns async-http-demo.http-client
  (:require [clj-http.conn-mgr :as conn]
            [promesa.core :as p]
            [clj-http.client :as client]
            [clj-http.core :as core]))

(def acm (conn/make-reuseable-async-conn-manager {}))

(def ahclient (core/build-async-http-client {} acm))

(defn async-get
  "Async executes the HTTP request corresponding to the given map and returns a promise of the response map."
  [url]
  (p/promise (fn [resolve reject]
               (client/get url {:async? true :connection-manager acm :http-client ahclient :as :json}
                           (fn [response] (resolve (:body response))) reject))))