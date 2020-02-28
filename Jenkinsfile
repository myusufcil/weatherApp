pipeline {
  agent any
  stages {
    stage('Build') {
      agent {
        node {
          label 'build'
        }

      }
      steps {
        sh 'npm install'
      }
    }

  }
}