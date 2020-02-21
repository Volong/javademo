package github.io.longo.j8fp.chapter04;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.IntSummaryStatistics;
import java.util.Optional;

import github.io.longo.j8fp.Album;
import github.io.longo.j8fp.SampleData;
import github.io.longo.j8fp.Track;

public class Example {

    public static void main(String[] args) {

        // summaryStatistics
        printTrackLengthStatistics(SampleData.manyTrackAlbum);

        // 创建某个值的 Optional 对象
        Optional<String> of = Optional.of("of");
        String s = of.get();

        // 创建一个空的 Optional 对象，并检查其是否有值
        Optional emptyOptional = Optional.empty();
        Optional alsoEmpty = Optional.ofNullable(null); // 将一个空值转换成Optional 对象
        assertFalse(emptyOptional.isPresent()); // 判断一个 Optional 对象里是否有值
        assertTrue(of.isPresent());

        // orElse : 当 Optional 对象为空时，提供一个备选值
        assertEquals("b", emptyOptional.orElse("b"));

        emptyOptional = null;
        // orElseGet
        assertEquals("c", emptyOptional.orElseGet(() -> "c"));

    }

    public static void printTrackLengthStatistics(Album album) {
        IntSummaryStatistics summaryStatistics = album.getTracks()
                .mapToInt(Track::getLength)
                .summaryStatistics();

        System.out.printf("Max: %d, Min: %d, Avg: %f, Sum: %d",
                summaryStatistics.getMax(), summaryStatistics.getMin(), summaryStatistics.getAverage(), summaryStatistics.getSum());

        System.out.println();
    }
}
