pipelineJob('my-js-pipeline-from-csm') {

  def repo = "${GITHUB_URL}"
  disabled()
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
