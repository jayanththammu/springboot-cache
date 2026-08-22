pipeline {
    agent any

    stages {

        stage('Build JAR') {
            steps {
                bat 'mvn clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                bat 'docker build -t spring-cache-app .'
            }
        }

        stage('Stop Old Container') {
            steps {
                bat '''
                    docker stop spring-cache-container 2>nul || exit /b 0
                    docker rm spring-cache-container 2>nul || exit /b 0
                '''
            }
        }

        stage('Run Container') {
            steps {
                bat '''
                    docker run -d --name spring-cache-container -p 8080:8080 spring-cache-app
                '''
            }
        }
    }
}