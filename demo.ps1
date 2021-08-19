# This is the Windows equivalent of demo.sh, run it from the PowerShell window 

# This requires that you first build the project with the usual 'mvn clean package'
# Once you've the project's .jar in place, the commands below launches it as a web server
#  


# Go to and save my dir.
$WORKDIR = split-path -parent $MyInvocation.MyCommand.Definition
Push-Location $WORKDIR
$MYDIR = Get-Location
Pop-Location

# This is passed to the API and override the localhost default
# user/password are irrelevant in this case, since authentication is disabled
$env:NEO_URL="bolt://knetminer-neo4j.cyverseuk.org:7688" # Arabidopsis dataset

Start-Process -FilePath java -NoNewWindow -Wait -ArgumentList "-cp target/kgraph-usecases-0.0.1-SNAPSHOT.jar org.springframework.boot.loader.JarLauncher"
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

# Remove this at the end, it interferes with the Maven build.
$env:NEO_URL=$null
