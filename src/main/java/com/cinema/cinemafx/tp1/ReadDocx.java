package com.cinema.cinemafx.tp1;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import java.io.FileInputStream;
import java.util.List;


public class ReadDocx {
    public static void main(String[] args) {
        try {
            FileInputStream fis = new FileInputStream("src/main/resources/CinemaInputFile.docx");
            XWPFDocument doc=new XWPFDocument(OPCPackage.open(fis));

            List<XWPFParagraph> paragraphList =  doc.getParagraphs();

            for (XWPFParagraph paragraph: paragraphList){

                System.out.println(paragraph.getText()+"\n");
            }

        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}


//BIEN FAIT !
