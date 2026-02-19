(ns {{name}}.diplomat.http-server
  (:require [{{name}}.diplomat.http-server.hello-world :as diplomat.http-server.hello-world]))

(def routes [["/api/hello-world" :post [diplomat.http-server.hello-world/hello-world]
              :route-name :hello-world]])