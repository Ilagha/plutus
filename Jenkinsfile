pipeline {
  agent any

  tools {
    jdk 'jdk21'
    maven 'maven3'
  }

  stages {
    stage('Checkout') {
      steps {
        checkout scm
      }
    }

    stage('Build & Test') {
      steps {
        sh 'mvn -B clean test'
      }
    }

    stage('Package') {
      steps {
        sh 'mvn -B clean package -DskipTests'
      }
    }
  }

  post {
    always {
      junit 'target/surefire-reports/*.xml'
      archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
    }
  }
}
