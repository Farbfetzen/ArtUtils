# ArtUtils
A library containing miscellaneous utilities for creating art with [Processing](https://processing.org).

## Changelog
See file [CHANGELOG.md](CHANGELOG.md).

## How to get the Processing dependency:
Download Processing from processing.org, unpack it, then install it into your local Maven repository.
See [this](http://maven.apache.org/general.html#importing-jars) command for help.
In my case it was 
```
mvn install:install-file \
    -Dfile=<path to core.jar> \
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
