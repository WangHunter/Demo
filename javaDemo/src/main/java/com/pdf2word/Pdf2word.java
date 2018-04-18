package com.pdf2word;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

import java.io.*;


//解决不了图片问题，出现乱码，存在格式异常
public class Pdf2word {
    public static void main(String[] args) throws IOException {
        File file = new File("D:\\Desktop\\zookeeper-3.4.6和kafka安装文档.pdf");
        PDDocument doc=PDDocument.load(file);
        int pagenumber=doc.getNumberOfPages();
        System.out.print("页数共有："+pagenumber);
        FileOutputStream fos=new FileOutputStream("D:\\Desktop\\zookeeper.doc");
        Writer writer=new OutputStreamWriter(fos,"UTF-8");
        PDFTextStripper stripper=new PDFTextStripper();
        stripper.setSortByPosition(true);//排序
        stripper.setStartPage(1);//设置转换的开始页
        stripper.setEndPage(pagenumber);//设置转换的结束页
        stripper.writeText(doc,writer);
        writer.close();
        doc.close();
    }
}
