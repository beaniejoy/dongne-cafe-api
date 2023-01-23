pipeline {
    agent any

    stages {
        stage('Init') {
            steps {
                sh 'printenv'
            }
        }

        stage('Github clone') {
            steps {
                git branch: '${BUILD_BRANCH}', url: 'https://github.com/beaniejoy/dongne-cafe-api.git'
            }
        }

        stage('Test') {
            steps {
                sh './gradlew clean :dongne-service-api:test --stacktrace'
            }
        }

        stage('Build') {
            steps {
                sh './gradlew clean :dongne-service-api:build -x test'
            }
        }
    }
}