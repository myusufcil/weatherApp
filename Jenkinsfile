pipeline {
  agent any
  stages {
    stage('Build') {
      agent any
      steps {
        sh './gradlew compileDebugSources'
    }
    stage('Unit test') {
      steps {
        sh './gradlew testDebugUnitTest testDebugUnitTest'
        junit '**/TEST-*.xml'
      }
    }
    stage('Build APK') {
      steps {
        sh './gradlew assembleDebug'
        archiveArtifacts '**/*.apk'
      }
    }
    stage('Static analysis') {
      steps {
        sh './gradlew lintDebug'
        androidLint(pattern: '**/lint-results-*.xml')
      }
    }
    stage('Deploy') {
      when {
        branch 'master'
      }
      environment {
        SIGNING_KEYSTORE = credentials('my-app-signing-keystore')
        SIGNING_KEY_PASSWORD = credentials('my-app-signing-password')
      }
      post {
        success {
          mail(to: 'myusufcl7@gmail.com', subject: 'New build available!', body: 'Check it out!')

        }

      }
      steps {
        sh './gradlew assembleRelease'
        archiveArtifacts '**/*.apk'
        androidApkUpload(googleCredentialsId: 'Google Play', apkFilesPattern: '**/*-release.apk', trackName: 'beta')
      }
    }
  }
  post {
    failure {
      mail(to: 'myusufcl7@gmail.com', subject: 'Oops!', body: "Build ${env.BUILD_NUMBER} failed; ${env.BUILD_URL}")
    }

  }
  options {
    skipStagesAfterUnstable()
  }
}