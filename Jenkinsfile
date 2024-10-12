pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build and Start Services') {
            steps {
                script {
                    sh 'docker-compose up -d --build'
                }
            }
        }

        stage('Health Check') {
            steps {
                script {
                    sh 'sleep 60'

                    sh 'curl -f http://localhost:8761 || exit 1'
                    sh 'curl -f http://localhost:8888 || exit 1'
                    sh 'curl -f http://localhost:8765 || exit 1'
                }
            }
        }
    }

    post {
        failure {
            sh 'docker-compose down -v'
        }
    }
}