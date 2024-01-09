pipelineJob('my-js-pipeline-4') {
  definition {
    cpsScm {
        scm {
            git {
                remote {
                    url("${GITHUB_URL}.git")
                }
                branch('*/main')
            }
        }
        scriptPath('Jenkinsfile')
        lightweight()
        }
  }
  triggers {
    githubPush()
    
  }
}