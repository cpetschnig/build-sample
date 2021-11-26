import org.jenkinsci.plugins.pipeline.modeldefinition.Utils

def call(boolean condition, body) {
    def config = [:]
    body.resolveStrategy = Closure.OWNER_FIRST
    body.delegate = config

    if (condition) {
        body()
    } else {
        Utils.markStageSkippedForConditional(STAGE_NAME)
    }
}


pipeline {
  agent any
  stages {
    stage('Set Deployment Parameters') {
      when { expression { env.BRANCH_NAME.equals('master') || env.BRANCH_NAME.startsWith('feature') } }
      steps {
        script {
          echo '********Set Deployment Parameters********'
          echo '--currentBuild---'
          println(currentBuild)
          echo '--rawbuild---'
          println(currentBuild.rawBuild)
          properties([
                            parameters([
                            [$class: 'ChoiceParameter',
                                choiceType: 'PT_SINGLE_SELECT',
                                description: 'Select the Environemnt for Deployment',
                                filterLength: 1,
                                filterable: false,
                                name: 'TARGET_ENVIRONMENT',
                                randomName: 'choice-parameter-6860532452500',
                                script: [$class: 'GroovyScript',
                                    fallbackScript: [
                                        classpath: [],
                                        sandbox: false,
                                        script: 'return ["Could not get The environemnts"]'
                                    ], script: [
                                        classpath: [],
                                        sandbox: false,
                                      script: "return ${targetEnvNames}"
                                    ]
                                ]
                            ],
                            [$class: 'ChoiceParameter',
                                choiceType: 'PT_SINGLE_SELECT',
                                description: 'Select the Deployment runtime',
                                filterLength: 1,
                                filterable: false,
                                name: 'TARGET_RUNTIME',
                                randomName: 'choice-parameter-6860532452501',
                                script: [$class: 'GroovyScript',
                                    fallbackScript: [
                                        classpath: [],
                                        sandbox: false,
                                        script: 'return ["No Runtime Available"]'
                                    ], script: [
                                        classpath: [],
                                        sandbox: false,
                                        script: 'return [\'cloudhub\',\'hybrid\',]'
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
        // container('maven') {
          script {
            echo '********Maven Build step********'

              sh '''
                              echo "Foo bar Maven"
                          '''
          }
        // }
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
