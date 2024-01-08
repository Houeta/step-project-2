pipeline {
    agent {
        any
    }
    stage('test') {
        echo 'Hello world'
    }
}