import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.util.Arrays;

public class TestPinyin4j {
    public static void main(String[] args) throws BadHanyuPinyinOutputFormatCombination {
        // 这个方法的参数, 是一个字符 (一个汉字)
        // 返回值, 是一个 String[], 由于汉字里面存在多音字. 这里就是把所有可能的读音, 都列出来了.
        String[] result = PinyinHelper.toHanyuPinyinStringArray('绿');
        System.out.println(Arrays.toString(result));
    }
}
