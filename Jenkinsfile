node {

   stage('Clone Repository') {
        // Get some code from a GitHub repository
        git 'https://leo-yassuda@bitbucket.org/leo-yassuda/product-service.git'
   }
   stage('Build Maven Image') {
        docker.build("maven-build")
   }

   stage('Run Maven Container') {

        //Remove maven-build-container if it exisits
        sh " docker rmi -f maven-build-container"

        //Run maven image
        sh "docker run --rm --name maven-build-container maven-build"
   }

   stage('Deploy Spring Boot Application') {

         //Remove maven-build-container if it exisits
        sh " docker rmi -f java-deploy-container"

        sh "docker run --name java-deploy-container --volumes-from maven-build-container -d -p 8080:8080 deploys/product-service-deploy"
   }

}