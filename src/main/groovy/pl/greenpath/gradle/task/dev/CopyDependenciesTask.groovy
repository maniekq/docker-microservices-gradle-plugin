package pl.greenpath.gradle.task.dev

import org.gradle.api.tasks.Copy

class CopyDependenciesTask extends Copy {

  CopyDependenciesTask() {
    setGroup('develpoment')
  }
}
