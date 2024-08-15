pipeline {
  /*
   * TODO: Implement pipeline stages/steps
   *   See documentation: https://www.jenkins.io/doc/book/pipeline/syntax/#stages
   */
   agent any
   
   stages {
           stage('Setup') {
               steps {
               // Install node
                   sh '''
                       curl -sL https://deb.nodesource.com/setup_20.16.0 | sudo -E bash -
                       sudo apt-get install -y nodejs
                   '''

               // Install yarn
                   sh '''
                       npm install -g yarn
                   '''
               }
           }
           stage('Build') {
               steps {
                   sh 'export NODE_OPTIONS=--openssl-legacy-provider'
                   sh './gradlew assemble'
               }
           }
           stage('Test') {
               steps {
                   sh './gradlew test'
               }
           }
   }
}