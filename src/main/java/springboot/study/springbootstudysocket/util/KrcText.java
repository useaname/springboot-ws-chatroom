package springboot.study.springbootstudysocket.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static org.apache.coyote.http11.Constants.a;

public class KrcText {
    private static final char[] miarry = {'@', 'G', 'a', 'w', '^', '2', 't',
            'G', 'Q', '6', '1', '-', 'Î', 'Ò', 'n', 'i'};

    public static void main(String[] args) throws IOException {
        String filenm = "D:\\test\\springboot-ws-chatroom\\will_better.krc";//krc文件的全路径加文件名
        String krcText = new KrcText().getKrcText(filenm);

        System.out.println(new KrcText().getKrcText(krcText));
        String[] split = krcText.split("\n");
        String s = "[76338,3705]<0,317,0>倾<317,866,0>诉<1183,148,0>遥<1331,206,0>远<1537,150,0>的<1687,332,0>祝<2019,1686,0>福";

        String[] a1 = s.substring(1, s.indexOf("]")).split(",");

        String b = s.substring(s.indexOf("]") + 1);

        String[] a2 = b.split("<\\d*,\\d*,\\d*>");
//        String reg = "/^<d*,d*,d*>$/";

        for (int i = 0; i < a2.length; i++) {
            System.out.println(a2[i]);
        }

        System.out.println(s);
        System.out.println("句开始时间 ======》" + a1[0]);
        System.out.println("句持续时间 ======》" + a1[1]);
        System.out.println(b);

        //        for (int i = 0; i < split.length; i++) {
//            System.out.println(split[10]);
//            System.out.println("----------------------------");
//        }
    }

    /**
     * @param filenm krc文件路径加文件名
     * @return krc文件处理后的文本
     * @throws IOException
     */
    public String getKrcText(String filenm) throws IOException {
        File krcfile = new File(filenm);
        byte[] zip_byte = new byte[(int) krcfile.length()];
        FileInputStream fileinstrm = new FileInputStream(krcfile);
        byte[] top = new byte[4];
        fileinstrm.read(top);
        fileinstrm.read(zip_byte);
        int j = zip_byte.length;
        for (int k = 0; k < j; k++) {
            int l = k % 16;
            int tmp67_65 = k;
            byte[] tmp67_64 = zip_byte;
            tmp67_64[tmp67_65] = (byte) (tmp67_64[tmp67_65] ^ miarry[l]);
        }
        String krc_text = new String(ZLibUtils.decompress(zip_byte), "utf-8");
        return krc_text;
    }
}
