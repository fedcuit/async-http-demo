(ns async-http-demo.github
  (:require
   [async-http-demo.http-client :refer [async-get]]))

(defn followers
  "Returns followers of the given user."
  [username]
  (async-get (format "https://api.github.com/users/%s/followers" username)))

(defn repos
  "Returns repositories of the given user."
  [username]
  (async-get (format "https://api.github.com/users/%s/repos" username)))