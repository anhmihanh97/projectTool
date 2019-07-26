
package vn.com.cmcglobal.du5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import vn.com.cmcglobal.du5.service.TestResultService;

@SpringBootApplication
public class AppTestResult implements CommandLineRunner {
	// private Desktop desktop = Desktop.getDesktop();
//	private ConfigurableApplicationContext springContext;
	// private String filename = "";
	// private String fileout = "";

	@Autowired
	private TestResultService resultService;

	public static void main(final String[] args) {
		SpringApplication app = new SpringApplication(AppTestResult.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);

		// Application.launch(args);
	}

//    @Override
//    public void init() throws Exception {
//        springContext = SpringApplication.run(AppTestResult.class);
//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Main.fxml"));
//        fxmlLoader.setControllerFactory(springContext::getBean);
//        rootNode = fxmlLoader.load();
//    }

	// @Override
//	public void start(Stage primaryStage) throws Exception {
//		final FileChooser fileChooser = new FileChooser();
//		DirectoryChooser directoryChooser = new DirectoryChooser();
//
//		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("excel file", "*.XLSX"),
//				new FileChooser.ExtensionFilter("excel file", "*.xlsx"));
//		fileChooser.setInitialDirectory(new File(Constants.DESKTOP + "\\Result"));
//		directoryChooser.setInitialDirectory(new File(Constants.DESKTOP + "\\Result"));
//
//		TextArea textAreaLog = new TextArea();
//		textAreaLog.setMinHeight(70);
//
//		Button btnSelectResult = new Button("Select testresult file");
//
//		Button btnOK = new Button("Start to review");
//
//		Label lbreportFileName = new Label("No testresult file!");
//		lbreportFileName.setMinWidth(300);
//
//		FlowPane flowPane = new FlowPane();
//		flowPane.setMargin(btnSelectResult, new Insets(20, 0, 20, 20));
//		flowPane.setMargin(btnOK, new Insets(20, 0, 20, 20));
//		flowPane.setHgap(25);
//
//		// Retrieving the observable list of the flow Pane
//		ObservableList list = flowPane.getChildren();
//
//		// Adding all the nodes to the flow pane
//		list.addAll(btnSelectResult, lbreportFileName, btnOK);
//
//		// flowPane.getChildren().addAll( btnDailyReport, btnResultFile,
//		// btnOK,textAreaLog);
//
//		Scene scene = new Scene(flowPane, 500, 300);
//
//		primaryStage.setTitle("Review test result tool");
//		primaryStage.setScene(scene);
//		primaryStage.show();
//
//		btnSelectResult.setOnAction(e -> {
//			File selectedFile = fileChooser.showOpenDialog(primaryStage);
//			if (selectedFile != null) {
//				resultService.setTestResultFile(selectedFile.getAbsolutePath());
//				resultService.setOutputFolder(selectedFile.getParent());
//				filename = selectedFile.getAbsolutePath();
//				lbreportFileName.setText(selectedFile.getAbsolutePath());
//			}
//
//		});
//
////		btnSelectOutputFolder.setOnAction(e -> {
////			File selectedDirectory = directoryChooser.showDialog(primaryStage);
////			if (selectedDirectory == null) {
////				// No Directory selected
////			} else {
////				resultService.setOutputFolder(selectedDirectory.getAbsolutePath());
////				fileout = selectedDirectory.getAbsolutePath() + "\\" + NameColumn.NAME_FILE_OUT;
////				lbResultFile.setText(selectedDirectory.getAbsolutePath());
////			}
////
////		});
//
//		btnOK.setOnAction(new EventHandler<ActionEvent>() {
//			@Override
//			public void handle(ActionEvent event) {
//				try {
//					try {
//						resultService.writeWrongResult();
//					} catch (InvalidFormatException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					} catch (IOException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//				} catch (EncryptedDocumentException e) {
//					e.printStackTrace();
//
//				}
//			}
//		});
//	}

	// @Override
//	public void stop() throws Exception {
//		springContext.close();
//	}

//	private void openFile(File file) {
//		try {
//			this.desktop.open(file);
//		} catch (IOException e) {`
//			e.printStackTrace();
//		}
//	}

//	public void showInfoMsg(String title, String message) {
//		Alert alert = new Alert(AlertType.INFORMATION);
//		alert.setTitle(title);
//		alert.setHeaderText(null);
//		alert.setContentText(message);
//		alert.showAndWait();
//	}

	@Override
	public void run(String... args) throws Exception {
		resultService.setTestResultFile(
				Constants.DESKTOP + "\\Result\\【H820PF】システムテスト_Hands_Free_Telephone_Function_保証型_00_テスト成績書.xlsm");
		resultService.setOutputFolder(Constants.DESKTOP + "\\Result");
		resultService.writeWrongResult();
		// exit(0);
	}

}
