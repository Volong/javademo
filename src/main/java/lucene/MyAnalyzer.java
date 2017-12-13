package lucene;

import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.tokenattributes.PositionIncrementAttribute;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyAnalyzer extends Analyzer {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyAnalyzer.class);
    
    @Override
    protected TokenStreamComponents createComponents(String fieldName) {
        final Tokenizer source = new MyTokenizer();
        return new TokenStreamComponents(source);
    }

    public static void main(String[] args) throws IOException {
        
        Analyzer analyzer = new MyAnalyzer();
        TokenStream tokenStream = analyzer.tokenStream("text", "杨尚川是，APDPlat应用级产品开发平台的作者");
        tokenStream.reset();
        while(tokenStream.incrementToken()){
            CharTermAttribute charTermAttribute = tokenStream.getAttribute(CharTermAttribute.class);
            OffsetAttribute offsetAttribute = tokenStream.getAttribute(OffsetAttribute.class);
            PositionIncrementAttribute positionIncrementAttribute = tokenStream.getAttribute(PositionIncrementAttribute.class);
            LOGGER.info(charTermAttribute.toString()+" ("+offsetAttribute.startOffset()+" - "+offsetAttribute.endOffset()+") "+positionIncrementAttribute.getPositionIncrement());
        }
        tokenStream.close();
        analyzer.close();
    }
}
