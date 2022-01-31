#!/usr/bin/env groovy

def author () {
    def commit = sh(returnStdout: true, script: 'git rev-parse HEAD')
    return sh(returnStdout: true, script: "git --no-pager show -s --format='%an' ${commit}").trim()
}

def lastCommit () {
    return sh(returnStdout: true, script: "git rev-parse HEAD | cut -c 1-7")
}

def lastCommitDate () {
    return sh(returnStdout: true, script: "git show -s --format=%ci HEAD | cut -c 1-10")
}

def secondLastCommit () {
    return sh(returnStdout: true, script: "git rev-parse HEAD~1 | cut -c 1-7")
}

def secondLastCommitDate () {
    return sh(returnStdout: true, script: "git show -s --format=%ci HEAD~1 | cut -c 1-10")
}

def message () {
    return sh(returnStdout: true, script: 'git log -1 --pretty=%B').trim()
}
