pipeline {

    environment {
        registry = "gapla"


        registryCredential = 'platformdevvops'
        acPort = 1337
        dockerImage = ''
        }

    agent any

    stages {
            stage('Delete old  ') {
                        steps {
                            script {
                             try {
                           sh("sudo kubectl delete deployment wox-gapla")
                           sh("docker rmi gapla:latest")
                                        } catch (err) {
                                            echo err.getMessage()
                                        }
                            }
                         }
                    }
            stage('Build docker image') {
                 steps {
                     script {
                        dockerImage = docker.build registry
                     }
                  }
             }

//             stage('Pause after build') {
//                  steps {
//                      script {
//                         sleep time: 1, unit: 'MINUTES'
//                      }
//                   }
//              }

            stage('Run new Kuber Deploy'){
                 steps{
                    script{
                     sh("sudo kubectl apply -f configmap.yaml")
                     sh("sudo  kubectl apply -f deployment.yaml")
//                      sh("sudo  kubectl apply -f service.yaml")
//                      sh("sudo  kubectl apply -f ingress.yaml")
                    }
                 }
            }

        }
}
