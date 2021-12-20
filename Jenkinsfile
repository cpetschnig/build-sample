import groovy.json.JsonSlurper

pipeline {
  agent any
  stages {
    stage('Set Deployment Parameters') {
      when { expression { env.BRANCH_NAME.equals('master') || env.BRANCH_NAME.startsWith('feature') } }
      steps {
        script {
          echo '********Set Deployment Parameters********'

          def codeFromFile = readFile("./myscript.groovy")
          echo codeFromFile

          properties([
                    parameters([
                    [$class: 'ChoiceParameter',
                        choiceType: 'PT_SINGLE_SELECT',
                        description: 'Select the Environment for Deployment',
                        filterLength: 1,
                        filterable: false,
                        name: 'TARGET_ENVIRONMENT',
                        randomName: 'choice-parameter-6860532452500',
                        script: [$class: 'GroovyScript',
                            fallbackScript: [
                                classpath: [],
                                sandbox: true,
                                script: 'return ["Could not get the environments"]'
                            ], script: [
                                classpath: [],
                                sandbox: true,
                                  script: codeFromFile
                            ]
                        ]
                    ]

            ])
          ])
        }
      }
    }

    stage('Build') {
      steps {
        script {
          echo '********Maven Build step********'

            sh '''
                  echo "Maven"
               '''
        }
      }
    }
  }

  post {
    always {
      echo 'This will always run'
    }
    success {
      echo 'This will run only if successful'
    }
    failure {
      echo 'This will run only if failed'
    }
    unstable {
      echo 'This will run only if the run was marked as unstable'
    }
    changed {
      echo 'This will run only if the state of the Pipeline has changed'
      echo 'For example, if the Pipeline was previously failing but is now successful'
    }
  }
}
