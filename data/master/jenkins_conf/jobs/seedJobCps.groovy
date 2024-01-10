

pipelineJob('js-pipeline-cps') {
    definition {
        cps {
            script(readFileFromWorkspace('my-pipeline.groovy'))

        }
    }
    triggers {
        scm('H/5 * * * *')
    }
}