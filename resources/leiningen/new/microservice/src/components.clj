(ns {{name}}.components
  (:require [common-clj.integrant-components.config :as component.config]
            [common-clj.integrant-components.routes :as component.routes]
            [http-client-component.with-httpkit-client :as component.http-client]
            [telegrama.component :as component.telegrama]
            [datalevin.component :as component.datalevin]
            [service.component :as component.service]
            [{{name}}.db.datalevin.config :as datalevin.config]
            [{{name}}.diplomat.http-server :as diplomat.http-server]
            [{{name}}.diplomat.telegram.consumer :as diplomat.telegram.consumer]
            [integrant.core :as ig]
            [taoensso.timbre :as timbre]
            [taoensso.timbre.tools.logging])
  (:gen-class))

(taoensso.timbre.tools.logging/use-timbre)

(def components
  {:config      (ig/ref ::component.config/config)
   :datalevin   (ig/ref ::component.datalevin/datalevin)
   :http-client (ig/ref ::component.http-client/http-client)})

(def arranjo
  {::component.config/config       {:path "resources/config.edn"
                                    :env  :prod}
   ::component.datalevin/datalevin {:schema     datalevin.config/schema
                                    :components (select-keys components [:config])}
   ::component.http-client/http-client {:components {:config     (ig/ref ::component.config/config)}}
   ::component.telegrama/consumer  {:settings   diplomat.telegram.consumer/settings
                                    :components components}
   ::component.routes/routes       {:routes diplomat.http-server/routes}
   ::component.service/service     {:components (merge components
                                                       {:routes (ig/ref ::component.routes/routes)})}})

(defn start-system! []
  (timbre/set-min-level! :debug)
  (ig/init arranjo))

(def -main start-system!)
