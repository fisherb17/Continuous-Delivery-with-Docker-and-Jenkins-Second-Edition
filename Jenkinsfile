pipeline {
    agent {
        docker {
	     image 'gradle:jdk17'
	     args '-v /var/run/docker.sock:/var/run/docker.sock --entrypoint='
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
		    chmod +x gradlew
                    gradle test
                """
            }
        }
        stage('Code coverage') {
            steps {
                sh """
                    cd Chapter08/sample1
		    chmod +x gradlew
                    gradle jacocoTestReport
                    gradle jacocoTestCoverageVerification
                """
                publishHTML(target: [
                    reportDir: 'Chapter08/sample1/build/reports/jacoco/test/html',
                    reportFiles: 'index.html',
                    reportName: "JaCoCo Report"
                ])
            }
        }
	stage('Checkstyle') {
            steps {
                sh """
                    cd Chapter08/sample1
		    chmod +x gradlew
                    gradle checkstyleMain
                    gradle checkstyleTest
                """
                publishHTML(target: [
                    reportDir: 'Chapter08/sample1/build/reports/checkstyle',
                    reportFiles: 'main.html',
                    reportName: "Checkstyle Report"
                ])
            }
        }
    }
}
