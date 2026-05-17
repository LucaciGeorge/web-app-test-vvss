pipeline {
    agent any

    options {
        timestamps()
    }

    environment {
        MAVEN_OPTS = '--add-opens=java.base/java.lang=ALL-UNNAMED --add-opens=java.base/java.lang.reflect=ALL-UNNAMED --add-opens=java.base/java.util=ALL-UNNAMED --add-opens=java.base/java.text=ALL-UNNAMED --add-opens=jdk.compiler/com.sun.tools.javac.api=ALL-UNNAMED --add-opens=jdk.compiler/com.sun.tools.javac.code=ALL-UNNAMED --add-opens=jdk.compiler/com.sun.tools.javac.comp=ALL-UNNAMED --add-opens=jdk.compiler/com.sun.tools.javac.file=ALL-UNNAMED --add-opens=jdk.compiler/com.sun.tools.javac.jvm=ALL-UNNAMED --add-opens=jdk.compiler/com.sun.tools.javac.main=ALL-UNNAMED --add-opens=jdk.compiler/com.sun.tools.javac.model=ALL-UNNAMED --add-opens=jdk.compiler/com.sun.tools.javac.parser=ALL-UNNAMED --add-opens=jdk.compiler/com.sun.tools.javac.processing=ALL-UNNAMED --add-opens=jdk.compiler/com.sun.tools.javac.tree=ALL-UNNAMED --add-opens=jdk.compiler/com.sun.tools.javac.util=ALL-UNNAMED'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Run Lab05 tests') {
            steps {
                sh 'mvn -B -Dwebdriver.driver=chrome -Dheadless.mode=true -Dwebdriver.chrome.driver=/usr/bin/chromedriver -Dwebdriver.chrome.binary=/usr/bin/chromium -Dchrome.switches="--headless,--no-sandbox,--disable-dev-shm-usage,--window-size=1920,1080" clean verify serenity:aggregate'
            }
        }
    }

    post {
        always {
            junit testResults: 'target/failsafe-reports/*.xml', allowEmptyResults: true
            archiveArtifacts artifacts: 'target/site/serenity/**/*', allowEmptyArchive: true
        }
    }
}
