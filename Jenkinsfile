pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        sh 'echo build'
      }
    }

    stage('Backend') {
      parallel {
        stage('Unit') {
          steps {
            sh 'echo unit'
          }
        }

        stage('Performance') {
          steps {
            sh 'echo Performance'
          }
        }

      }
    }

    stage('Frontend') {
      steps {
        sh 'echo Frontend'
      }
    }

    stage('Static Analysis') {
      steps {
        sh 'echo static'
      }
    }

    stage('Deoploy') {
      steps {
        sh 'echo Deploy'
      }
    }

  }
}