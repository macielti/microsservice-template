(ns {{name}}.diplomat.telegram.consumer
  (:require [clostache.parser :as parser]
            [morse.api :as morse-api]))

(defn start
  [{update           :update
    {:keys [config]} :components}]
  (let [token (-> config :telegram :token)
        chat-id (-> update :message :chat :id)
        message-text (parser/render-resource (format "%s/start.mustache"
                                                     (-> config :telegram :message-template-dir)))]
    (morse-api/send-text token chat-id message-text)))

(def consumers
  {:message {:start {:consumer/handler start}}})
