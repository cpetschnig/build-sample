import groovy.json.JsonSlurper

pipeline {
  agent any
  stages {
    stage('Set Deployment Parameters') {
      when { expression { env.BRANCH_NAME.equals('master') || env.BRANCH_NAME.startsWith('feature') } }
      steps {
        script {
          echo '********Set Deployment Parameters********'
          // sh(script: 'echo `date`, begin >> /tmp/script.log')

          // def code2 = load "./myscript.groovy"
          // result = code2.mymethod()
          // echo result[0]

def code = sprintf("""
def getArtifactList(items) {
  def artifactData = [:]
  items.each { entry ->
      def tempMap = [:]
      tempMap['artifact_name'] = entry.name
      def assetJar = entry.assets[0]
      def appJarPath = assetJar.downloadUrl
      def sepPosStart = appJarPath.lastIndexOf('/')
      def sepPosEnd = appJarPath.lastIndexOf('.jar')
      def appName = appJarPath.substring(sepPosStart + 1, sepPosEnd)
      tempMap['path'] = appJarPath
      artifactData[appName] = tempMap
  }
  return artifactData
}

try {
  if ("%s" in %s) {
    choices = ["Apple","Banana","Cherry"]
  } else {
    choices = ["Amaranth","Beet","Cabbage"]
  }
  return choices
  }
catch (Exception e) {
  return [e.dump()]
}
""", "blah", '["blub"]')

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
                                sandbox: false,
                                  script: code

                                  // 'def code = load "./myscript.groovy"; return [code.dump()]'
                                    // result = code.mymethod()
                                    // return result

                                  // HttpURLConnection connection;

                                  // //sh(script: 'echo `date`, 11 >> /tmp/script.log')

                                  // try {

                                  //   URL url = new URL('https://jsonplaceholder.typicode.com/posts/1')

                                  //   def url = 'https://jsonplaceholder.typicode.com/posts/1'
                                  //   def urlFinal = new StringBuilder(url)
                                  //   def response =  sh(returnStdout: true, script: 'curl -s ' + urlFinal).trim()
                                  //   def jsonSlurper1 = new JsonSlurper()
                                  //   def json = jsonSlurper1.parseText(response)

                                  //   return [
                                  //     json.title
                                  //     ]
                                  // }
                                  // catch(Exception e) {
                                  //   return [e.dump()]
                                  // }
                                  // """
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
