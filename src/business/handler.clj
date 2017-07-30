(ns business.handler
  (:require [compojure.api.sweet :refer [api context resource]]
            [ring.util.http-response :refer [ok]]
            [spec-tools.data-spec :as ds]
            [clojure.spec.alpha :as s]))

(s/def ::business
  (ds/spec
    ::business
    {:id integer?
     :name string?
     :tags #{keyword?}
     :phone_number {:id integer?
                    :phone_number string?}}))

(def app
  (api
    {:coercion :spec
     :swagger
     {:ui "/"
      :spec "/swagger.json"
      :data {:info {:title "Business-api"}}}}

    (context "/business" []
      (resource
        {:post
         {:summary "business-api"
          :parameters {:body-params ::business}
          :responses {200 {:schema ::business}}
          :handler (fn [{body :body-params}]
                     (ok body))}}))))
