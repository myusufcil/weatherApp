pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        echo 'tebrikler'
        sh 'gradlew build.bat'
      }
    }

    stage('Test') {
      steps {
        echo 'test'
      }
    }

  }
}