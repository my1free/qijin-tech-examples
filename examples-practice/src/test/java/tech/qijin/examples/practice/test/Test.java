package tech.qijin.examples.practice.test;

import com.google.common.collect.Maps;
import com.google.common.hash.Hashing;
import com.googlecode.aviator.AviatorEvaluator;
import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import org.assertj.core.util.Lists;
import org.objectweb.asm.*;
import org.objectweb.asm.tree.*;
import org.objectweb.asm.tree.ClassNode;
//import tech.qijin.examples.practice.service.asm.MyClassVisitor;
import tech.qijin.util4j.utils.DateUtil;

import java.io.*;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * @author michealyang
 * @date 2019-11-06
 * @relax: 开始眼保健操 ←_← ↓_↓ →_→ ↑_↑
 */
@Slf4j
public class Test extends ClassLoader implements Opcodes {
    @org.junit.Test
    public void consistentHash() throws IOException {
        long l = DateUtil.now().getTime() / 1000;
        System.out.print(l);
        System.out.println((int) l);
        System.out.println(2 ^ 2);
    }

    @org.junit.Test
    public void aviator() {
        String name = "唐简";
        Map<String, Object> env = new HashMap<>();
        env.put("name", name);
        env.put("a", 2);
        Object result = AviatorEvaluator.execute("a > 1", env);
        System.out.println(result);
    }

    @org.junit.Test
    public void jsqlParser() throws JSQLParserException {
        Statement stmt = CCJSqlParserUtil.parse("SELECT * FROM tab1");
        log.info("{}", stmt);
    }

    @org.junit.Test
    public void vm() throws IOException, AttachNotSupportedException {
        VirtualMachineDescriptor virtualMachineDescriptor = null;
        for (VirtualMachineDescriptor descriptor : VirtualMachine.list()) {
            String pid = descriptor.id();
            if (pid.equals(Long.toString(75369L))) {
                virtualMachineDescriptor = descriptor;
                break;
            }
        }
        VirtualMachine virtualMachine = null;
        virtualMachine = VirtualMachine.attach(virtualMachineDescriptor);
        Properties targetSystemProperties = virtualMachine.getSystemProperties();

        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();

        Map<String, Thread> threads = getThreads();
        System.out.printf("ads");
    }

    public static ThreadGroup getRoot() {
        ThreadGroup group = Thread.currentThread().getThreadGroup();
        ThreadGroup parent;
        while ((parent = group.getParent()) != null) {
            group = parent;
        }
        return group;
    }

    public static Map<String, Thread> getThreads() {
        ThreadGroup root = getRoot();
        Thread[] threads = new Thread[root.activeCount()];
        while (root.enumerate(threads, true) == threads.length) {
            threads = new Thread[threads.length * 2];
        }
        SortedMap<String, Thread> map = new TreeMap<String, Thread>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        for (Thread thread : threads) {
            if (thread != null) {
                map.put(thread.getName() + "-" + thread.getId(), thread);
            }
        }
        return map;
    }

    @org.junit.Test
    public void asm() throws IOException, InvocationTargetException, IllegalAccessException {
//        ClassReader classReader = new ClassReader("tech/qijin/examples/practice/service/MxBeanService");
//        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS);
//        ClassVisitor classVisitor = new MyClassVisitor(classWriter);
//        classReader.accept(classVisitor, ClassReader.SKIP_DEBUG);
//        byte[] data = classWriter.toByteArray();
//        File f = new File("MxBeanServiceE.class");
//        FileOutputStream fout = new FileOutputStream(f);
//        fout.write(data);
//        fout.close();
        ClassReader classReader = new ClassReader("tech/qijin/examples/practice/service/MxBeanService");
        ClassNode cn = new ClassNode();
        classReader.accept(cn, ClassReader.EXPAND_FRAMES);
        List<MethodNode> methods = cn.methods;
        Map<String, List<String>> rtn = new HashMap<String, List<String>>();
        List<AbstractInsnNode> nodes = Lists.newArrayList();
        for (int i = 0; i < methods.size(); ++i) {
            List<LocalVariable> varNames = new ArrayList<LocalVariable>();
            MethodNode method = methods.get(i);
            List<LocalVariableNode> local_variables = method.localVariables;
            for (int l = 0; l < local_variables.size(); l++) {
                String varName = local_variables.get(l).name;
                // index
                int index = local_variables.get(l).index;
                if (!"this".equals(varName)) // 非静态方法,第一个参数是this
                    varNames.add(new LocalVariable(index, varName));
            }
            LocalVariable[] tmpArr = varNames.toArray(new LocalVariable[0]);
            Arrays.sort(tmpArr);
            List<String> list = new ArrayList<String>();
            for (LocalVariable var : tmpArr) {
                list.add(var.name);
            }

            if (method.name.equals("info")) {
                AbstractInsnNode insnNode = method.instructions.getFirst();
                while (insnNode != null) {
                    nodes.add(insnNode);
                    insnNode = insnNode.getNext();
                }
                System.out.println("sadf");
            }
            rtn.put(method.name + "," + method.desc, list);
        }
        System.out.println(rtn);
//        ClassWriter cw = new ClassWriter(0);
//        cw.visit(V1_1, ACC_PUBLIC, "Example", null, "java/lang/Object", null);
//
//        // 为隐含的默认构造器创建MethodWriter
//        MethodVisitor mw = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null,
//                null);
//        // 将this变量放入局部变量表
//        mw.visitVarInsn(ALOAD, 0);
//        // 调用父类的默认构造函数
//        mw.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V",false);
//        mw.visitInsn(RETURN);
//        // 这段代码使用了最大为1的栈元素，且只有一个局部变量
//        mw.visitMaxs(1, 1);
//        mw.visitEnd();
//
//        // 为main方法创建MethodWriter
//        mw = cw.visitMethod(ACC_PUBLIC + ACC_STATIC, "main",
//                "([Ljava/lang/String;)V", null, null);
//        // 将System的out域入栈
//        mw.visitFieldInsn(GETSTATIC, "java/lang/System", "out",
//                "Ljava/io/PrintStream;");
//        // String类型的"Hello World!"常量入栈
//        mw.visitLdcInsn("Hello world!");
//        // 调用System.out的println方法
//        mw.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println",
//                "(Ljava/lang/String;)V", false);
//        mw.visitInsn(RETURN);
//        // 这段代码使用了最大为2的栈元素，包含两个局部变量
//        mw.visitMaxs(2, 2);
//        mw.visitEnd();
//
//        // 获得Example类的字节码，并且动态加载它
//        byte[] code = cw.toByteArray();
//
//        FileOutputStream fos = new FileOutputStream("Example.class");
//        fos.write(code);
//        fos.close();
//
//        Test loader = new Test();
//        Class<?> exampleClass = loader.defineClass("Example", code, 0,
//                code.length);
//
//        // 使用动态生成的类打印'Helloworld'
//        exampleClass.getMethods()[0].invoke(null, new Object[] { null });
    }

    static class LocalVariable implements Comparable<LocalVariable> {
        public int index;
        public String name;

        public LocalVariable(int index, String name) {
            this.index = index;
            this.name = name;
        }

        public int compareTo(LocalVariable o) {
            return this.index - o.index;
        }
    }
}
