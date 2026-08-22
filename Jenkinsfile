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
                bat '"C:\\Users\\DELL\\AppData\\Local\\Programs\\DockerDesktop\\resources\\bin\\docker.exe" build -t spring-cache-app .'
            }
        }

        stage('Stop Old Container') {
            steps {
                bat '''
                    "C:\\Users\\DELL\\AppData\\Local\\Programs\\DockerDesktop\\resources\\bin\\docker.exe" stop spring-cache-container 2>nul || exit /b 0
                    "C:\\Users\\DELL\\AppData\\Local\\Programs\\DockerDesktop\\resources\\bin\\docker.exe" rm spring-cache-container 2>nul || exit /b 0
                '''
            }
        }

        stage('Run Container') {
            steps {
                bat '''
                    "C:\\Users\\DELL\\AppData\\Local\\Programs\\DockerDesktop\\resources\\bin\\docker.exe" run -d --name spring-cache-container -p 8080:8080 spring-cache-app
                '''
            }
        }
    }
}