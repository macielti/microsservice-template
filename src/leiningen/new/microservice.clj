(ns leiningen.new.microservice
  (:require [leiningen.new.templates :as tmpl]
            [leiningen.core.main :as main]))

(def render (tmpl/renderer "microservice"))

(defn microservice
  "FIXME: write documentation"
  [& args]
  (let [name (first args)
        data {:name      name
              :sanitized (tmpl/name-to-path name)}]
    (main/info "Generating fresh 'lein new' net.clojars.macielti/microservice project.")
    (main/info "Using '0.0.0.0' as host and '8000' as port, you can change it by editing 'resources/config.edn'")
    (tmpl/->files data
                  ["src/{{sanitized}}/components.clj" (render "src/components.clj" data)]
                  ["src/{{sanitized}}/db/datalevin/config.clj" (render "src/db/datalevin/config.clj" data)]
                  ["src/{{sanitized}}/diplomat/http_server/hello_world.clj" (render "src/diplomat/http_server/hello_world.clj" data)]
                  ["src/{{sanitized}}/diplomat/http_server.clj" (render "src/diplomat/http_server.clj" data)]
                  ["resources/config.edn" (render "resources/config.edn" data)]
                  ["Dockerfile" (render "Dockerfile" data)]
                  [".dockerignore" (render "dockerignore" data)]
                  [".gitignore" (render "gitignore" data)]
                  ["project.clj" (render "project.clj" data)]
                  ["reflect-config.json" (render "reflect-config.json" data)]
                  ["README.md" (render "README.md" data)])))
