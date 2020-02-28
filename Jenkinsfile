pipeline {
  agent any
  stages {
    stage('Build') {
      agent {
        docker {
          args '-p 3000:3000'
          image 'maven:3-alpine'
        }

      }
      steps {
        sh 'npm install'
      }
    }

  }
}