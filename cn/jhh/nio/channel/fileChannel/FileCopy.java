package cn.jhh.nio.channel.fileChannel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author Jhh
 */
public class FileCopy {

    /**
     * 1.创建文件输入流，获取inChannel
     * 2.创建buffer
     * 3.读取数据，往buffer写数据
     * 4.flip
     * 5.创建输出流，获取outChannel
     * 6.往输出流写数据，即从buffer中读数据（先flip）
     */
    public static void test(){
        String file = "cn/jhh/nio/channel/fileChannel/a.txt";
        String des = "cn/jhh/nio/channel/fileChannel/b.txt";

        FileInputStream fis = null;
        FileOutputStream fos = null;
        FileChannel inChannel = null;
        FileChannel outChannel = null;

        try {
            fis = new FileInputStream(file);
            fos = new FileOutputStream(des);
            inChannel = fis.getChannel();
            outChannel = fos.getChannel();

            ByteBuffer buffer = ByteBuffer.allocate(12);
            int length = -1;
            while((length = inChannel.read(buffer) )!= -1){
                buffer.flip();
                System.out.println("读入的字节数："+length);
                int writeLen = 0;

                while((writeLen = outChannel.write(buffer))!=0){
                    System.out.println("写入的字节数"+writeLen);
                }
                //buffer读->写要 clear
                buffer.clear();

            }
            outChannel.force(true);
            System.out.println("finished");
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            if(fis!=null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(fos!=null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(inChannel!=null){
                try {
                    inChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(outChannel!=null){
                try {
                    outChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
    /**
     * 
     * @param args
     */
    public static void main(String[] args) {
        test();
    }
}
