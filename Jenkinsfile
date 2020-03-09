pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        echo 'tebrikler'
        sh 'gradlew build'
      }
    }

    stage('Test') {
      steps {
        echo 'test'
      }
    }

  }
}