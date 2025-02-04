package com.admin.projeto_tabulando.controller;

import com.admin.projeto_tabulando.Application;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;



public class BuscarJogoController {
    @FXML
    private ComboBox matricula;
    @FXML
    private TextField nome;
    @FXML
    private TextField cpf;
    @FXML
    private DatePicker data;
    @FXML
    private ImageView foto;

    File file;
    @FXML

    public void onFotoClicked(){
        FileChooser fc = new FileChooser();
        file = fc.showOpenDialog(Application.getScene().getWindow());
        if(file!=null){
            foto.setImage(new Image(file.getAbsolutePath()));
        }
    }
    @FXML
    public void onAtualizarClicked(){}

    @FXML
    public void onBuscarClicked(){

    }

    @FXML
    public void onDeletarClicked(){
    }

}

