package pl.greenpath.gradle.task.dev

import pl.greenpath.gradle.task.DockerRunTask

class DockerBootRunTask extends DockerRunTask {


  @Override
  protected List<String> imageName() {
    ['java:8']
  }

  @Override
  protected List<String> extraArgs() {
    return super.extraArgs() + ['--entrypoint="java -cp ' + classpath() + ' ' + mainClassFile + '"']
  }

  private String classpath() {
    new SourceSetFinder(project).findMainSourceSet().getRuntimeClasspath().filter { it.isFile() }.join(':')
  }

  private String mainClassFile() {
    'pl.greenpath.Todo'
  }
}
