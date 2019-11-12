package tech.qijin.examples.practice.test;

import com.google.common.hash.Hashing;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author michealyang
 * @date 2019-11-06
 * @relax: 开始眼保健操 ←_← ↓_↓ →_→ ↑_↑
 */
public class Test {
    @org.junit.Test
    public void consistentHash() throws IOException {
        File filename = new File("/Users/yangshangqiang/Downloads/hash.txt");
        filename.createNewFile();
        BufferedWriter out = new BufferedWriter(new FileWriter(filename));
        for (int i = 0; i <= 10000000; i++) {
            int hash = Hashing.consistentHash(i, 512);
            out.write(hash + "\n");
            out.flush();
        }
        out.close();
    }
}
