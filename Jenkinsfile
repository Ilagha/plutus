pipeline {
  agent any

  tools {
    jdk 'jdk21'
    maven 'maven3'
  }

  options {
    timestamps()
  }
  
  stages {
    stage('Checkout') {
      steps { checkout scm }
    }

    stage('Start DB') {
      steps {
        sh 'docker compose up -d'
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
      sh 'docker compose down -v || true'
      junit allowEmptyResults: true, testResults: 'target/surefire-reports/*.xml'	
      archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
    }
  }
}

