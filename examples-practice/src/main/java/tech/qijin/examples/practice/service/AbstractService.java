package tech.qijin.examples.practice.service;

import tech.qijin.util4j.aop.annotation.Log;

public abstract class AbstractService {
    @Log
    public String withAnnotation() {
        return "withAnnotation";
    }

    @Log
    public abstract String withoutAnnotation();
}
