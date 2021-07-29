pipeline {
    agent any
    tools {
        maven 'M3'
    }
    
    stages {
        stage('Checkout') {
            steps {
                  git branch: 'master', url:'https://github.com/Fahadi1/Mathilda-app.git'
					sh 'mvn clean'
                script {
                try {
                sh 'docker stop myboot && docker rm myboot'
                } catch (Exception e) {
                    sh 'echo "---=--- No container to remove ---=---" '
                }
            }
        }
        }
		
		stage('Compile') {
            steps {
                sh 'echo "---=--- Compile ---=---"'
                sh 'mvn clean compile'
            }
        }
		
		stage('test'){
            steps {
                sh 'mvn test'
            }
        }
        
        
        stage('package'){
            steps {
                sh 'echo "---=--- Package ---=---"'
                sh 'mvn -DskipTests package'
            }
              post {
            always {
                archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
            }
        } 
        }
        
        stage('SSH transfert') {
        steps {
            script {
                sshPublisher(publishers: [
                    sshPublisherDesc(configName: 'ec2-host', transfers:[
                        sshTransfer(
                          execCommand: '''
                                echo "-=- Cleaning project -=-"
                                sudo docker stop myboot  || true
                                sudo docker rm myboot || true
                                sudo docker rmi bertromain/myboot:1.0 || true
                            '''
                        ),
                        sshTransfer(
                            sourceFiles:"target/*.jar",
                            removePrefix: "target",
                            remoteDirectory: "//home//ec2-user",
                            execCommand: "ls /home/ec2-user"
                        ),
                        sshTransfer(
                            sourceFiles:"Dockerfile",
                            removePrefix: "",
                            remoteDirectory: "//home//ec2-user",
                            execCommand: '''
                                cd /home/vagrant;
                                sudo docker build -t bertromain/myboot:1.0 .; 
                                sudo docker run -d --name myboot -p 8085:8085 bertromain/myboot:1.0;
                            '''
                        )
                    ])
                ])                
            }
        }
    }
    }
    
}
