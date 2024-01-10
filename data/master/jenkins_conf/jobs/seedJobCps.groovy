

pipelineJob('js-pipeline-cps') {
    definition {
        cps {
            script(readFileFromWorkspace("${Jenkins.instance.getJob('js-pipeline-cps').workspace}/my-pipeline.groovy"))

        }
    }
    triggers {
        scm('H/5 * * * *')
    }
}