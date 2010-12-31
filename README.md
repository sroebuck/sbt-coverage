sbt-coverage code coverage processor for sbt
============================================

Version: 0.1-SNAPSHOT

This code is currently under development.  It produces code coverage reports
of projects in sbt using the [undercover](http://code.google.com/p/undercover/)
code coverage tool.

Known issues
------------

Currently the tool collect code coverage data from running the full suite of
project tests from within sbt.  However, the data is only written to disk when
the JVM halts which is when sbt exits, not when the tests complete.
Consequently, the first report that is produced will have no coverage. Exiting
sbt and then re-running the report a second time will produce a coverage
report using the data from the previous run.  I am investigating ways of
working around this.

There is no code listing output in the current reports.  I haven't looked into
this in detail to discover what is going wrong.

Building and installing
-----------------------

Until I have published this to a public maven repository it is currently
necessary to download the source and build it locally.  Having downloaded
the source you should be able to enter the following commands from within
sbt to install the processor:

    publish-local
    *undercoverRepo at http://undercover.googlecode.com/svn/maven/repository/
    *coverage is com.proinnovate sbt-coverage 0.1-SNAPSHOT

The first line should compile the processor and publish it to your local ivy
repository.  The second line defines the repository for getting the undercover
dependency.  The final line defines the processor which is read from the local
repository.

If you are making changes to the code and wish to reload the processor you
need to remove the existing one first with:

    *remove coverage

You may also need to exit sbt and start it again for the new coverage tool
to work properly.

Using the processor
-------------------

First install the processor (see above), then open up an existing sbt Scala /
Java project and (in the sbt command line) enter the command:

    coverage

This will compile your code, instrument the classes, run all your tests with
the instrumented main classes and then produce a test report.

However, until a current issue is fixed you must then exit sbt, restart it and
re-run the coverage report:

    exit
    sbt
    coverage
    
Now you should have a coverage report here:

    target/scala-2.x.x/coverage/html/index.html
    
where `scala-2.x.x` matches the scala version set for your project. The
directory with the file above has a lot of other related report files, but
the one above is the main index file.  Open this in your web-browser of
choice and enjoy!

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
   

