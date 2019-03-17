(ns async-http-demo.github
  (:require [clj-http.client :as client]
            [promesa.core :as p]))

(defn- async-get
  "Async executes the HTTP request corresponding to the given map and returns a promise of the response map."
  [url]
  (p/promise (fn [resolve reject]
               (client/get url {:async? true :as :json}
                           (fn [response] (resolve (:body response))) reject))))

(defn followers
  "Returns followers of the given user."
  [username]
  (async-get (format "https://api.github.com/users/%s/followers" username)))

(defn repos
  "Returns repositories of the given user."
  [username]
  (async-get (format "https://api.github.com/users/%s/repos" username)))