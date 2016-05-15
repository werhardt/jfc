package controller;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.FileItem;
import model.FileItemListView;
import util.FileUtils;

public class Controller {

    @FXML
    private FileItemListView leftListView;

    @FXML
    private TextField rightTextField;

    @FXML
    private TextField leftTextField;

    @FXML
    private FileItemListView rightListView;


    public void initialize() {
        this.selectFirstRoot(this.leftListView);
        this.selectFirstRoot(this.rightListView);
        this.focus(this.leftListView);
    }

    @FXML
    public void buttonClick() {
////        Alert alert = new Alert(Alert.AlertType.INFORMATION, "alles cool", ButtonType.OK);
//        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//
//        alert.setHeaderText("blbl");
//        alert.setTitle("ttitltltltlt");
//        Optional<ButtonType> result = alert.showAndWait();
//        if(result.isPresent() && result.get() == ButtonType.OK) {
//
//            System.out.println("ok");
//        } else {
//            System.out.println("nicht ok");
//        }
//        NotificationPane notificationPane = new NotificationPane();
//        notificationPane.getStyleClass().add(NotificationPane.STYLE_CLASS_DARK);
//        notificationPane.show();

    }

//    private void showNotification(String title, String text) {
//        Notifications.create()
//                .title(title)
//                .text(text)
//                .showWarning();
//    }

//    @FXML
//    public void loadFiles() {
//
//        this.leftListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
//        this.showFiles(this.leftListView, FileUtils.listRoots());
//    }

    public void selectFirstRoot(FileItemListView listView) {

        this.showFiles(listView, FileUtils.getFirstRoot().getAbsolutePath());
    }

    private FileItemListView getOpposite(FileItemListView listView) {
        return listView == this.leftListView ? this.rightListView : this.leftListView;
    }


    @FXML
    public void navigateTextField(KeyEvent event) {

        if(event.getCode() == KeyCode.ENTER) {
            TextField textField = (TextField) event.getSource();
            this.showFiles(this.getListView(textField), textField.getText());
        }

    }

    @FXML
    public void navigateListView(KeyEvent event) {
        FileItemListView listView = (FileItemListView) event.getSource();
        ObservableList<FileItem> selectedItems = listView.getSelectionModel().getSelectedItems();

        if(event.getCode() == KeyCode.ENTER) {
            FileItem fileItem = null;

            if(selectedItems != null && selectedItems.size() > 0) {
                fileItem = selectedItems.get(0);
            } else {
                if(listView.getItems().size() > 0) {
                    fileItem = listView.getItems().get(0);
                }
            }

            if(fileItem != null && fileItem.isDirectory()) {
                this.showFiles(listView, fileItem.getAbsolutePath());
            }

        } else if(event.getCode() == KeyCode.BACK_SPACE) {
            this.showFiles(listView, FileUtils.getParentPath(listView.getCurrentPath()));
        } else if(event.getCode() == KeyCode.TAB) {
            this.focus(this.getOpposite(listView));
        } else if(event.isControlDown() && event.getCode() == KeyCode.L) {
            this.focus(this.getTextField(listView));
        } else if(this.isValidKeyCode(event.getCode())){
            TextField tf = this.getTextField(listView);
            tf.appendText(event.getText());
            // TODO   nach dem schreiben das textfield fokusieren, damit mit ENTER navigiert werden kann.

        }

    }

    private boolean isValidKeyCode(KeyCode code) {
        return code != KeyCode.UP && code != KeyCode.DOWN;
    }

    private void focus(Control control) {
        Platform.runLater(() -> control.requestFocus());
    }

//    private void showFilesLeft(String path) {
//        this.showFiles(this.leftListView, this.leftLabel, path);
//    }
//
//    private void showFilesRight(String path) {
//        this.showFiles(this.rightListView, this.rightLabel, path);
//    }

    private TextField getTextField(FileItemListView listView) {
        return listView == this.leftListView ? this.leftTextField : this.rightTextField;
    }

    private FileItemListView getListView(TextField textField) {
        return textField == this.leftTextField ? this.leftListView : this.rightListView;
    }

    private void showFiles(FileItemListView listView, String path) {
        listView.setCurrentPath(path);
        this.getTextField(listView).setText(path);

        ObservableList<FileItem> items = FXCollections.observableArrayList ();

        items.addAll(FileUtils.listFiles(path));

        listView.setItems(items);
    }

    @FXML
    public void close() {
        System.exit(0);
    }
}
