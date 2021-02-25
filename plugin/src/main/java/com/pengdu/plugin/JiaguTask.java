package com.pengdu.plugin;

import org.gradle.api.Action;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;
import org.gradle.process.ExecSpec;

import java.io.File;

import javax.inject.Inject;

/**
 * @author dupeng
 * @version 9.20.5 2020/9/28  3:24 PM
 * @since android young_people
 */
class JiaguTask extends DefaultTask {

    private File apk;
    private UserBean userBean;

    @Inject
    public JiaguTask(File apk, UserBean userBean) {
        setGroup("jiagu");
        this.apk = apk;
        this.userBean = userBean;
    }

    @TaskAction
    public void pluginTest() {
        getProject().exec(new Action<ExecSpec>() {
            @Override
            public void execute(ExecSpec execSpec) {
                execSpec.commandLine("java", "-version");
            }
        });
    }
}
