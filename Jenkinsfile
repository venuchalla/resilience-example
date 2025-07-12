pipeline {
    agent any
tools {
        maven 'Maven 3.8.1'
        jdk 'jdk8'
    }
       parameters {
        booleanParam(name: "RELEASE", defaultValue: false)
    }
    stages {
                stage ('bulid') {
                       steps {
                        echo "buliding"
                        bat """mvn clean"""
                        bat """ mvn install"""
                    }
                }
               stage ('dockerimage-build') {
                                           steps {
                                                echo "docker image buliding"      
                                             }
                   
                                           }
    }
}
