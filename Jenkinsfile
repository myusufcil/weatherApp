pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        echo 'BuildStarted'
        sh 'gradlew build'
        echo 'BuildFinished'
      }
    }

    stage('Test') {
      steps {
        echo 'testStarted'
      }
    }

    stage('deploy') {
      steps {
        echo 'deploymaster'
      }
    }

  }
}