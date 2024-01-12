pipelineJob('js-pipeline-cps') {

    description("Pipeline for ${GITHUB_URL}")

    definition {
        cps {
            sandbox()
            script('''
                pipeline {
                    agent {
                        label 'docker'
                    }

                    environment {
                        github_url = "https://github.com/Houeta/step-project-2"
                        major_v = 1
                        registry = 'pathetic/step-project'

                        DOCKER_IMAGE = "${registry}:${major_v}.${env.BUILD_NUMBER}"
                        DOCKER_CREDS = credentials('docker-creds')
                    }

                    stages {
                        stage('Checkout') {
                            steps {
                                git branch: 'main', url: github_url
                            }
                        }

                        stage('Build image') {
                            steps {
                                echo "Build image with tag v${major_v}.${env.BUILD_NUMBER}"
                                sh "docker build -t ${DOCKER_IMAGE} ."
                            }
                        }

                        stage('Test') {
                            steps {
                                echo 'Run tests based on jtest'
                                sh "docker run ${DOCKER_IMAGE} test"
                            }
                        }
                    }

                    post {
                        success {
                            sh """
                            docker login -u ${DOCKER_CREDS_USR} -p ${DOCKER_CREDS_PSW}
                            docker push ${DOCKER_IMAGE}
                            """
                        }
                        failure {
                            echo 'Tests failed'
                        }
                    }

                }
            ''')
        }
    }
    triggers {
        scm('H/5 * * * *')
    }
}