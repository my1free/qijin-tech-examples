package tech.qijin.examples.practice.service;

import org.springframework.stereotype.Service;
import tech.qijin.util4j.aop.annotation.Log;

@Service
public class InheritAnnotationService extends AbstractService{
    @Override
    public String withoutAnnotation() {
        return "withoutAnnotation";
    }
}
