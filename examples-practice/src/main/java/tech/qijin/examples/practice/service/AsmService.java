package tech.qijin.examples.practice.service;

import org.objectweb.asm.ClassReader;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class AsmService {

    public void test() throws IOException {
        ClassReader classReader = new ClassReader("tech/qijin/examples/practice/service/MxBeanService");
        classReader.getClassName();
    }
}
