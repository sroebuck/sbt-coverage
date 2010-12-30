package com.proinnovate.sbt.coverage

import _root_.sbt._
import _root_.sbt.processor.BasicProcessor

class SbtCoverageProcessor extends BasicProcessor {
  
  def apply(project: Project, args: String) {
    project.log.info("Result of compile was %s".format(project.act("compile")))
    project.log.info("Processor test. args: '" + args + "'")
  }

  // =================================================================================================================
  // INSTRUMENTATION ACTIONS
  // =================================================================================================================

  // Create code coverage task `instrument` which instruments the compiled classes for the `undercover`
  // code coverage tool...
/*

  lazy val instrument = instrumentAction

  def instrumentAction = instrumentTask().dependsOn(compile,testCompile)
          .describedAs("Instrument project classes ready for code coverage testing.")

  def instrumentTask() {
    log.info("Instrumenting main classes")
    instrumentClasses(mainCompilePath, outputPath / "classes-inst")
    log.info("Instrumenting test classes")
    instrumentClasses(testCompilePath, outputPath / "test-classes-inst")
    None
  }

  // Create a code coverage test task `test-coverage` which runs the tests over the instrumented
  // classes...

  lazy val testCoverage = testCoverageAction

  def testCoverageAction = testCoverageTask() dependsOn (instrument)

  def testCoverageTask() {
    var instTestClasspath = testClasspath.
            --- (outputPath / "classes").
            --- (outputPath / "test-classes").
            +++ (outputPath / "classes-inst" / "classes").
            +++ (outputPath / "test-classes-inst" / "classes").
            +++ (rootProject.info.projectPath ** "undercover*.jar")

    val coverageDataPath = outputPath / "coverage" / "undercover.cd"
    log.debug("Coverage data path: " + coverageDataPath.absolutePath)

    val settings = new undercover.runtime.UndercoverSettings()
    settings.setCoverageSaveOnExit(true)
    settings.setCoverageFile(coverageDataPath.asFile)
    (outputPath / "classes-inst").asFile.mkdirs
    val propertiesFile = (outputPath / "classes-inst" / "undercover.properties").asFile
    settings.save(propertiesFile)

    testTask(testFrameworks, instTestClasspath, testCompileConditional.analysis, testOptions)
  }

  lazy val testCoverageReport = testCoverageReportAction

  def testCoverageReportAction = testCoverageReportTask() dependsOn(testCoverage)

  def testCoverageReportTask() {
    val sourceFinder = new undercover.report.SourceFinder()
    val sourcePathFiles = (testSources +++ mainSources).getFiles
    val sourcePathsJavaList = java.util.Arrays.asList(sourcePathFiles.toArray: _*)
    sourceFinder.setSourcePaths(sourcePathsJavaList)

    val metaDataFile = (outputPath / "classes-inst" / "undercover.md").asFile
    val coverageDataFile = (outputPath / "coverage" / "undercover.cd").asFile
    val builder = new undercover.report.ReportDataBuilder(metaDataFile, coverageDataFile)
    builder.setProjectName(projectName.toString)
    builder.setSourceFinder(sourceFinder)
    val reportData = builder.build()
    val outputEncoding = "UTF-8"

    val formats = List("html")
    formats.foreach { format =>
      log.info("Generating " + format + " report")
      format match {
        case "html" => {
            val report = new undercover.report.html.HtmlReport()
            report.setReportData(reportData)
            val outputDirectory = (outputPath / "coverage" / "html").asFile
            report.setOutputDirectory(outputDirectory)
            report.setEncoding(outputEncoding)
            report.generate()
          }
        case "coberturaxml" => {
            val report = new undercover.report.xml.CoberturaXmlReport(reportData)
            val outputFile = (outputPath / "coverage" / "cobertura.xml").asFile
            report.writeTo(outputFile, outputEncoding)
          }
        case "emmaxml" => {
            val report = new undercover.report.xml.EmmaXmlReport(reportData)
            val outputFile = (outputPath / "coverage" / "emma.xml").asFile
            report.writeTo(outputFile, outputEncoding)
          }
        case _ => log.warn("Unknown report format: " + format)
      }
    }

    None
  }
*/

  /**
   * Use undercover to instrument the compiled main and test classes and put them in instrumented
   * classes directories within the target directory.
   */
  private def instrumentClasses(inputPath: Path, outputPath: Path) {

    val instr = new undercover.instrument.OfflineInstrument()
    val paths = new java.util.ArrayList[java.io.File]()
    paths.add(inputPath.asFile)
    instr.setInstrumentPaths(paths)

    instr.setOutputDirectory(outputPath.asFile)

    val globFilter = new undercover.instrument.filter.GlobFilter(Array[String](), Array[String]())
    instr.setFilter(globFilter)

    val metaFile = (outputPath / "undercover.md").asFile
    instr.setMetaDataFile(metaFile)

    instr.fullcopy()
  }

}
