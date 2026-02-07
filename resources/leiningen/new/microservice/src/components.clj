(ns {{name}}.components
  (:require [common-clj.component.config :as component.config]
            [common-clj.component.datomic :as component.datomic]
            [com.stuartsierra.component :as component]
            [common-clj.component.telegram.consumer :as component.telegram.consumer]
            [common-clj.component.service :as component.service]
            [common-clj.component.http-client :as component.http-client]
            [common-clj.component.routes :as component.routes]
            [{{name}}.db.datomic.config :as datomic.config]
            [{{name}}.diplomat.http-server :as diplomat.http-server]))

(def system-prod
  (component/system-map
    :config (component.config/new-config "resources/config.edn" :prod :edn)
    :telegram-webhook-consumer (component/using (component.telegram.consumer/new-telegram-webhook-consumer) [:config :datomic])
    :datomic (component/using (component.datomic/new-datomic datomic.config/schemas) [:config])
    :http-client (component/using (component.http-client/new-http-client) [:config])
    :routes (component/using (component.routes/new-routes diplomat.http-server/routes) [:datomic :config])
    :service (component/using (component.service/new-service) [:routes :config :datomic :http-client])))

(defn start-system! []
  (component/start system-prod))

(def system-test
  (component/system-map
    :config (component.config/new-config "resources/config.example.edn" :test :edn)
    :datomic (component/using (component.datomic/new-datomic datomic.config/schemas) [:config])
    :http-client (component/using (component.http-client/new-http-client) [:config])
    :routes (component/using (component.routes/new-routes diplomat.http-server/routes) [:datomic :config])
    :service (component/using (component.service/new-service) [:routes :config :datomic :http-client])))
