(ns async-http-demo.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.util.response :refer [response content-type]]
            [ring.middleware.json :refer [wrap-json-response]]
            [ring.middleware.defaults :refer [wrap-defaults api-defaults]]
            [ring.util.mime-type :refer [default-mime-types]]
            [promesa.core :as p]
            [async-http-demo.github :refer [followers]]
            [cheshire.core :as json]))

(defroutes app-routes
  (GET "/users/:user-id/followers" [user-id] (fn [_ respond raise]
                                               (p/branch (followers user-id)
                                                         (fn [followers]
                                                           (respond (-> (json/generate-string followers)
                                                                        response
                                                                        (content-type (default-mime-types "json")))))
                                                         raise)))
  (route/not-found "Not Found"))

(def app
  (-> app-routes
      (wrap-defaults api-defaults)))
