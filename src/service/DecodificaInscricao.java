/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.imageio.ImageIO;
import uteis.CodigoBarrasZXing;

/**
 *
 * @author janio
 */
public class DecodificaInscricao {

    public String[] carregaInscricaoPeloCodigoBarras(File arquivoAtual) {
        String nomeArquivo = arquivoAtual.getName().substring(0, arquivoAtual.getName().indexOf("."));

        return carregaInscricaoPeloCodigoBarras(nomeArquivo);
    }

    public String[] carregaInscricaoPeloCodigoBarras(String codioOriginalCB) {
        String inscricao = "";
        int disciplina = 0;
        int questao = 0;
        String[] retorno = {"", "", ""};

        String atual = codioOriginalCB;
        //if (atual.indexOf("CODE") != -1 ) {
        try {

            String strDisc = atual.substring(atual.indexOf("D") + 1, atual.indexOf("Q"));

            inscricao = (atual.substring(atual.indexOf("I") + 1));
            questao = Integer.valueOf(atual.substring(atual.indexOf("Q") + 1, atual.indexOf("I")));
            if(questao==0){
                questao = Integer.valueOf(atual.substring(atual.indexOf("Q") + 1, atual.indexOf("Q") + 2));
            }
            
            if(strDisc!=null && !strDisc.trim().equalsIgnoreCase("")){
                disciplina = Integer.valueOf(strDisc);
            }else{
                disciplina =40;
                System.out.println("ERRO AO OBTER A DISCIPLINA DO CÓDIGO DE BARRAS. Assumindo: "+disciplina);
            }
            
            if (inscricao.equalsIgnoreCase("")) {
                try {
                    inscricao = (atual.substring(atual.indexOf("i") + 1));
                } catch (Exception ex) {
                    inscricao = "";
                    System.out.println("Erro " + ex.getMessage());
                }
            }
            if (questao == 0) {
                try {
                    questao = Integer.valueOf(atual.substring(atual.indexOf("Q") + 1, atual.indexOf("Q") + 3));
                } catch (Exception ex) {
                    questao = 0;
                    System.out.println("Erro " + ex.getMessage());
                }
            }
            if (disciplina == 0) {
                try {
                    disciplina = Integer.valueOf(strDisc.substring(strDisc.indexOf("d") + 1, strDisc.indexOf("d") + 3));
                } catch (Exception ex) {
                    disciplina = 0;
                    System.out.println("Erro " + ex.getMessage());
                }
            }

        } catch (NumberFormatException ex) {
            inscricao = "";
        } catch (Exception ex) {
            inscricao = "";
        }
        if (!(!inscricao.equalsIgnoreCase("") && questao > 0 && disciplina > 0)) {
            String vazio[] = {""};
            return vazio;
        }

        inscricao = inscricao.substring(0, 10);
        if (inscricao.equalsIgnoreCase("")) {
            try {
                inscricao = (codioOriginalCB);
                if (codioOriginalCB.startsWith("0")) {
                    inscricao = (codioOriginalCB.substring(2));
                }
            } catch (NumberFormatException ex) {
                inscricao = "";
            }
        }
        retorno[0] = inscricao;
        retorno[1] = String.valueOf(questao);
        retorno[2] = String.valueOf(disciplina);
        return retorno;

    }

    public String[] carregaNovaInscricaoPeloNomeArquivo(String[] codigos, String nomeArquivo) {
        String inscricao = "";
        int disciplina = 0;
        int questao = 0;
        String[] retorno = {"", "", ""};

        for (String atual : codigos) {
            //if (atual.indexOf("CODE") != -1 ) {
            try {

                String strDisc = atual.substring(atual.indexOf("D") + 1, atual.indexOf("Q"));

                inscricao = (atual.substring(atual.indexOf("I") + 1));
                questao = Integer.valueOf(atual.substring(atual.indexOf("Q") + 1, atual.indexOf("Q") + 2));
                disciplina = Integer.valueOf(strDisc);

                if (inscricao.equalsIgnoreCase("")) {
                    try {
                        inscricao = (atual.substring(atual.indexOf("i") + 1));
                    } catch (Exception ex) {
                        inscricao = "";
                        System.out.println("Erro " + ex.getMessage());
                    }
                }
                if (questao == 0) {
                    try {
                        questao = Integer.valueOf(atual.substring(atual.indexOf("Q") + 1, atual.indexOf("Q") + 3));
                    } catch (Exception ex) {
                        questao = 0;
                        System.out.println("Erro " + ex.getMessage());
                    }
                }
                if (disciplina == 0) {
                    try {
                        disciplina = Integer.valueOf(strDisc.substring(strDisc.indexOf("d") + 1, strDisc.indexOf("d") + 3));
                    } catch (Exception ex) {
                        disciplina = 0;
                        System.out.println("Erro " + ex.getMessage());
                    }
                }

            } catch (NumberFormatException ex) {
                inscricao = "";
            } catch (Exception ex) {
                inscricao = "";
            }
            if (!inscricao.equalsIgnoreCase("") && questao > 0 && disciplina > 0) {
                break;
            }
        }
        inscricao = inscricao.substring(0, 10);
        if (inscricao.equalsIgnoreCase("")) {
            try {
                inscricao = (nomeArquivo);
                if (nomeArquivo.startsWith("0")) {
                    inscricao = (nomeArquivo.substring(2));
                }
            } catch (NumberFormatException ex) {
                inscricao = "";
            }
        }
        retorno[0] = inscricao;
        retorno[1] = String.valueOf(questao);
        retorno[2] = String.valueOf(disciplina);
        return retorno;
    }
}
