(ns async-http-demo.github
  (:require [clj-http.client :as client]
            [promesa.core :as p]))

(defn followers
  "Returns the followers of the given user."
  [user-id]
  (p/promise (fn [resolve reject]
               (client/get (format "https://api.github.com/users/%s/followers" user-id) {:async? true :as :json}
                           (fn [response] (resolve (:body response))) reject))))