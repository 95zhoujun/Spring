/**
 * @author zhoujun
 * @date 2020-08-03 16:28
 */

import org.junit.jupiter.api.Test;

import java.nio.ByteBuffer;

/**
 * 一、缓冲区（Buffer）：在Java NIO中负责数据的存取。缓冲区就是数组。用于存储不同数据类型的数据。
 *
 * 根据数据类型不同（Boolean除外），提供了相应类型的缓冲区：
 * ByteBuffer
 * CharBuffer
 * ShortBuffer
 * IntBuffer
 * LongBuffer
 * FloatBuffer
 * DoubleBuffer
 *
 * 上述缓冲区的管理方式几乎一致，通过allocate()获取缓冲区
 *
 * 二、缓冲区存取数据的两个核心方法：
 * put():存入数据到缓存区中
 * get():获取缓冲区中的数据
 *
 * 四、缓冲区的四个核心属性：
 * capacity:容量，表示缓冲区中最大存储数据的容量。一旦声明不能改变。
 * limit:界限，表示缓冲区可以操作数据的大小。（limit 后数据不能进行读写）
 * position：位置，表示缓冲区中正在操作数据的位置。
 *
 * mark:标记，表示记录当前position的位置。可以通过reset()恢复到mark的位置
 *
 * 0<=mack <= position <=limit<=capacity
 */
public class TestBuffer {

    @Test
    public void test2(){
        String str = "abdce";
        ByteBuffer buf = ByteBuffer.allocate(1024);
        buf.put(str.getBytes());
        buf.flip();
        byte[] dst = new byte[buf.limit()];
        buf.get(dst,0,2);
        System.out.println(buf.position());

        //mack():标记
        buf.mark();

        buf.get(dst,2,2);
        System.out.println(new String(dst,2,2));
        System.out.println(buf.position());

        //reset(),恢复到mack地址
        buf.reset();
        System.out.println(buf.position());

        //判断缓冲区中是否还有剩余数据
        if(buf.hasRemaining()){
            //获取缓冲区中可以操作的数据
            System.out.println(buf.remaining());
        }
    }

    @Test
    public void test1(){
        String str = "abcde";

        //1.分配一个指定大小的缓冲区
        ByteBuffer buf = ByteBuffer.allocate(1024);

        System.out.println("--------------------allocate()-------------------");
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());

        //2.利用put()存入数据到缓冲区中
        buf.put(str.getBytes());

        System.out.println("--------------------put()-------------------");
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());

        //3.切换读取数据模式
        buf.flip();
        System.out.println("--------------------flip()-------------------");
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());

        //4.利用get()读取缓冲区的数据
        byte[] dst = new byte[buf.limit()];
        buf.get(dst);
        System.out.println(new String(dst,0,dst.length));
        System.out.println("--------------------get()-------------------");
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());

        //5.rewind():可重复读
        buf.rewind();
        System.out.println("--------------------rewind()-------------------");
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());

        //6.clear():清空缓冲区，但是缓冲区的数据依然存在，但是处于“被遗忘”状态
        buf.clear();
        System.out.println("--------------------clear()-------------------");
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());

    }
}
