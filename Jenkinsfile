pipeline {
  /*
   * TODO: Implement pipeline stages/steps
   *   See documentation: https://www.jenkins.io/doc/book/pipeline/syntax/#stages
   */
   agent any
   
   stages {
        stage('Install Node.js and Yarn') {
                   steps {
                       sh '''
                       # Install Node.js and npm
                       curl -fsSL https://deb.nodesource.com/setup_20.x | bash -
                       apt-get install -y nodejs

                       # Install Yarn
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