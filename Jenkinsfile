pipeline {
  agent any
  stages {
    stage('Build') {
      agent any
      steps {
        sh 'gradlew compileDebugSources'
      }
    }
  }
}