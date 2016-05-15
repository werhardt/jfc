package model;

import javafx.scene.control.ListView;

/**
 * Created by werhardt on 10.05.16.
 */
public class FileItemListView extends ListView<FileItem> {
    private String currentPath;

    public String getCurrentPath() {
        return currentPath;
    }

    public void setCurrentPath(String currentPath) {
        this.currentPath = currentPath;
    }
}
