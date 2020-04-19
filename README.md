# Space Invaders
<iframe width="1280" height="720" src="https://www.youtube.com/embed/VgFt-7SPotA" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
<img src="https://images-na.ssl-images-amazon.com/images/I/5102qTz1LPL._AC_SY741_.jpg" align="right" width="180" height="269">
This JavaFX project aims to recreate the famous classic shoot 'em up game <b>Space Invaders</b> (1978).
Some game mechanics have been adjusted and the game has been introduced with some new features:
<ul>
<li>The player is now able to advance through levels, each increasing in difficulty and changing in appearance.</li>
<li>There now is a final boss to put the player(s) to the test in a nerve wrecking battle.</li>
<li>The player can now enjoy the game with one other person thanks to a local multiplayer system</li>
</ul>

# Manual
### Installation
To open this project in IntelliJ, just import the pom.xml file. The pom.xml file will have all the data needed
for IntelliJ to import the project successfully.

For Eclipse users:
- choose import project
- select maven project 
    - existing maven project
- select the root directory of the project 
- press finish

### Running the game
If you have followed the installation steps correctly, you should be able to run the Runner.java file and the game will start. 

### pom.xml
The pom.xml file is a file used to compile and build your java application with maven. In this file are dependencies defined 
that are used in your application like the GameEngine. If you want to add more dependencies to your project, this is the file in which you 
should define them. Shown below is a example of a dependency code block for your pom file.  

```
<dependency>
    <groupId>depGroupId</groupId>
    <artifactId>artifactId</artifactId>
    <version>versionNumber</version>
</dependency>
```

If you remove lines from the pom.xml, be sure you know what you are doing, because you might not be able to deploy your project successfully.

### .gitlab-ci.yml
The gitlab-ci.yml file is used to deploy your application to the gitlab server. On this server, your game will be build and compiled so it can be deployed
to the gameslist server. This file will generate a .jar file of your game that can be downloaded by other students to play.
Do not change anything in this file because it will break your ci/cd pipelines.   
