/*
 * Copyright (c) ProInnovate Limited, 2011
 */

package com.proinnovate.sbt.utils

import org.scalatest.FunSuite

class SbtVersionTest extends FunSuite {

  test("Check that a sbt version string is greater than or less than required values") {
    assert(SbtVersion("0.7.4") < SbtVersion("0.7.5.RC0"))
    assert(SbtVersion("0.7.5") > SbtVersion("0.7.5.RC0"))
    assert(SbtVersion("0.7.5") > SbtVersion("0.7.4"))
    assert(SbtVersion("0.7.5") > SbtVersion("0.6.12"))
  }

}

