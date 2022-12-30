(ns {{name}}.diplomat.http-server
  (:require [common-clj.component.telegram.consumer :as component.telegram.consumer]
            [{{name}}.diplomat.telegram.consumer :as diplomat.telegram.consumer]))

(def routes [["/api/handler" :post [(fn telegram-bot-handler
                                      [{update     :json-params
                                        components :components}]
                                      (component.telegram.consumer/consume-update! update diplomat.telegram.consumer/consumers components)
                                      {:status 200})] :route-name :telegram-bot-handler]])