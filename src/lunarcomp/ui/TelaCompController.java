package lunarcomp.ui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import lunarcomp.Lex;

public class TelaCompController implements Initializable {

    @FXML
    private TextArea txEditor;
    @FXML
    private TextArea txOutput;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void evtAbrir(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selecione o arquivo");
        Window stage = null;
        String selectedFile = fileChooser.showOpenDialog(stage).toString();
        
        txEditor.setText("");
        
        try 
        {
            FileReader arq = new FileReader(selectedFile);
            BufferedReader lerArq = new BufferedReader(arq);

            String linha = lerArq.readLine();
            while (linha != null) {
                txEditor.setText(txEditor.getText() + linha + "\n");
                linha = lerArq.readLine(); // lê da segunda até a última linha
                
            }
            arq.close();
        } 
        catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
        }
        
    }

    @FXML
    private void evtSalvar(ActionEvent event) {
        
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Salvar");
        dialog.setHeaderText("Salvar arquivo");
        dialog.setContentText("Entre o nome do arquivo");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            BufferedWriter writer = null;
            try {
                
                File arquivo = new File(result.get() + ".txt");

                writer = new BufferedWriter(new FileWriter(result.get() + ".txt"));
                writer.write(txEditor.getText());
                
            } catch (Exception e) {
                txOutput.setText("Erro ao salvar o arquivo!\n");
            } finally {
                try {
                    writer.close();
                } catch (Exception e) {}
            }
        }
    }

    @FXML
    private void evtNovo(ActionEvent event) {
        Alert a = new Alert(AlertType.CONFIRMATION, "Os dados no editor não serão salvos. Tem certeza que deseja começar um novo código?");
        a.setHeaderText("");
        Optional<ButtonType> result = a.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            txEditor.setText("");
        }
    }
    
    @FXML
    private void evtExecutar(ActionEvent event) {
        txOutput.setText("");
        Lex l = new Lex();
        l.ler_lexemas(txEditor.getText());
        l.criar_tokens();
        for(int i = 0; i < l.getTokens().size(); i++) {
            txOutput.setText(txOutput.getText() + "\nToken: " + l.getTokens().get(i).getToken() + "\nLex: " + l.getTokens().get(i).getLex() + "\nLinha: " + l.getTokens().get(i).getLinha() + "\n");
        }
       
    }
    
}
