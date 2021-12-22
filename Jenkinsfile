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

          def staticOptions = '[' + getAllResults().inject([]) { array, key, value ->
                                      array += "'${key}': '${value}'"
                                      array
                                    }.join(', ') + ']'

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
                    ],
                    [$class: 'ChoiceParameter',
                        choiceType: 'PT_SINGLE_SELECT',
                        description: 'Select the Foo for Deployment',
                        filterLength: 1,
                        filterable: false,
                        name: 'TARGET_FOO',
                        randomName: 'choice-parameter-6860532452500',
                        script: [$class: 'GroovyScript',
                            fallbackScript: [
                                classpath: [],
                                sandbox: true,
                                script: 'return ["Could not get the foos"]'
                            ], script: [
                                classpath: [],
                                sandbox: true,
                                script: "return ${staticOptions}"
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




void addItemToMap(Map map, String key, String value) {
  map[key] = value
  map['X-' + key] = 'X: ' + value
}


def getAllResults() {
  def count = 0
  def result = [:]

  HttpURLConnection connection
  URL url = new URL('https://jsonplaceholder.typicode.com/')

  while (true) {
    String optionValue
    String optionDisplay

    try {
      url.set(url.protocol, url.host, url.port, '/posts/' + (count + 1).toString(), '')

      connection = url.openConnection()
      def text = connection.inputStream.text
      def json = new JsonSlurper().parseText(text)

      optionValue = json.id
      optionDisplay = json.title
    }
    catch (Exception e) {
      return [e.dump()]
    }
    finally {
      connection.disconnect()
    }

    addItemToMap(result, optionValue, optionDisplay)

    count++
    if (count > 2) { break }
  }

  return result
}
