# NJIT-DSA-2024 Tools

This readme instructs you what tools are needed and where to get them.

## Tools

The tools that are used in this course and you need to install, are:

* [git](https://git-scm.com) for version control and delivering work to teachers.
* [JDK version 18](https://jdk.java.net/18/) -- install the version 18 (or newer) if you have an older JDK.
* [Maven](http://maven.apache.org) for building and running tests. Install the latest version.
* IDE for coding and debugging -- teachers use [Visual Studio Code](https://code.visualstudio.com), and recommend that for you too.
* [Java extensions](https://code.visualstudio.com/docs/java/java-tutorial) to the VS Code. Useful extensions are Extension Pack for Java, Language support for Java by Red Hat and Maven for Java extensions for VS Code. The Sonarlint extension helps you in creating better quality code by providing warnings of things that are possible issues and/or bad coding style, so consider installing that too. The extensions are installed from the VS Code Extensions section.

You can check the versions of tools you may have installed or now install, using the terminal. Here is my installation:

```console
> git --version
git version 2.32.1 (Apple Git-133)

> mvn --version
Maven home: /opt/apache-maven-3.6.3
Java version: 18.0.1.1, vendor: Homebrew, runtime: /opt/homebrew/Cellar/openjdk/18.0.1.1/libexec/openjdk.jdk/Contents/Home
Default locale: fi_FI, platform encoding: UTF-8
OS name: "mac os x", version: "12.4", arch: "aarch64", family: "mac"

> java --version
openjdk 18.0.1.1 2022-04-22
OpenJDK Runtime Environment Homebrew (build 18.0.1.1+0)
OpenJDK 64-Bit Server VM Homebrew (build 18.0.1.1+0, mixed mode, sharing)

> javac --version
javac 18.0.1.1
```
VS Code version can be checked from the VSC menus. Installed VSC extensions can be seen from the VSC Extensions tool.

**Make sure** JDK `bin` and Maven `bin` directories are on your **PATH** environment variable, and that **JAVA_HOME** environment variable points to the JDK home directory! 

Each exercise contains unit tests. For unit testing, JUnit Jupiter version 5.7 or newer is used. These component dependencies (JUnit Jupiter) are installed by Maven / Visual Studio Code *automatically*. You do not have to try to install these yourself.

Optionally, it is also recommended that you install a proper terminal app, especially on Windows. Though you can work with Visual Studio Code and the terminal in it, having a good terminal for command line based work is better than working with the Windows Command Prompt. 

One option is Microsoft's [Windows Terminal](https://github.com/microsoft/terminal). [Other terminal apps](https://dev.to/adnanmostafa/the-best-free-standalone-terminals-for-windows-2019-kmj) than the basic Command Prompt have been designed for programmers and are usually much better in e.g. supporting Unicode text. For macOS users, [iTerm2](https://iterm2.com) is a good option to native macOS Terminal app.

After installing the tools, continue reading how to set up the workspace you will be working with in the course: [SETUP.md](SETUP.md).

## About

* Course material for Tietorakenteet ja algoritmit | Data structures and algorithms 2022.
* Study Program for Information Processing Science, Department of Information Technology and Electical Engineering, University of Oulu.
* Antti Juustila, INTERACT Research Group.
* For NJIT DSA implementation:
* Pertti Karhapää, M3S Research Unit
