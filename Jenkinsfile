pipeline {
    agent any

    tools {
        maven 'Maven_3.8.1'
        jdk 'JDK_11'
    }

    stages {
        stage('Build') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Reports') {
            steps {
                junit '**/target/surefire-reports/*.xml'
            }
        }
    }

    post {
        success {
            echo '✅ Build and tests successful.'
        }
        failure {
            echo '❌ Something went wrong.'
        }
    }
}