package util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class PinyinUtil {
    // 第一个参数表示要获取拼音的字符串
    // 第二个参数表示是否是全拼. 比如针对 "基础语法" 这样的字符串来说, 如果是第二个参数为 true, 得到 jichuyufa 结果.
    // 如果第二个参数为 false, 得到 jcyf 这个结果
    // 注意!!! 此处针对多音字, 不做过多考虑. 当前阶段, 很难做出适配.
    // 这个 Pinyin4j 即使是得到多音字, 数组元素的 0 号元素大概率也是最常用的发音了.
    // 此处针对多音字, 只取第一个发音, 作为最终结果了.
    // 要想得到更精确的 "多音字" 结果, 可以找一些 "分词" 库.
    public static String get(String src, boolean fullSpell) {
        // trim 效果是去掉字符串两侧的空白字符.
        // 例如有个字符串     hello \t  \n   => hello
        if (src == null || src.trim().length() == 0) {
            // 空的字符串, 无需多言
            return null;
        }

        // 针对 Pinyin4j 稍作配置, 让它输出的 yu 这个拼音, 能够使用 v 表示.
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setVCharType(HanyuPinyinVCharType.WITH_V);;

        // 遍历字符串的每个字符, 针对每个字符分别进行转换, 把转换得到拼音的结果, 拼接到 StringBuilder 里面
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < src.length(); i++) {
            char ch = src.charAt(i);
            // 针对单个字符进行转换
            String[] tmp = null;
            try {
                tmp = PinyinHelper.toHanyuPinyinStringArray(ch, format);
            } catch (BadHanyuPinyinOutputFormatCombination e) {
                e.printStackTrace();
            }
            if (tmp == null || tmp.length == 0) {
                // 如果结果是空的数组, 说明转换失败了.
                // 如果输入的字符, 没有汉语拼音, 自然就会转换失败!!!
                // 比如, 输入的字符, a, b, c, 1, 2, 3
                // 保留原始字符. 直接把原始字符加入结果中
                stringBuilder.append(ch);
            } else if (fullSpell) {
                // 拼音结果非空, 并且获取完整全拼.
                stringBuilder.append(tmp[0]);
            } else {
                // 拼音结果非空, 并且获取拼音首字母
                // 比如 "汤" => ["tang1", "shang1"], 此时取 0 号元素, 得到了 "tang1" , 再取 0 号字符, 得到 t
                stringBuilder.append(tmp[0].charAt(0));
            }
        }
        return stringBuilder.toString();
    }
}
