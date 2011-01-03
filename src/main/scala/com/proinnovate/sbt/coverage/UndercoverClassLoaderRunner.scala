/*
 * Copyright (c) ProInnovate Limited, 2011
 */

package com.proinnovate.sbt.coverage

/**
 * This class is here for the sole purpose of being instantiated with the same class loader as the test classes so that
 * it has access to the undercover Probe instance used to collect coverage data.  Calling the `exitUndercoverRuntime()`
 * method of this class then forces undercover to save the coverage data without requiring the JVM running sbt to
 * exit first.
 *
 * Note:  It appears that when this is run it has limited access to some classes that would normally be in the
 * classpath.  For example the scala.StringBuilder does not appear to be accessible.  So, be warned that if you make
 * this class more complex you may break things!
 */
class UndercoverClassLoaderRunner {

  /**
   * Shutdown undercover and write out the coverage data to the predetermined file location.
   */
  def exitUndercoverRuntime() {
    val probe = undercover.runtime.Probe.INSTANCE
    val coverageData = probe.getCoverageData
    coverageData.save(probe.getSettings.getCoverageFile)
  }

}

