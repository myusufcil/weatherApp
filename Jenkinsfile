pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        echo 'tebrikler'
      }
    }

    stage('Test') {
      steps {
        echo 'test'
      }
    }

  }
  post {
    always {
      archiveArtifacts(artifacts: 'build/libs/**/*.jar', fingerprint: true)
      junit 'build/reports/**/*.xml'
    }

  }
}