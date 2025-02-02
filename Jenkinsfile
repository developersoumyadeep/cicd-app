pipeline {
    agent {
        docker {
            image 'maven:3.9.9-amazoncorretto-17-alpine'
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
                sh 'echo "building and testing"'
                sh 'mvn clean package'
            }
        }

    }
}