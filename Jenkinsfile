pipeline {
  /*
   * TODO: Implement pipeline stages/steps
   *   See documentation: https://www.jenkins.io/doc/book/pipeline/syntax/#stages
   */
   agent any
   
   stages {
           stage('Build') {
               steps {
                   export NODE_OPTIONS=--openssl-legacy-provider
                   ./gradlew assemble
               }
           }
           stage('Test') {
               steps {
                   ./gradlew test
               }
           }
           }
       }
}
