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
           stages {
                   stage('Setup Node.js, npm, and Yarn') {
                       steps {
                           // Determine the OS and architecture
                           sh '''
                           case "$(uname -s)" in
                               Linux*)
                                   NODE_OS=linux
                                   ;;
                               Darwin*)
                                   NODE_OS=darwin
                                   ;;
                               CYGWIN*|MINGW*|MSYS*)
                                   NODE_OS=win
                                   ;;
                               *)
                                   echo "Unsupported OS"
                                   exit 1
                                   ;;
                           esac

                           ARCH=$(uname -m)
                           if [ "$ARCH" = "x86_64" ]; then
                               NODE_ARCH=x64
                           elif [ "$ARCH" = "arm64" ]; then
                               NODE_ARCH=arm64
                           else
                               echo "Unsupported architecture: $ARCH"
                               exit 1
                           fi

                           NODE_DIST=node-v$NODE_VERSION-$NODE_OS-$NODE_ARCH

                           if [ ! -d "$NODE_DIR" ]; then
                               echo "Node.js is not installed. Installing Node.js..."
                               mkdir -p $NODE_DIR
                               curl -o nodejs.tar.xz "https://nodejs.org/dist/v$NODE_VERSION/$NODE_DIST.tar.xz"
                               tar -xJf nodejs.tar.xz -C $NODE_DIR --strip-components=1
                               rm nodejs.tar.xz
                           else
                               echo "Node.js is already installed."
                           fi
                           '''

                           // Ensure npm is available
                           sh '''
                           if ! command -v npm &> /dev/null; then
                               echo "npm could not be found. Node.js installation should include npm."
                           else
                               echo "npm is already installed."
                           fi
                           '''

                           // Install Yarn if not installed
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