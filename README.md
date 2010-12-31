To use this processor enter the following commands:

    *undercoverRepo at http://undercover.googlecode.com/svn/maven/repository/
    *remove coverage
    *coverage is com.proinnovate sbt-coverage 0.1-SNAPSHOT

Once this has been uploaded to a public maven repository there will be an additional repository definition required
in the above command list.

To instrument your project enter:

    coverage instrument

