pipeline {
  /*
   * TODO: Implement pipeline stages/steps
   *   See documentation: https://www.jenkins.io/doc/book/pipeline/syntax/#stages
   */
   agent any

   environment {
           NODE_VERSION = '20.16.0'
           NODE_DIR = "${WORKSPACE}/nodejs" // Directory to install Node.js
           PATH = "${NODE_DIR}/bin:${PATH}" // Add Node.js and npm to PATH
       }
   
   stages {
           stage('Setup Node.js, npm, and Yarn') {
                       steps {
                           // Install node
                           sh '''
                           if [ ! -d "$NODE_DIR" ]; then
                               echo "Node.js is not installed. Installing Node.js..."
                               mkdir -p $NODE_DIR
                               curl -o nodejs.tar.xz "https://nodejs.org/dist/v$NODE_VERSION/node-v$NODE_VERSION-linux-x64.tar.xz"
                               tar -xJf nodejs.tar.xz -C $NODE_DIR --strip-components=1
                               rm nodejs.tar.xz
                           else
                               echo "Node.js is already installed."
                           fi
                           '''
                           // Install yarn
                           sh '''
                           if ! command -v yarn &> /dev/null; then
                               echo "Yarn is not installed. Installing Yarn..."
                               npm install -g yarn
                           else
                               echo "Yarn is already installed."
                           fi
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