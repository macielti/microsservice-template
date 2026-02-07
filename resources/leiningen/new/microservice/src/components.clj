(ns {{name}}.components
  (:require [common-clj.integrant-components.config :as component.config]
            [integrant.core :as ig]
            [taoensso.timbre :as timbre]
            [taoensso.timbre.tools.logging])
  (:gen-class))

(taoensso.timbre.tools.logging/use-timbre)

(def components
  {:config      (ig/ref ::component.config/config)})

(def arranjo
  {::component.config/config  {:path "resources/config.edn"
                               :env  :prod}})

(defn start-system! []
  (timbre/set-min-level! :debug)
  (ig/init arranjo))

(def -main start-system!)
