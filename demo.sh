#!/usr/bin/env bash

# This requires that you first build the project with the usual 'mvn clean package'
# Once you've the project's .jar in place, the commands below launches it as a web server
#  

cd `dirname "$0"`

# This is passed to the API and override the localhost default
# user/password are irrelevant in this case, since authentication is disabled
# 
export NEO_URL="bolt://knetminer-neo4j.cyverseuk.org:7688" # Arabidopsis dataset

java -jar target/kgraph-usecases-0.0.1-SNAPSHOT.jar

# After this, you should be able to go to: 
# Foo static page (index.html): http://localhost:8080
# Simple graph test: http://localhost:8080/test-graph.html 
# The metagraph visualisation: http://localhost:8080/metagraph.html 
#
# It might take a while before seeing anything, give a look to the terminal logs
#
# The files that are accessible this way are at src/main/resources/static
# They're automatically published via web by Spring Boot:
#   https://spring.io/blog/2013/12/19/serving-static-web-content-with-spring-boot
#
