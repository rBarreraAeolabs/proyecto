package com.gruposolux.rcivil.pdisciplinario.service.util;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.xml.parsers.ParserConfigurationException;

import com.gruposolux.rcivil.pdisciplinario.service.DocumentoService;
import com.itextpdf.text.*;
import com.itextpdf.tool.xml.XMLWorker;
import com.itextpdf.tool.xml.html.Tags;
import com.itextpdf.tool.xml.parser.XMLParser;
import com.itextpdf.tool.xml.pipeline.end.PdfWriterPipeline;
import com.itextpdf.tool.xml.pipeline.html.AbstractImageProvider;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipelineContext;
import com.itextpdf.tool.xml.pipeline.html.LinkProvider;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.fit.pdfdom.PDFDomTree;

import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.DELETE_ON_CLOSE;

/**
 * Created by sneiraillanes on 09-04-2019.
 */
public class PdfConverter {
    public static void generateHTMLFromPDF(String filename) throws ParserConfigurationException, IOException {
        PDDocument pdf = PDDocument.load(new File(filename));
        PDFDomTree parser = new PDFDomTree();
        Writer output = new PrintWriter("src/output/pdf.html", "utf-8");
        parser.writeText(pdf, output);
        output.close();
        if (pdf != null) {
            pdf.close();
        }
    }

    public static String generatePDFFromHTML(String filename, String data, Path pathAdjunto) throws ParserConfigurationException, IOException, DocumentException {
        Logger log = LoggerFactory.getLogger(PdfConverter.class);

        Document document = new Document(PageSize.A4);

        try {
            String html = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "\n" +
                "<head>\n" +
                "<title>A very simple webpage</title>\n" +
                "</head>\n" +
                "\n" +
                "<body>";
            html += data;
            html += "</body>\n" +
                "\n" +
                "</html>";
            byte content[] = html.getBytes();
//            Path p = Paths.get("./upload/temp.html");
            Path p = Paths.get(pathAdjunto.toString() + "/temp.html");
            OutputStream os = Files.newOutputStream(p);
            os.write(content, 0, content.length);

            if (filename.indexOf(".") < 0) {
                filename = filename + ".pdf";
            }
//            String path = "./upload/" + filename;
            String path = pathAdjunto.toString() + "/" + filename;

            // content/logo/logo.png URL del logo

            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(path));
            document.open();

            HtmlPipelineContext htmlContext = new HtmlPipelineContext(null);
            htmlContext.setTagFactory(Tags.getHtmlTagProcessorFactory());
            htmlContext.setImageProvider(new AbstractImageProvider() {
                @Override
                public String getImageRootPath() {
//                    return "C:\\Users\\sneiraillanes\\proyectos\\netlinux\\registrocivil-pdisciplinario\\app\\src\\main\\webapp\\";
                    try {
//                        return new File(".").getCanonicalPath() + File.separator + "src" + File.separator + "main" + File.separator + "webapp" + File.separator;
                        return new File(".").getCanonicalPath() + File.separator + "target" + File.separator + "www" + File.separator;
                    } catch (IOException e) {
                        log.debug(e.getMessage());
                    }
                    return "." + File.separator;
                }
            });
            htmlContext.setLinkProvider(new LinkProvider() {
                @Override
                public String getLinkRoot() {
//                    return "C:\\Users\\sneiraillanes\\proyectos\\netlinux\\registrocivil-pdisciplinario\\app\\src\\main\\webapp\\";
                    try {
//                        return new File(".").getCanonicalPath() + File.separator + "src" + File.separator + "main" + File.separator + "webapp" + File.separator;
                        return new File(".").getCanonicalPath() + File.separator + "target" + File.separator + "www" + File.separator;
                    } catch (IOException e) {
                        log.debug(e.getMessage());
                    }
                    return "." + File.separator;
                }
            });

            PdfWriterPipeline pdf = new PdfWriterPipeline(document, writer);
            HtmlPipeline htmlPipeline = new HtmlPipeline(htmlContext, pdf);

            XMLWorker worker = new XMLWorker(htmlPipeline, true);
            XMLParser xmlParser = new XMLParser(worker);

//            xmlParser.parse(new FileInputStream("./upload/temp.html"));
            xmlParser.parse(new FileInputStream(pathAdjunto.toString() + "/temp.html"));

//            XMLWorkerHelper.getInstance().parseXHtml(writer, document, new FileInputStream("./upload/temp.html"));
            document.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        log.debug("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
        log.debug("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
        log.debug("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
        log.debug("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
        log.debug("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
        log.debug("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
        log.debug("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
        log.debug("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");

        log.debug(new File(".").getCanonicalPath() + File.separator + "target" + File.separator + "www" + File.separator);


        return filename;
    }
}
