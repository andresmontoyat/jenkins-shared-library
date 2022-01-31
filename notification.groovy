#!/usr/bin/env groovy

def slackMessage(String pipeline = null, String author = null, String message = null) {
    def colorCode = '#848484' // Gray

    switch (currentBuild.result) {
        case 'SUCCESS':
            colorCode = '#00FF00' // Green
            break
        case 'UNSTABLE':
            colorCode = '#FFFF00' // Yellow
            break
        case 'FAILURE':
            colorCode = '#FF0000' // Red
            break;
    }

    String text = """
        Pipeline: `${pipeline}`
        Job name: `${env.JOB_NAME}`
        Build: `#${env.BUILD_ID}`
        Build status: `${currentBuild.result}`
        Duration: `${currentBuild.duration/1000}seg`
        Commit: `${env.GIT_COMMIT}`
        Author: ${author}
        Message: ${message}
        Pipeline details: <${env.BUILD_URL} | See in web console>
    """.stripIndent()

    return slackSend(color: colorCode, message: text, botUser: true, iconEmoji: 'deadpool')
}

def bitbucketStatus(String state = null, String key = null, String name = null) {

    bitbucketStatusNotify(
        buildState: state,
        buildKey: key,
        buildName: name,
        commitId: "`${env.GIT_COMMIT}`"
    )
}
