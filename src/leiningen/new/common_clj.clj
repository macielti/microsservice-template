(ns leiningen.new.common-clj
  (:require [leiningen.new.templates :as tmpl]
            [leiningen.core.main :as main]))

(def render (tmpl/renderer "common_clj"))

(defn common-clj
  "FIXME: write documentation"
  [name]
  (let [data {:name      name
              :sanitized (tmpl/name-to-path name)}]
    (main/info "Generating fresh 'lein new' net.clojars.macielti/common-clj project.")
    (main/info "Using '0.0.0.0' as host and '8000' as port, you can change it by editing 'resources/config.example.edn'")
    (tmpl/->files data
                  ["src/{{sanitized}}/components.clj" (render "src/components.clj" data)]
                  ["src/{{sanitized}}/db/datomic/config.clj" (render "src/db/datomic/config.clj" data)]
                  ["src/{{sanitized}}/diplomat/http_server.clj" (render "src/diplomat/http_server.clj" data)]
                  ["resources/config.example.edn" (render "resources/config.example.edn" data)]
                  ["resources/templates/start.mustache" (render "resources/templates/start.mustache" data)]
                  ["project.clj" (render "project.clj" data)])))
