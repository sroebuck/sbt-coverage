package com.proinnovate.sbt.coverage

import _root_.sbt._
import _root_.sbt.processor.BasicProcessor

class SbtCoverageProcessor extends BasicProcessor {
  
  def apply(project: Project, args: String) {
    project.log.info("Processor test. args: '" + args + "'")
  }

}
