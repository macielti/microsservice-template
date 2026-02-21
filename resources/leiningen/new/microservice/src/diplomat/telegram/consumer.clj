(ns {{name}}.diplomat.telegram.consumer
  (:require [clojure.tools.logging :as log]))

(defn hello-word-handler
  [context]
  (log/info :telegram-bot-update-consumed :context context))

(def settings
  {:bot-command {:hello-world {:handler hello-word-handler}}})

