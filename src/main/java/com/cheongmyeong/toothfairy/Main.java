package com.cheongmyeong.toothfairy;

import com.cheongmyeong.toothfairy.config.StageManager;
import com.cheongmyeong.toothfairy.views.FxmlView;
import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

/**
 *
 * @author Sapo
 */
@SpringBootApplication
public class Main extends Application {
    
    protected StageManager stageManager;
    protected ConfigurableApplicationContext springContext;
     
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void init() throws Exception {
        springContext = springBootApplicationContext();
    }

    @Override
    public void start(Stage stage) throws Exception {
        stageManager = springContext.getBean(StageManager.class, stage);
        displayInitialScene();
    }

    @Override
    public void stop() throws Exception {
        springContext.close();
    }

    protected void displayInitialScene() {
        stageManager.switchScene(FxmlView.LOGIN);
    }

    private ConfigurableApplicationContext springBootApplicationContext() {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(Main.class);
        String[] args = getParameters().getRaw().stream().toArray(String[]::new);
        return builder.run(args);
    }
    
}
