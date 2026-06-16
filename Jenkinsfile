pipeline {
    agent {
        docker {
	     image 'gradle:jdk17'
	     args '-v /var/run/docker.sock:/var/run/docker.sock'
	}
    }
    stages {
        stage('Checkout code and prepare environment') {
            steps {
                git url: 'https://github.com/fisherb17/Continuous-Delivery-with-Docker-and-Jenkins-Second-Edition.git',
                    branch: 'master'
                sh """
                    cd Chapter08/sample1
                    chmod +x gradlew
                """
            }
        }
        stage('Run tests') {
            steps {
                sh """
                    cd Chapter08/sample1
                    ./gradlew test
                """
            }
        }
        stage('Code coverage') {
            steps {
                sh """
                    cd Chapter08/sample1
                    ./gradlew jacocoTestReport
                    ./gradlew jacocoTestCoverageVerification
                """
                publishHTML(target: [
                    reportDir: 'Chapter08/sample1/build/reports/jacoco/test/html',
                    reportFiles: 'index.html',
                    reportName: "JaCoCo Report"
                ])
            }
        }
    }
}
