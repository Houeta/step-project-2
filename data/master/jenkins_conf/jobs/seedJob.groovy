pipelineJob('my-js-pipeline') {
  definition {
    cpsScm {
        scm {
            git {
                remote {
                    url('${GITHUB_URL}.git')
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