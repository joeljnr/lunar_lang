package lunarcomp.ui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import lunarcomp.Erro;
import lunarcomp.ID;
import lunarcomp.Lex;
import static lunarcomp.LunarComp._erros;
import lunarcomp.Syntax;

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
        if(!txEditor.getText().isEmpty()) {
            l.lerLexemas(txEditor.getText());

            l.criarTokens();

            Syntax s = new Syntax(l.getTokens(), 0);
            /*
            for(int i = 0; i < l.getTokens().size(); i++) {
                System.out.println("Token: " + l.getTokens().get(i).getToken());
                System.out.println("Lexema: " + l.getTokens().get(i).getLex());
                System.out.println("Linha: " + l.getTokens().get(i).getLinha());
                System.out.println();
            }
            */
            
            if(s.launch().isAceito() && _erros.size() == 0) {
                txOutput.setText(txOutput.getText() + "ACEITO\n");
                for(int i = _erros.size()-1 ; i >= 0; i--) {
                    txOutput.setText(txOutput.getText() + _erros.get(i).exibeErro());
                }
            } else {
                txOutput.setText("REJEITADO\n");
                //System.out.println(_erros.size());
                for(int i = _erros.size()-1 ; i >= 0; i--) {
                    txOutput.setText(txOutput.getText() + _erros.get(i).exibeErro());
                }
            }
            
            ArrayList<ID> ids = s.getIdtable();
            
            s.exibeIdTable();
            
            _erros.clear();
        } else {
            txOutput.setText("Launch não encontrado!\n");
        }
    }
    
}
