pipeline {
    agent any
    environment {
        repo_path = '$(basename $PWD)'
    }
    stages {
        stage('sync source code') {
            when{ branch 'v1.0.2' }
            steps {
                sh "rsync -rva ../${repo_path} ubuntu@10.20.1.252:/home/ubuntu/"
            }
        }
        stage('build') {
            when { branch 'v1.0.2' }
            steps {
                sh "ssh ubuntu@10.20.1.252 'cd ~/'gamos1_v1.0.2' ; mvn clean package -DskipTests'"
                sh "ssh ubuntu@10.20.1.252 'cd ~/'gamos1_v1.0.2'/frontend ; npm install'"
                sh "ssh ubuntu@10.20.1.252 'cd ~/'gamos1_v1.0.2'/frontend ; ng build --prod'"
            }
        }
        stage('Deploy') {
            when { branch 'v1.0.2' }
            steps {
               sh "ssh ubuntu@10.20.1.252 'cd ~/'gamos1_v1.0.2' ; docker-compose up --build -d config-server eureka-server'"
               sh "ssh ubuntu@10.20.1.252 'cd ~/'gamos1_v1.0.2' ; docker-compose up --build -d '"
               sh "ssh ubuntu@10.20.1.252 'cd ~/'gamos1_v1.0.2' ; docker system prune --volumes -f '"
            }
        }
        stage('Deployment status') {
            when { branch 'v1.0.2' }
            steps {
                sh "ssh ubuntu@10.20.1.252 'cd ~/'gamos1_v1.0.2' ; sleep 30 ; docker ps'"
            }
        }
    }
}

