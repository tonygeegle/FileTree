package com.jiguangchao;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class Main {

    public static void main(String[] args) throws IOException {
        //        File file = new File("D:/java");
//        System.out.println(file.isDirectory());
//        file = new File("D:/java/Java.iml");
//        System.out.println(file.isDirectory());

//        File newFile = new File("D:/testFileCreate");
//        // 如果文件已经存在，就不会新建
//        System.out.println(newFile.createNewFile());
//        File newDir = new File("D:/testDirCreate");
//        // 如果文件已经存在，就不会新建
//        System.out.println(newDir.mkdir());
//        File newDirs = new File("D:/testDirs/1/2/3");
//        // 如果文件已经存在，就不会新建
//        System.out.println(newDirs.mkdirs());
//        File newDirss = new File("D:/testDirss/1/2/3/");
//        // 如果文件已经存在，就不会新建
//        System.out.println(newDirss.mkdirs());
//
//        File relaFile = new File("test");
//        System.out.println(Path.of(relaFile.getAbsolutePath(), "testaaa", "bbb","ccc"));

//        FileTree ft;
//        ft = new FileTree();
//        ft.show();
        var ft = new FileTree("D:\\Java");
        ft.show();
        ft.copyTo("D:\\copyTest3");
        new FileTree("D:\\copyTest3").show();
    }
}
