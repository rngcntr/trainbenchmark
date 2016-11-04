# Train Benchmark

[![Build Status](https://travis-ci.org/FTSRG/trainbenchmark.svg?branch=master)](https://travis-ci.org/FTSRG/trainbenchmark)

**Summary.** :information_source: The Train Benchmark is a framework for measuring the performance of continuous model transformations, with a particular emphasis on the performance of incremental query reevaluation. The benchmark is actively developed since 2011.

**Technologies.** The framework is implemented in Java 8 (for the main components) and [Groovy](http://www.groovy-lang.org/) (for the scripts). The visualization is handled by [R scripts](https://www.r-project.org/). Both the build and the benchmark process in governed by [Gradle](https://gradle.org/).

**Contributions welcome.** If you would like to implement the benchmark on your tool, do not hesitate to [get it contact](https://github.com/szarnyasg)!

**Warning.** :warning: The Train Benchmark is designed to run in an isolated server environment, e.g. virtual machines in the cloud. Some implementations may shut down or delete existing databases, so only run it on your developer workstation if you understand the consequences.

**Note.** :notebook_with_decorative_cover: The Train Benchmark has a fork for the [2015 Transformation Tool Contest](https://github.com/FTSRG/trainbenchmark-ttc), primarily targeting EMF tools. _That fork is no longer maintained._ You should use this repository, containing the full, cross-technology Train Benchmark (also supporting RDF, SQL and property graph databases).

**Details.** For theoretical and implementation details, check out the following documents:
* [Train Benchmark documentation](http://docs.inf.mit.bme.hu/trainbenchmark)
* [Related publications](http://incquery.net/publications/trainbenchmark)

## Getting started

The framework provides a set of scripts for building the projects, generating the instance models and running the benchmark.

### Installation guide

The benchmark requires a 64-bit operating system. We recommend Ubuntu-based Linux systems.

### Setup

* Initialization
    * [`install-jdk.sh`](scripts/install-jdk.sh): installs [Oracle JDK 8](https://github.com/FTSRG/cheat-sheets/wiki/Linux-packages#oracle-jdk)
    * [`install-gradle.sh`](scripts/install-maven.sh): installs Gradle.
    * [`gradle initScript`](trainbenchmark-scripts/build.gradle): intializes the Groovy scripts for the [generate](trainbenchmark-scripts/src-template/GeneratorScript.groovy) and thet [benchmark](trainbenchmark-scripts/src-template/BenchmarkScript.groovy) goals.

Provided that you start with a fresh Ubuntu server installation, you can run the provided install scripts like this:

```bash
scripts/install-jdk.sh && \
scripts/install-gradle.sh && \
gradle initScripts
```

#### Optional dependencies

Some tools require dependencies, e.g. installing a database manager or adding artifacts to your local Maven repository

* [MySQL](hu.bme.mit.trainbenchmark.benchmark.mysql): install with `sudo apt-get install -y mysql-server` and set the root password to empty.
* [SQLite](hu.bme.mit.trainbenchmark.benchmark.sqlite): install with `sudo apt-get install -y sqlite3`.

### Usage

The benchmark configuration is defined in the `trainbenchmark-scripts/src/BenchmarkScript.groovy` file.

* Use `gradle shadowJar generate benchmark` to run the benchmark and generate the plots.
* `plot`: generate the plots.
* `page`: run a Jetty server showing the plots.
* `cleanresults`: remove the previous benchmark results

### Projects

* The **generator projects** are responsible for generating instance models. For **each format**, there is a separate generator project.
* The **benchmark projects** are responsible for running the benchmarks. For each tool, there is a separate benchmark project.

Currently, the following formats and tools are supported.

* **EMF:** Drools, Eclipse OCL, EMF API, VIATRA
* **Property graph:** Neo4j, Tinkergraph
* **RDF:** Blazegraph, Jena, Sesame 2.0, RDF4J
* **SQL:** MySQL, SQLite

### Importing to Eclipse

The projects are developed and tested with **Eclipse Mars**.

To develop the Train Benchmark, you a Gradle Eclipse plugin from the **Eclipse Marketplace**, e.g. the **Buildship: Eclipse Plug-ins for Gradle**. Also install the Eclipse Groovy tooling from <https://github.com/groovy/groovy-eclipse/wiki>.

To import the projects, choose **Import...** | **Gradle Project**, specify the root directory as the repository directory and import them with the default **Gradle distribution** (**Gradle wrapper (recommended)**). If Eclipse prompts you if you would like to overwrite existing Eclipse project descriptors, choose **Keep**.

### Naming conventions

**Note.** :notebook_with_decorative_cover: To avoid confusion between the different implementations, we decided to naming similar to the [Smurf Naming convention](http://blog.codinghorror.com/new-programming-jargon/) (see #21). For example, the classes in the EMF API implementation are named `EmfApiQueryPosLength`, `EmfApiQueryRouteSensor`, etc., while the classes in the VIATRA implementation are named `ViatraQueryPosLength`, `ViatraQueryRouteSensor`, etc. We found that relying on the package names to differentiate class names is error-prone and should be avoided.

## Instance models

The Train Benchmark provides two sorts of instance models:

* **Minimal models**, used only for testing
* **Scalable models**, used both for testing and benchmarking

The **minimal models** contain only a few (8-10) model elements to provide simple models for development and testing.

The **scalable models** are generated for each scenario in sizes denoted by the powers of two, e.g. `railway-repair-1`, `railway-repair-2`, `railway-repair-4`, etc.

