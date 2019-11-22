/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carrega_imagem;

import importacaoimagens.view.RecorteImagem;
import importacaoimagens.view.VisualizarImagem;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JProgressBar;

/**
 *
 * @author katia
 */
public class ProgressoController {

    static List lista = new ArrayList();

    public List getLista() {
        return lista;
    }

    public void setLista(List lista) {
        this.lista = lista;
    }

    @SuppressWarnings("empty-statement")
    public void percorrerImagens(String caminho, JProgressBar barra) throws IOException {

        System.out.println("entrou carrega_imagem.ProgressoController -> percorrerImagens " + caminho);

        File diretorio = new File(caminho);
        File[] arquivos = diretorio.listFiles();

        int totalArquivos = arquivos.length;
        

        barra.setMinimum(0);        
        barra.setMaximum(totalArquivos);
        barra.setStringPainted(true);
        
        DesenharImagem di = new DesenharImagem();
        RecorteImagem ri = new RecorteImagem();
        TratarImagem ti = new TratarImagem();

        List aux = new ArrayList();

        for (int i = 0; i < totalArquivos; i++) {
           
            aux.add(arquivos[i]);
                          
            System.out.println("arquivo  " + i + " de " + totalArquivos);

            barra.setValue(i);
 
            for (long espera = 0; espera < 10000000; espera++);
        }
        setLista(aux);

    }

    public String[] decodeBarCodes(String fileName){
        try {
            String comando = "zbarimg "+fileName;
            Runtime rt = Runtime.getRuntime();
            Process pr = rt.exec(comando);
            BufferedReader br = new BufferedReader(new InputStreamReader(pr.getInputStream()));
            String linha=null;
            List<String> codigos = new ArrayList<String>();
            while( (linha=br.readLine()) != null ){
                if(!linha.startsWith("scanned"))
                    codigos.add(linha);
            }
            String[] retorno=new String[codigos.size()];
            for(int k=0;k<codigos.size();k++){
                retorno[k] = new String(codigos.get(k));
            }
            return retorno;
        } catch (IOException ex) {
            
            return null;
        }
    }    
    
    public void listarArquivos(JList jListListaDeArquivos, JLabel jLabel1) {

        System.out.println("entrou em ProgressoController -> listarArquivos: ");

        DefaultListModel model;
        model = new DefaultListModel();
        jListListaDeArquivos.setModel(model);

        System.out.println("imprimindo arrayList : " + lista);
        System.out.println("tamanho arrayList : " + lista.size());

        for (int i = 0; i < lista.size(); i++) {
            
            model.addElement(lista.get(i));
            jLabel1.setText("" + lista.size() + " arquivos");
        }
    }

    public void abrirImagem(int indice) throws IOException {

        String indiceValor = lista.get(indice).toString();
        System.out.println("conteudo do indice: " + indiceValor);
        VisualizarImagem visualizar = new VisualizarImagem();
        visualizar.setVisible(true);
    
        visualizar.desenhaAoCarregar(indiceValor);

    }
}
