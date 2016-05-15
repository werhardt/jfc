package util;

import model.FileItem;

import java.io.File;
import java.util.*;

public class FileUtils {

    public static List<FileItem> listRoots() {

        List<FileItem> items = new ArrayList<>();
        for(File f : File.listRoots()) {
            items.add(FileItem.build(f));
        }

        return items;
    }

    public static FileItem getFirstRoot() {
        return listRoots().get(0);
    }

    public static List<FileItem> listFiles(FileItem fileItem) {
        return listFiles(fileItem.getAbsolutePath());
    }

    public static List<FileItem> listFiles(String path) {
        File file = new File(path);
        List<FileItem> items = new ArrayList<>();
        if(file.exists()) {
            File[] files = file.listFiles();

            for(File f : files) {
                items.add(FileItem.build(f));
            }


            sort(items);

            return items;
        }
        return null;
    }

    public static void sort(List<FileItem> items) {
        Collections.sort(items, (FileItem a, FileItem b) -> {
            if(a.isDirectory() && b.isFile()) {
                return -1;
            } else if(a.isFile() && b.isDirectory()) {
                return 1;
            }
            if (!a.getName().equals(b.getName())) {
                return a.getName().compareToIgnoreCase(b.getName());
            }
            return 0;
        });
    }

    public static String getParentPath(FileItem fileItem) {
        return getParentPath(fileItem.getAbsolutePath());
    }

    public static String getParentPath(String path) {
        path = path.substring(0, path.lastIndexOf("/"));
        if("".equals(path)) {
            return "/";
        }
        return path;
    }

    // windows shortcuts handling
    // http://stackoverflow.com/questions/309495/windows-shortcut-lnk-parser-in-java/25501224#25501224
}
