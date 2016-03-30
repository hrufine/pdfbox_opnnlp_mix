
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;

import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.tokenize.SimpleTokenizer;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.util.InvalidFormatException;
import opennlp.tools.util.Span;
import net.sourceforge.tess4j.*;
import net.sourceforge.tess4j.util.LoadLibs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.util.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;
import javax.imageio.ImageIO;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;


/**
 * Hello world OpenNLP!
 * 
 */
class TesseractExample {
			void TesseractConversion()
			{
				 // System.setProperty("jna.library.path", "32".equals(System.getProperty("sun.arch.data.model")) ? "lib/win32-x86" : "lib/win32-x86-64");

		        File imageFile = new File("C:/Users/ji287396/Downloads/CSA set/images/L.png");
		        ITesseract instance = new Tesseract();  // JNA Interface Mapping
		       //  ITesseract instance = new Tesseract1(); // JNA Direct Mapping
		         File tessDataFolder = LoadLibs.extractTessResources("tessdata"); // Maven build bundles English data
		         instance.setDatapath(tessDataFolder.getAbsolutePath());

		        try {
		            String result = instance.doOCR(imageFile);
		            System.out.println(result);
		        } catch (TesseractException e) {
		            System.err.println(e.getMessage());
		        }
		    }
				
				
				
	}



class ConvertPDFPagesToImages{
	
	  void ConvertImages()
	  {
		  try {
		        String sourceDir = "C:/Users/ji287396/Downloads/CSA set/Lisa CSA_1.pdf"; // Pdf files are read from this folder
		        String destinationDir = "C:/Users/Downloads/CSA set/images/"; // converted images from pdf document are saved here

		        File sourceFile = new File(sourceDir);
		        File destinationFile = new File(destinationDir);
		        if (!destinationFile.exists()) {
		            destinationFile.mkdir();
		            System.out.println("Folder Created -> "+ destinationFile.getAbsolutePath());
		        }
		        if (sourceFile.exists()) {
		            System.out.println("Images copied to Folder: "+ destinationFile.getName());             
		            PDDocument document = PDDocument.load(sourceDir);
		            @SuppressWarnings("unchecked")
					List<PDPage> list = document.getDocumentCatalog().getAllPages();
		            System.out.println("Total files to be converted -> "+ list.size());

		            String fileName = sourceFile.getName().replace(".pdf", "");             
		            int pageNumber = 1;
		            for (PDPage page : list) {
		                BufferedImage image = page.convertToImage();
		                File outputfile = new File(destinationDir + fileName +"_"+ pageNumber +".png");
		                System.out.println("Image Created -> "+ outputfile.getName());
		                ImageIO.write(image, "png", outputfile);
		                pageNumber++;
		            }
		            document.close();
		            System.out.println("Converted Images are saved at -> "+ destinationFile.getAbsolutePath());
		        } else {
		            System.err.println(sourceFile.getName() +" File not exists");
		        }

		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
	  }
	

class openNlp{
	
	public void DoCalculation(String data_to_pass) throws InvalidFormatException, IOException{
		Logger log = LoggerFactory.getLogger(BasicNameFinder.class);

        String[] sentences = {data_to_pass};
        //sentences[0] = data_to_pass;
        // Load the model file downloaded from OpenNLP
        // http://opennlp.sourceforge.net/models-1.5/en-ner-person.bin
        TokenNameFinderModel model = new TokenNameFinderModel(new File(
                "./input/en-ner-person.bin"));

        // Create a NameFinder using the model
        NameFinderME finder = new NameFinderME(model);

        Tokenizer tokenizer = SimpleTokenizer.INSTANCE;

        for (String sentence : sentences) {

            // Split the sentence into tokens
            String[] tokens = tokenizer.tokenize(sentence);

            // Find the names in the tokens and return Span objects
            Span[] nameSpans = finder.find(tokens);

            // Print the names extracted from the tokens using the Span data
            log.info(Arrays.toString(Span.spansToStrings(nameSpans, tokens)));
		
	}
}
}

class PDFWithText {
	void CreatePDF(){
	PDDocument doc = null;
    PDPage page = null;
    try{
        doc = new PDDocument();
        page = new PDPage();

        doc.addPage(page);
        PDFont font = PDType1Font.HELVETICA_BOLD;

        PDPageContentStream content = new PDPageContentStream(doc, page);
        content.beginText();
        content.setFont( font, 12 );
        content.moveTextPositionByAmount( 100, 700 );
        content.drawString("Hello from www.printmyfolders.com");

        content.endText();
        content.close();
       doc.save("./PDFWithText.pdf");
       doc.close();
 } catch (Exception e){
     System.out.println(e);
 }
	}
}
class PDFRead{
	String ReadPdf()
	{   String data = new String();
		PDDocument pd;
		 BufferedWriter wr;
		 try {
	         File input = new File("D://workspace//Proxy_Statement_.pdf");  // The PDF file from where you would like to extract
	         File output = new File("D://workspace//SampleText.txt"); // The text file where you are going to store the extracted data
	         pd = PDDocument.load(input);
	         System.out.println(pd.getNumberOfPages());
	         System.out.println(pd.isEncrypted());
	         pd.save("THE_A__14_A.pdf"); // Creates a copy called "CopyOfInvoice.pdf"
	         PDFTextStripper stripper = new PDFTextStripper();
	         //System.out.println(stripper.getText(pd));
	         data=new String(stripper.getText(pd));
	         
	        //stripper.setStartPage(3); //Start extracting from page 3
	        // stripper.setEndPage(5); //Extract till page 5
	         wr = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(output)));
	         stripper.writeText(pd, wr);
	         
	         if (pd != null) {
	             pd.close();
	         }
	        // I use close() to flush the stream
	        wr.close();
	        
	 } catch (Exception e){
	         e.printStackTrace();
	        }
		 return data;
		 
	}
}
public class BasicNameFinder {
    public static void main(String[] args) throws InvalidFormatException, IOException  {
       String data_to_pass=null;
       TesseractExample TesseractTest=new  TesseractExample();
       TesseractTest.TesseractConversion();
       System.out.println("tess4j Conversion is done");
       ConvertPDFPagesToImages convert_images=new ConvertPDFPagesToImages();
       convert_images.ConvertImages();
       System.out.println("Image Conversion is done");
       
    	openNlp abcd=new openNlp();
    	PDFWithText pdf=new PDFWithText();
    	
    	pdf.CreatePDF();
    	System.out.println("1st call done");
    	
    	PDFRead pdfread=new PDFRead();
    	data_to_pass=pdfread.ReadPdf();
    	System.out.println("2nd call done");
    	
    	abcd.DoCalculation(data_to_pass);   
    	
    	System.out.println("3rd call done");
    	//System.out.println(data_to_pass);    	
    	System.out.println("Executed both");
        }
}
