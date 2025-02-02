pipeline {
    agent {
        docker {
//             image 'abhishekf5/maven-abhishek-docker-agent:v1'
            image 'maven:3.9.9-amazoncorretto-17-alpine'
            args '--user root'
        }
    }
    stages {
        stage('Checkout') {
            steps {
                sh 'echo passed'
                //git branch: 'main', url: 'https://github.com/developersoumyadeep/cicd-app.git'
            }
        }
        stage('Build and Test'){
            steps {
                sh 'whoami'
                sh 'echo "building and testing"'
                sh 'mvn clean package'
            }
        }
        stage('Build and push image') {
            environment {
                DOCKER_IMAGE = "soumyadeep90014842/cicd-app:${BUILD_NUMBER}"
                REGISTRY_CREDENTIALS = credentials('docker-cred')
            }
            steps {
                script {
                    sh 'ls -ltr'
//                     sh 'apk add openrc --no-cache'
//                     sh 'apk add docker'
//                     sh 'addgroup root docker'
                    sh 'docker build -t ${DOCKER_IMAGE} .'
                    def dockerImage = docker.image("${DOCKER_IMAGE}")
                    docker.withRegistry('', 'docker-cred') {
                        dockerImage.push()
                    }
                }
            }
        }

    }
}
