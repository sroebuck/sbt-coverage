sbt-coverage code coverage processor for sbt
============================================

Version: 0.1-SNAPSHOT

This code is currently under development.  It produces code coverage reports
of projects in sbt using the [undercover](http://code.google.com/p/undercover/)
code coverage tool.

Prerequisites
-------------

The current version of this sbt processor relies on test callback classloader
information provided in version `0.7.5.RC0` of sbt.  From all the testing I have
carried out sbt `0.7.5.RC0` is as stable as the current stable release `0.7.4` so
you should be able to upgrade the version for each sbt project by entering:

    > set sbt.version 0.7.5.RC0
    > reload

Also note that the current version of this sbt processor is only compiled for
Scala 2.7.7.  If you have set the `default.scala.version` in sbt to `2.8.1` or
any other version, then the sbt processor will fail to load as it will fail to
find a build of the current version.  However, current sbt runs in version 2.7.7
by default even when building code for 2.8.1 so this is unlikely to be a problem.

Building and installing
-----------------------

Until I have published this to a public maven repository it is currently
necessary to download the source and build it locally.  Having downloaded
the source you should be able to enter the following commands from within
sbt to install the processor:

    > publish-local
    > *undercoverRepo at http://undercover.googlecode.com/svn/maven/repository/
    > *coverage is com.proinnovate sbt-coverage 0.1-SNAPSHOT

The first line should compile the processor and publish it to your local ivy
repository.  The second line defines the repository for getting the undercover
dependency.  The final line defines the processor which is read from the local
repository.

If you are making changes to the code and wish to reload the processor you
need to remove the existing one first with:

    > *remove coverage

You may also need to exit sbt and start it again for the new coverage tool
to work properly.

Using the processor
-------------------

First install the processor (see above), then open up an existing sbt Scala /
Java project and (in the sbt command line) enter the command:

    > coverage

This will compile your code, instrument the classes, run all your tests with
the instrumented main classes and then produce a test report which should be
automatically opened in your default web browser.

### Additional commands

In addition to the standard `coverage` command you can also enter:

 * `coverage compile` - to compile the code and instrument the classes but
   nothing else.
 * `coverage test` - to compile, instrument and test but not produce a
   report.
 * `coverage report` - a long form of just entering `coverage`.  This does
   everything.
 * `coverage clean` - clean the code coverage output files from your target
   directory.  This is probably a good thing to do whenever you are producing
   a new report as there is currently no code which checks to make sure that
   old files are removed.
   

