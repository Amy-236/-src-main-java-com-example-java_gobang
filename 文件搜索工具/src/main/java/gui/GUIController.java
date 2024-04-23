package gui;

import dao.FileMeta;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Window;
import service.SearchService;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class GUIController implements Initializable {
    @FXML
    private GridPane gridPane;

    @FXML
    private Button button;

    @FXML
    private Label label;

    @FXML
    private TextField textField;

    @FXML
    private TableView<FileMeta> tableView;

    // Controller 里面的属性不一定非得是界面上的元素.
    private SearchService searchService = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // 在这里给输入框加上一个监听器.
        // 需要指定, 是针对 text 这个内容属性来进行监听.
        textField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                // 这个方法, 就会在每次用户修改输入框内容的时候, 被自动调用到.
                // oldValue 输入框被改之前, 旧的值是啥.
                // newValue 输入框改完之后, 新的值是啥.
                // 此处要干的事情, 根据新的这个值, 重新进行查询操作.
                freshTable(newValue);
            }
        });
    }

    private void freshTable(String query) {
        // 重新查询数据库, 把查询结果, 设置到表格中.
        // 查询操作, 依赖这个 SearchService 对象的.
        if (searchService == null) {
            System.out.println("searchService 尚未初始化, 不能查询!");
            return;
        }
        // 先把之前表格中旧的数据给干掉. 需要拿到 tableView 内部的集合类.
        ObservableList<FileMeta> fileMetas = tableView.getItems();
        // 清空之前的内容.
        fileMetas.clear();
        List<FileMeta> results = searchService.search(query);
        // 把查询结果添加到 TableView 之中.
        fileMetas.addAll(results);
    }

    // 使用这个方法, 作为鼠标点击事件的回调方法. 这个方法里需要有一个 MouseEvent 参数.
    // 当前可能用不到这个参数. 但是定义方法的时候还是得有这个参数的.
    public void choose(MouseEvent mouseEvent) {
        // System.out.println("点击按钮");
        // 先创建出一个 目录选择器对象
        DirectoryChooser directoryChooser = new DirectoryChooser();
        // 还需要把这个对话框显示出来.
        Window window = gridPane.getScene().getWindow();
        File file = directoryChooser.showDialog(window);
        if (file == null) {
            System.out.println("当前用户选择的路径为 空 ");
            return;
        }
        System.out.println(file.getAbsolutePath());

        // 让用户选择的路径, 显示到 label 标签中.
        label.setText(file.getAbsolutePath());

        // 判定一下当前 searchService 是否是 null . 如果非空, 说明现在不是程序首次扫描
        // 就应该停止上次的扫描任务.
        if (searchService != null) {
            searchService.shutdown();
        }
        // 让用户选择路径, 初心是希望程序对指定路径进行扫描. 把对应的数据加入到数据库中.
        searchService = new SearchService();
        searchService.init(file.getAbsolutePath());
    }
}
