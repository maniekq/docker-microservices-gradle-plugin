package pl.greenpath.gradle.task

import org.gradle.api.tasks.Copy

class CopyJarToDockerDirTask extends Copy {

  CopyJarToDockerDirTask() {
    setGroup(AbstractDockerTask.GROUP_NAME)
  }
}
