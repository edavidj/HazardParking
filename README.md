# HazardParking
Create an interactive heatmap of parking tickets in a region. Project designed for the final project in McMaster CS second year course 2XB3 Cs Practice & Exp:Theory To Practice. Currently only covers the region of Washington DC, as that's all our dataset had data on; as per project guidelines we were restricted in data sources. That said we have no plans to expand that scope.

## Set-up working environment
#### Using *Intellij IDEA*
1. git clone https://github.com/edavidj/hazardparking.git
2. When Intellij starts up on the *Welcome Screen* select import project.
3. In the following dialogue it will say select file or directory to import, select /src/build.gradle
4. Once *Intellij* finishes building your work environment you should be able to run it by running the Application.java file.
5. Go to localhost:8080 in browser and it should return a massive array of Entry objects.
#### Using *Eclipse*
1. git clone https://github.com/edavidj/hazardparking.git
2. From eclipse go to Import from the File menu
3. In the following context screen navigate to Gradle/Existing Gradle Project
4. Select the cloned "hazardparking" folder as your root directory
5. Keep selecting next until eclipse begins installing everything.
6. Run Application.java (it might suggest using gradle to run you dont need to) and
navigate to localhost:8080 in your browser.
## Reference
We used
[Spring's "*Working a Getting Started guide with IntelliJ IDEA*"](https://spring.io/guides/gs/intellij-idea/) in order to bootstrap a functional Spring RESTful Web App environment that we could work with, then removed their class files for our own and customized the environment while removing unneeded files.
