# Processing-Utilities
A library wit miscellaneous utilities for the generation of (processing)[https://processing.org] sketches.

### How to get the Processing dependency:
Download Processing from processing.org, unpack it, then install it into your local Maven repository.
See [this](http://maven.apache.org/general.html#importing-jars) command for help.
In my case it was 
```
mvn install:install-file \
    -Dfile=/home/sebastian/Processing/processing-4.0b1/core/library/core.jar \
    -DgroupId=org.processing \
    -DartifactId=core \
    -Dversion=4.0b1 \
    -Dpackaging=jar \
    -DgeneratePom=true
```
Then you can add the following dependency to the pom.xml:
```
<dependency>
  <groupId>org.processing</groupId>
  <artifactId>core</artifactId>
  <version>4.0b1</version>
</dependency>
```
