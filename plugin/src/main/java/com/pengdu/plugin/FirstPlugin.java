package com.pengdu.plugin;

import com.android.build.gradle.AppExtension;
import com.android.build.gradle.api.ApplicationVariant;
import com.android.build.gradle.api.BaseVariantOutput;

import org.gradle.api.Action;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.tasks.TaskAction;

import java.io.File;

public class FirstPlugin implements Plugin<Project> {
    @Override
    public void apply(Project project) {

        final UserBean userBean = project.getExtensions().create("userBean", UserBean.class);

        project.afterEvaluate(new Action<Project>() {
            @Override
            public void execute(final Project project) {
                System.out.println(userBean.getName());
                //得到apk
                AppExtension appExtension = project.getExtensions().getByType(AppExtension.class);
                String compileSdkVersion = appExtension.getCompileSdkVersion();
                //得到一个集合（debug,release)
                appExtension.getApplicationVariants().all(new Action<ApplicationVariant>() {
                    @Override
                    public void execute(ApplicationVariant applicationVariant) {
                        applicationVariant.getOutputs().all(new Action<BaseVariantOutput>() {
                            @Override
                            public void execute(BaseVariantOutput baseVariantOutput) {
                                //得到apk （debug release）
                                File outputFile = baseVariantOutput.getOutputFile();
                                String name = outputFile.getName();
                                project.getTasks().create("jiagu" + name,
                                        JiaguTask.class, outputFile, userBean);
                                System.out.println(userBean.getName());

                            }
                        });
                    }
                });
            }
        });
    }

}