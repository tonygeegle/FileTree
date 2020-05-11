package com.jiguangchao;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;

@Deprecated
class SortedFile extends File {
    public SortedFile(String pathname) {
        super(pathname);
    }

    @Override
    public File[] listFiles(FilenameFilter filter) {
        ArrayList<File> dirs = new ArrayList<>();
        ArrayList<File> files = new ArrayList<>();
        File[] fileLists = super.listFiles(filter);

        for (File f:fileLists) {
            if (f.isDirectory()) {
                dirs.add(f);
            } else {
                files.add(f);
            }
        }
        dirs.addAll(files);
        return dirs.toArray(new File[fileLists.length]);
    }

    @Override
    public File getParentFile() {
        return super.getParentFile();
    }
}
