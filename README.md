# docker-license

Step 1: Build the optimization-lib to be able to import in standalone and web projects and then build standalone and web projects. : mvn clean install

Step 2: To test the library use in standalone mode. Assumptions the library is commited with a configuration of 2 parallel executions of solve method. In order to test we have get more than 2 connections to the library

        i.   Open 4 terminals or command prompts, navitgate to the root the standalone project where the class files are there
        ii.  cd /Users/sandeep/Documents/TechWork/IPG/optimization-solver-standalone/target/classes
        iii. set the classpath with command -- export CLASSPATH=.:/Users/sandeep/Documents/TechWork/IPG/optimization-lib/target/classes/
        iv.  execute the command - Java com.ipg.test.standalone.StandaloneApp
        
This should start the long running process in the 2 consoles while the other waits untill one of them is complete.


Step 3: To test the a Spring Boot Application is developed which exposes a rest endpoint
        
         i.   Open a web browser and hit http://localhost:8080/optimization-web/testing/1 - this should give a response as "Job "+id+" Submitted Successfully"
         ii.  Hit the URL http://localhost:8080/optimization-web/testing/2 just changing the url path param 2 and 3 and 4.
         iii. Now we should see that we will be getting the response for the first 2 URL only, rest of the urls will wait for the others to complete and then renders the response.
