pipeline {
    agent {
        node {
            label 'docker'
        }
    }
    environment {
        DOCKERHUB_CREDS = credentials('jenkins-dockerhub-creds')
    }

    stages {
        stage('Build') {
            steps {
                echo 'Build image'
            }
        }

        stage('Test') {
            steps {
                echo 'Test image'
            }
        }


    }

    post {
        success {
            echo 'Push built image to Docker hub'
        }
        failure {
            echo 'Tests failed'
        }
    }
}