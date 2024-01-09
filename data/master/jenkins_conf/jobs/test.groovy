pipelineJob('name') {
  definition {
      cps {
        script('''
            pipeline {
                agent {
                    any
                }
                stages {
                    stage("name") {
                        steps {
                            sh 'do something'
                        }
                    }
                 }
            }
        ''')
        }
  }
}