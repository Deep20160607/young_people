package com.pengdu.plugin;

import org.gradle.api.Action;
import org.gradle.api.Plugin;
import org.gradle.api.Project;

public class FirstPlugin implements Plugin<Project> {
    @Override
    public void apply(Project project) {

        final UserBean userBean = project.getExtensions().create("userBean", UserBean.class);

        project.afterEvaluate(new Action<Project>() {
            @Override
            public void execute(Project project) {
                System.out.println(userBean.getName());
            }
        });
    }
}