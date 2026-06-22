pipeline {
    agent {
        docker {
            image 'gradle:jdk17'
            args '-v /var/run/docker.sock:/var/run/docker.sock --entrypoint='
        }
    }
    triggers {
        pollSCM('* * * * *')
    }
    stages {
        stage('Prepare environment') {
            steps {
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
                    bash gradlew test
                """
            }
        }
        stage('Code coverage') {
            when {
                changeset '**/*.java'
            }
            steps {
                sh """
                    cd Chapter08/sample1
                    chmod +x gradlew
                    bash gradlew jacocoTestReport
                    bash gradlew jacocoTestCoverageVerification
                """
                publishHTML(target: [
                    reportDir: 'Chapter08/sample1/build/reports/jacoco/test/html',
                    reportFiles: 'index.html',
                    reportName: "JaCoCo Report"
                ])
            }
        }
        stage('Checkstyle') {
            when {
                changeset '**/*.java'
            }
            steps {
                sh """
                    cd Chapter08/sample1
                    chmod +x gradlew
                    bash gradlew checkstyleMain
                    bash gradlew checkstyleTest
                """
                publishHTML(target: [
                    reportDir: 'Chapter08/sample1/build/reports/checkstyle',
                    reportFiles: 'main.html',
                    reportName: "Checkstyle Report"
                ])
            }
        }
    }
    post {
        success {
            echo 'pipeline ran perfectly'
        }
        failure {
            echo 'pipeline failure'
        }
    }
}
