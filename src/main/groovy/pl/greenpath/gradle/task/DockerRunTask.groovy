package pl.greenpath.gradle.task

import pl.greenpath.gradle.extension.DockerExtension

class DockerRunTask extends AbstractDockerTask {

  @Override
  protected void prepareExecution() {
    args(['run'] + runDetached() + publishedPorts() + links() + extraArgs() + name() + volumes() + imageName())
    println "Running container: ${getContainerName()} args: ${getArgs()}"
  }

  private List<String> links() {
    dockerExtension().linkedMicroservices
        .collect { it.replaceAll('/', '-') }
        .collect { "--link=$it:$it" }
  }

  private List<String> publishedPorts() {
    dockerExtension().port > 0 ? ['-p', getPortMapping()] : []
  }

  private List<String> runDetached() {
    dockerExtension().runDetached ? ['-d'] : []
  }

  protected List<String> extraArgs() {
    dockerExtension().runExtraArgs
  }

  protected List<String> volumes() {
    def result = [];
    dockerExtension().getVolumes().each {
      key, value -> result << '-v' << "$key:$value"
    }
    result
  }

  protected List<String> imageName() {
    [getImageName()]
  }

  private List<String> name() {
    ['--name=' + getContainerName()]
  }

  private DockerExtension dockerExtension() {
    project.extensions.docker as DockerExtension
  }

}
