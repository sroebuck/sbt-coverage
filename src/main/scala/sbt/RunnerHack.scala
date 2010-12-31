/*
 * Copyright (c) ProInnovate Limited, 2010
 */

package sbt

/**
 * An object created within the sbt package in order to work around the fact that there appears to be no way for a
 * process to directly run a task unless it can be evoked from the command line.  Some task methods have additional
 * parameters which are not available at the command line and so the `run` method of this object provides a way of
 * running those tasks.
 *
 * Note:  This is a hack.  The sbt classes were designed to prevent you from doing this!
 */
object RunnerHack  {

  def run(project: Project, task: Project#Task, taskName: String): Option[String] = {
    impl.RunTask(task, taskName, project.parallelExecution) match {
      case Nil => None
      case x => Some(Set(x: _*).mkString("\n"))
    }
  }

}

