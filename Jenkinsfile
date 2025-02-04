pipeline {
    agent {
        docker {
//             image 'abhishekf5/maven-abhishek-docker-agent:v1'
//             image 'maven:3.9.9-amazoncorretto-17-alpine'
            image 'soumyadeep90014842/maven-docker-git-jenkins-agent:1.0.0'
            args '--user root -v "/var/run/docker.sock:/var/run/docker.sock:rw"'
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
                    sh 'ls -altr'
                    sh 'docker build -t ${DOCKER_IMAGE} .'
                    def dockerImage = docker.image("${DOCKER_IMAGE}")
                    docker.withRegistry('', 'docker-cred') {
                        dockerImage.push()
                    }
                }
            }
        }
        stage('Update kubernetes deployment file'){
            environment {
                       GIT_REPO_NAME = "cicd-app"
                       GIT_USER_NAME = "developersoumyadeep"

                   }
            steps {
                withCredentials([string(credentialsId: 'github', variable: 'GITHUB_TOKEN')]) {
                    sh '''
                       git status
                       git checkout master
                       git config user.email "developewithsoumyadeep@gmail.com"
                       git config user.name "Soumyadeep Ganguly"
                       BUILD_NUMBER=${BUILD_NUMBER}
                       DOCKER_IMAGE="soumyadeep90014842/cicd-app:${BUILD_NUMBER}"
                       sed -i "s|soumyadeep90014842/cicd-app:.*|${DOCKER_IMAGE}|g" k8s/deployment.yaml
                       git add k8s/deployment.yaml
                       git commit -m "Update deployment image to version ${BUILD_NUMBER}"
                       git push https://${GITHUB_TOKEN}@github.com/${GIT_USER_NAME}/${GIT_REPO_NAME} HEAD:master
                   '''
               }
           }
        }

    }
}
