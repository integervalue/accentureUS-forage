pipeline {
  /*
   * TODO: Implement pipeline stages/steps
   *   See documentation: https://www.jenkins.io/doc/book/pipeline/syntax/#stages
   */
   agent any
   
   stages {
           stage('Add legacy crypto support') {
                steps{
                    export NODE_OPTIONS=--openssl-legacy-provider
                }
           }
           stage('Build') {
               steps {
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
