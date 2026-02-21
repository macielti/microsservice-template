(ns {{name}}.diplomat.http-server
        (:require [schema.core :as s]))

{{=<% %>=}}
(s/defn hello-world
        [{{:keys [datalevin config]}  :components}]
        {:status 200
         :body   {:hello :world}})
<%={{ }}=%>

(def routes [["/api/hello-world" :post [hello-world]
              :route-name :hello-world]])