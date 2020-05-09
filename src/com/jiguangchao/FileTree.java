package com.jiguangchao;

import java.io.*;


public class FileTree {
    private File root;
    private String[] lines = {"└─ ","├─ ","│"};
    private char indentChar = ' ';
    private int indentNum = 4;
    private String[] ignores = {".git", ".idea", ".gitignore"};

    public FileTree(File root) {
        this.root = root;
    }

    public FileTree(String rootName) {
        this.root = new File(rootName);
    }

    public FileTree() {
        this(".");
    }

    public void show() {
        this.showTree(this.root, 0, 0, 0);
    }

    public void xcopy() {

    }

    private boolean copy(String source, String target) {
        byte[] buff = new byte[1024];
        try(
                var fis = new FileInputStream(source);
                var fos = new FileOutputStream(target))
        {
            while(fis.read(buff) != -1)
                fos.write(buff);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private class filter implements FilenameFilter {
        @Override
        public boolean accept(File dir, String name) {
            boolean ret = true;
            for (String ignore: ignores) {
                if(name.endsWith(ignore)) {
                    ret = false;
                    break;
                }
            }
            return ret;
        }
    }
/*
package_sample
└─ src
    ├─ hong
    │  └─ Person.java
    │  ming
    │  └─ Person.java
    └─ mr
       └─ jun
          └─ Arrays.java
 */
    // depth >=2 的时候才会调用该方法
    // entry = .name   index = 1   depth =2
    private boolean isVerticalLine(File entry, int depthIndex, int depth) {
        // 回溯找到当前节点指定depthIndex的祖先
         int i = depth;
         File parent = entry;
         while (i > depthIndex) {
             parent = parent.getParentFile();
             i--;
         }
        // 判断parent是否是最后一个节点（是否还有兄弟节点）这里不是很严谨，因为不能保证每次listFiles的顺序。后续再改进
         File[] children = parent.getParentFile().listFiles(new filter());
        // 如果parent为父节点的最后一个节点，则返回false,否则返回true
         return  !parent.equals(children[children.length -1]);
    }

    private void showTree(File entry, int depth, int order, int brothers) {
        String name = entry.getAbsolutePath();
        if (depth > 0) {
            //根据当前节点的深度，打印相应数量的空格进行缩进
            for (int i = 1; i < depth; i++) {
                int num = this.indentNum;
                // 判断是否打印竖线。这里好像比较复杂，应该进行回溯，单独编写个方法。
                // if (isVerticalLine(entry, i, depth))
                if (isVerticalLine(entry, i, depth)) {
                    System.out.print(this.lines[2]);
                    num--;
                }
                for (int j = 0; j < num; j++) {
                    System.out.print(indentChar);
                }
            }
            //根据当前节点是否是最后一个节点，打印"└─ "或者"├─ "
            System.out.print(lines[brothers - order == 0 ? 0: 1]);
            //根据当前节点的深度，调整打印名字，而不是绝对路径
            name = entry.getName();
        }
        System.out.println(name);
        if(entry.isDirectory()) {
            File[] children = entry.listFiles(new filter());
            for (int i = 0; i < children.length; i++) {
                showTree(children[i], depth + 1, i, children.length - 1);
            }
        }
    }
}
