package com.konxiang.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{

    public static void main( String[] args ) throws InterruptedException {
        Thread.sleep(30000);
        fillHeap(3000);
        System.out.println( "Hello World!" );
    }

    static class OOMObject{
        // 64kb
        public  byte[] placeholder = new byte[64 *1024];
    }

    public  static void fillHeap(int num ) throws InterruptedException{
        List<OOMObject> list =new ArrayList<OOMObject>();
        for(int i=0 ;i<num;i++){
            Thread.sleep(50);
            list.add(new OOMObject());
        }
        System.gc();
    }
}
