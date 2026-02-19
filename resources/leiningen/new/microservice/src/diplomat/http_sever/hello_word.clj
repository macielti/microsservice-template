(ns {{name}}.diplomat.http-sever.hello-word
  (:require [schema.core :as s]))

(s/defn hello-world
        [{{:keys [datalevin config]}  :components}]
        {:status 200
         :body   {:hello :world}})