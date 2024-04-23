package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GUIClient extends Application {

    // 这个 start 属于程序启动的时候, 就会立即执行的方法. 通过这个方法进行程序的初始化操作.
    @Override
    public void start(Stage primaryStage) throws Exception {
        // 加载 fxml 文件, 把 fxml 文件里的内容, 给设置到舞台中.
        Parent parent = FXMLLoader.load(GUIClient.class.getClassLoader().getResource("app.fxml"));
        primaryStage.setScene(new Scene(parent, 1000, 800));
        primaryStage.setTitle("文件搜索工具");
        // 帷幕拉开. 把场景显示出来.
        primaryStage.show();
    }

    public static void main(String[] args) {
        // 调用 javafx 提供的 launch 方法来启动整个程序
        launch(args);
    }
}
