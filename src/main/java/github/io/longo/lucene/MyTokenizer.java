package github.io.longo.lucene;

import java.io.BufferedReader;
import java.io.IOException;

import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

public class MyTokenizer extends Tokenizer {

    private CharTermAttribute charTerm = addAttribute(CharTermAttribute.class);
    
    @Override
    public boolean incrementToken() throws IOException {
        
        BufferedReader reader = new BufferedReader(input);
        String str = null;
        while ((str = reader.readLine()) != null) {
            String[] split = str.split("");
            for (String s : split) {
                charTerm.append(s).append('.');
            }
            return true;
        }
        
        return false;
    }

}
