package lunarcomp.ui;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Window;

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
            System.err.printf("Erro na abertura do arquivo: %s.\n",
            e.getMessage());
        }
        
    }

    @FXML
    private void evtSalvar(ActionEvent event) {
    }

    @FXML
    private void evtNovo(ActionEvent event) {
    }

    @FXML
    private void evtExecutar(ActionEvent event) {
    }
    
}
