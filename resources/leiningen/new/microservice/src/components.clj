(ns {{name}}.components
  (:require [common-clj.integrant-components.config :as component.config]
            [datalevin-component.core :as component.datalevin]
            [{{name}}.db.datalevin.config :as datalevin.config]
            [integrant.core :as ig]
            [taoensso.timbre :as timbre]
            [taoensso.timbre.tools.logging])
  (:gen-class))

(taoensso.timbre.tools.logging/use-timbre)

(def components
  {:config      (ig/ref ::component.config/config)
   :datalevin   (ig/ref ::component.datalevin/datalevin)})

(def arranjo
  {::component.config/config      {:path "resources/config.edn"
                                   :env  :prod}
   ::component.datalevin/datalevin {:schema     datalevin.config/schema
                                    :components (select-keys components [:config])}})

(defn start-system! []
  (timbre/set-min-level! :debug)
  (ig/init arranjo))

(def -main start-system!)
