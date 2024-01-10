pipelineJob('my-js-pipeline-from-csm') {

  def repo = "https://github.com/Houeta/step-project-2.git"
  description("Pipeline for $repo")

  definition {
    cpsScm {
        scm {
            git {
                remote { url(repo) }
                branch('main')
                scriptPath('Jenkinsfile')
            }
        }
        }
  }
  triggers {
    scm('H/5 * * * *')
  }
}