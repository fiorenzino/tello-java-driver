# Tello Java Driver
> Java driver for the DJI Ryze Tello drone.

[![Build Status](https://travis-ci.org/bdg91/tello-java-driver.svg?branch=develop)](https://travis-ci.org/bdg91/tello-java-driver)
[![codecov](https://codecov.io/gh/bdg91/tello-java-driver/branch/develop/graph/badge.svg)](https://codecov.io/gh/bdg91/tello-java-driver)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/9b6da0ce51cf495a962415570ecab2fb)](https://www.codacy.com/app/bdg91/tello-java-driver?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=bdg91/tello-java-driver&amp;utm_campaign=Badge_Grade)
[![](https://jitpack.io/v/bdg91/tello-java-driver.svg)](https://jitpack.io/#bdg91/tello-java-driver)
[![License](https://img.shields.io/badge/doc-javadoc-blue.svg)](https://jitpack.io/com/github/bdg91/tello-java-driver/latest/javadoc/)
[![License](http://img.shields.io/:license-mit-blue.svg)](https://github.com/bdg91/tello-java-driver/blob/develop/LICENSE)

---

## How to use

#### Maven
```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```

```xml
<dependency>
    <groupId>com.github.bdg91</groupId>
    <artifactId>tello-java-driver</artifactId>
    <version>v1.0.1</version>
</dependency>
```

#### Gradle
```groovy
allprojects {
    repositories {
        ...
	maven { url 'https://jitpack.io' }
    }
}
```

```groovy
dependencies {
    implementation 'com.github.bdg91:tello-java-driver:v1.0.1'
}
```

#### Implementation example

```java
TelloClient client = TelloClient.getInstance();

Command commandCommand = new CommandCommand(client);
Command takeOffCommand = new TakeOffCommand(client);
Command cwCommand = new CwCommand(client, 360);
Command landCommand = new LandCommand(client);

List<Command> flightPlan = Arrays.asList(commandCommand, takeOffCommand, cwCommand, landCommand);

flightPlan.forEach(command -> {
    try {
        command.execute();
        Thread.sleep(5000);
    } catch (Exception exception) {
        // Exception handling
    }
});
```

---

## Contributing
1. Fork the project
2. Create a feature branch (`git checkout -b feature/fooBar`)
3. Commit and push your changes
4. Create a new pull request against the develop branch

Pull requests should be made against the develop (default) branch and include relevant tests.

---

## Build
Java 12+ is required to build and compile the source. To build and test the driver:
```sh
$ git clone https://github.com/bdg91/tello-java-driver.git
$ cd tello-java-driver
$ mvn test
```

---

## Versioning
Major increments (such as 2.x -> 3.x) will occur when breaking changes are being made to the public API.

Minor 3.x increments (such as 3.1 -> 3.2) will occur when non-trivial new functionality is added or significant 
enhancements are made.

Patch 3.x.y increments (such as 3.0.0 -> 3.0.1, 3.1.1 -> 3.1.2) will occur for bug fixes only.

For more information see: [Semantic Versioning](https://semver.org/)

---
