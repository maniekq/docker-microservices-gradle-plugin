package pl.greenpath.gradle.task

class DockerRunTask extends AbstractDockerTask {

  @Override
  protected void prepareExecution() {
    args(['run'] + runDetached() + publishedPorts() + links() + extraArgs())
    println "Running container: ${getContainerName()} args: ${getArgs()}"
  }

  private List<String> links() {
    project.extensions.docker.linkedMicroservices
        .collect { it.replaceAll('/', '-') }
        .collect { "--link=$it:$it" }
  }

  private List<String> publishedPorts() {
    project.extensions.docker.port > 0 ? ['-p', getPortMapping()] : []
  }

  private List<String> runDetached() {
    project.extensions.docker.runDetached ? ['-d'] : []
  }

  private List<String> extraArgs() {
    project.extensions.docker.runExtraArgs + ['--name=' + getContainerName(), getImageName()]
  }

}
