package github.io.volong.decorator;

import java.io.BufferedInputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 装饰器模式
 * http://www.cnblogs.com/god_bless_you/archive/2010/06/10/1755212.html
 * @time 2017年12月13日 下午3:04:25
 * @author Volong
 *
 */
public class LowerCaseInputStream extends FilterInputStream {

    protected LowerCaseInputStream(InputStream in) {
        super(in);
    }
    
    @Override
    public int read() throws IOException {
        
        int c = super.read();
        
        return c < 0 ? c : Character.toLowerCase(c);
    }
    
    public static void main(String[] args) throws IOException {
       
        InputStream input = LowerCaseInputStream.class.getResourceAsStream("decorator");
        LowerCaseInputStream lowerCaseInputStream = new LowerCaseInputStream(new BufferedInputStream(input));
        
        int c;
        while ((c = lowerCaseInputStream.read()) >= 0) {
            System.out.print((char)c);
        }
        
        lowerCaseInputStream.close();
    }

}
