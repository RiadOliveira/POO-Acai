package controller.screens;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;

import javax.swing.JFileChooser;

import com.itextpdf.text.DocumentException;

import controller.DashboardPagesRedirect;
import errors.ValidationException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import model.BO.OrderBO;
import utils.ReportType;
import view.ScreenLoader;

public class ReportsScreen extends DashboardPagesRedirect {
    @FXML private DatePicker date;
    @FXML private ComboBox<ReportType> reportType;

    @FXML private VBox openReportContainer;
    @FXML private Button openReportButton;

    @FXML private Label adminName;

    private String directoryPath;

    public void initialize() {
		adminName.setText(admin.getName());

        ObservableList<ReportType> reportTypes = FXCollections.observableArrayList();
        reportTypes.addAll(ReportType.values());

        reportType.setItems(reportTypes);
    }

    public void generateReport() {
        try {
            directoryPath = chooseGenerateReportFolder() + "/report.pdf";

            OrderBO.generateReport(directoryPath, reportType.getValue(), date.getValue());

            openReportContainer.setStyle(openReportContainer.getStyle() + "-fx-opacity: 1;");
            openReportButton.setStyle(openReportButton.getStyle() + "-fx-cursor: CLOSED_HAND;");
        } catch (FileNotFoundException | DocumentException | SQLException | ValidationException err) {
            System.out.println(err.getMessage());
        }
    }

    public void openReport() {
        File report = new File(directoryPath);

        if(report.exists()) {
            ScreenLoader screen = new ScreenLoader();
            screen.getHostServices().showDocument(report.toURI().toString());
        }
    }

    public static String chooseGenerateReportFolder() {
        JFileChooser chooser;

        chooser = new JFileChooser(); 
        chooser.setDialogTitle("Selecione o local para salvar o PDF");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        chooser.setAcceptAllFileFilterUsed(false);

        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) { 
            return chooser.getCurrentDirectory().getPath();
        } else {
            return null;
        }
    }
}
