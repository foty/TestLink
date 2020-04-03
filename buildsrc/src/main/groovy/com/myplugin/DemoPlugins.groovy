package com.myplugin

import org.gradle.api.Plugin
import org.gradle.api.Project

class DemoPlugins implements Plugin<Project> {

    @Override
    void apply(Project project) {

        System.out.println("我是插件，执行成功")
        //创建一个task任务
        project.task('hello') {
            println "hello world "
            println "我是插件"
        }
    }
}