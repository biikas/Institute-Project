pipeline {
  agent any
  stages {
    stage('Custom Code Validation') {
      when {
        expression {
          BRANCH_NAME ==~ /(master|master-wip)/
        }

      }
      steps {
         sh 'bash ./mvnw compile'
      }
    }


    stage('Build') {
      when {
        expression {
          BRANCH_NAME ==~ /(master|master-wip)/
        }

      }
      steps {
        sh 'bash ./mvnw clean install -DskipTests'
      }
    }
    stage('Deploy') {
          when {
            expression {
              BRANCH_NAME ==~ /(master|master-wip)/
            }

          }
          parallel{

              stage('Deploy Bank Xp') {
          steps {
            	sh 'sshpass -p "bansksmart@123" scp bankxsp-web/target/ROOT.war  banksmart@10.13.194.173:/home/banksmart/omni-banking/webapps/ROOT.war'
              }
             }
            }
          }
  }
   options {
      	buildDiscarder(logRotator(numToKeepStr: '2', artifactNumToKeepStr: '2'))
     }
}
