(defproject {{name}} "0.1.0-SNAPSHOT"

            :description "FIXME: write description"

            :url "http://example.com/FIXME"

            :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
                      :url  "https://www.eclipse.org/legal/epl-2.0/"}

            :plugins [[lein-cloverage "1.2.3"]]

            :dependencies [[org.clojure/clojure "1.11.1"]
                           [net.clojars.macielti/common-clj "19.27.33"]
                           [hashp "0.2.2"]
                           [prismatic/schema "1.4.1"]
                           [org.clojure/data.csv "1.0.1"]
                           [morse "0.4.3"]
                           [incanter "1.9.3"]]

            :injections [(require 'hashp.core)]

            :resource-paths ["resources" "test/resources/"]

            :repl-options {:init-ns {{name}}.components}

            :main {{name}}.components/start-system!

            :test-paths ["test/unit" "test/integration" "test/helpers"])