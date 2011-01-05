/*
 * Copyright (c) ProInnovate Limited, 2011
 */

package com.proinnovate.sbt.utils

case class SbtVersion(versionString: String) extends Ordered[SbtVersion] {

  private val Version = """(\d+)\.(\d+)\.(\d+)(\.RC(\d+))?""".r

  lazy val Version(majorString, minorString, releaseString, rcFullString, rcString) = versionString

  lazy val major = majorString.toInt
  lazy val minor = minorString.toInt
  lazy val release = releaseString.toInt
  lazy val rc = if (rcString == null) None else Some(rcString.toInt)

  private lazy val versionNumber = (((major * 100 + minor) * 100 + release) * 100 - (if (rc.isDefined) (50 - rc.get) else 0))

  def compare(that: SbtVersion): Int = this.versionNumber - that.versionNumber
  
}


