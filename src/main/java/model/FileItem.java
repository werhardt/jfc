package model;

import java.io.File;

/**
 * Created by werhardt on 10.05.16.
 */
public class FileItem {

    private String absolutePath;
    private String name;
    private boolean isFile;
    private boolean isDirectory;
    private String parentPath;

    public static FileItem build(File file) {
        FileItem item = new FileItem();

        item.absolutePath = file.getAbsolutePath();
        item.isDirectory = file.isDirectory();
        item.isFile = file.isFile();
        item.parentPath = file.getParent();
        item.name = file.getName();

        return item;
    }

    public String getAbsolutePath() {
        return absolutePath;
    }

    public void setAbsolutePath(String absolutePath) {
        this.absolutePath = absolutePath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isFile() {
        return isFile;
    }

    public void setFile(boolean file) {
        isFile = file;
    }

    public boolean isDirectory() {
        return isDirectory;
    }

    public void setDirectory(boolean directory) {
        isDirectory = directory;
    }

    public String getParentPath() {
        return parentPath;
    }

    public void setParentPath(String parentPath) {
        this.parentPath = parentPath;
    }

    @Override
    public String toString() {
        return (this.isDirectory ? " dir" : "file") + " | " + this.getName();
//        return "FileItem{" +
//                "absolutePath='" + absolutePath + '\'' +
//                ", name='" + name + '\'' +
//                ", isFile=" + isFile +
//                ", isDirectory=" + isDirectory +
//                ", parentPath='" + parentPath + '\'' +
//                '}';
    }
}
