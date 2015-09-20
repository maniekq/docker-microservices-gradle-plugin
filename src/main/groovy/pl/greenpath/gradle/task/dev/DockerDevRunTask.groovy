package pl.greenpath.gradle.task.dev

import pl.greenpath.gradle.task.AbstractDockerTask

class DockerDevRunTask extends AbstractDockerTask {

  @Override
  protected void prepareExecution() {
    args(['run'] + (project.extensions.docker.runDetached ? ['-d'] : []) +
        (project.extensions.docker.port > 0 ? ['-p', getPortMapping()] : []) + getLinkedMicroservices() + project.extensions.docker.runExtraArgs +
        ['--name=' + getContainerName(), getImageName()])
    println 'Running container: ' + getContainerName() + ' args: ' + getArgs()
  }

  private List<String> getLinkedMicroservices() {
    return project.extensions.docker.linkedMicroservices.collect {
      def linkName = it.replaceAll('/', '-')
      "--link=$linkName:$linkName"
    }
  }

}
