package tk.daudecinc.gimcana.tools;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.google.zxing.WriterException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import tk.daudecinc.gimcana.model.entities.Location;

@Component
public class PDFLocationsGenerator {
	
	private Log log = LogFactory.getLog(PDFLocationsGenerator.class);
	
	@Autowired
	private QRGenerator qrGenerator;
	
	@Value("${dd5.web.url}")
	private String webUrl;
	
	private Font normalFont = new Font(FontFamily.HELVETICA, 12);
	private Font titleFont = new Font(FontFamily.HELVETICA, 16, Font.BOLD);
	private Font notesFont = new Font(FontFamily.HELVETICA, 9);
	
	
	public InputStream generateLocationsPDFDocument(List<Location> locations) {
		try {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			Document doc = generateDocument(out);
			
			locations.forEach(l -> {
				try {
					generateDocumentBody(doc, l);
				} catch (DocumentException | URISyntaxException | IOException | WriterException e) {
					log.error(e);
				}
			});
			
			doc.close();
			return new ByteArrayInputStream(out.toByteArray());
			
		} catch (DocumentException de) {
			log.error(de);
		}
		
		return null;
	}

	private void generateDocumentBody(Document doc, Location currentLocation) throws DocumentException, URISyntaxException, IOException, WriterException {	
		log.info("Generating location for: " + currentLocation.getName());
		
		generateHeader(doc);
		generateTitle(doc, currentLocation);
		generateQR(doc, currentLocation);
		generateFooter(doc);
		
		doc.newPage();
		
	}

	private void generateFooter(Document doc) throws DocumentException {
		PdfPTable table = new PdfPTable(1);
		
		PdfPCell text = new PdfPCell(new Phrase("Selecciona el teu jugador al formulari, i posa la teva paraula " +
				"de pas per obtenir la lletra oculta en aquesta localització, i l'endevinalla de la propera.", normalFont));
		text.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		text.setMinimumHeight(130);
		text.setBorder(Rectangle.NO_BORDER);
		table.addCell(text);
		
		text = new PdfPCell(new Phrase("Aquest document forma part de l'activitat \"Gimcana Tecnològica\" que se celebrarà a Sant Climent de Llobregat." + 
				" Si us plau, no faci malbé aquest document per tal que l'activitat es pugui dur a terme sense incidents.", notesFont));
		text.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		text.setBorder(Rectangle.NO_BORDER);
		table.addCell(text);
		
		doc.add(table);
		
	}

	private void generateQR(Document doc, Location currentLocation) throws DocumentException, IOException, WriterException {
		PdfPTable table = new PdfPTable(1);
		
		PdfPCell text = new PdfPCell(new Phrase("Has arribart a un punt de control." + 
				" Escaneja amb el teu dispositiu mòbil el següent codi QR:", normalFont));
		text.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		text.setMinimumHeight(30);
		text.setBorder(Rectangle.NO_BORDER);
		table.addCell(text);
		
		String url = webUrl + "/gimcana-tecnologica/players/checkPoint" + (currentLocation.getCode() == null ? "" : "?locationCode=" + currentLocation.getCode());
		Image qrCode = Image.getInstance(qrGenerator.generateQR(url));
		text = new PdfPCell(qrCode);
		text.setHorizontalAlignment(Element.ALIGN_CENTER);
		text.setMinimumHeight(175);
		text.setBorder(Rectangle.NO_BORDER);
		table.addCell(text);
		
		text = new PdfPCell(new Phrase(url, notesFont));
		text.setHorizontalAlignment(Element.ALIGN_CENTER);
		text.setMinimumHeight(60);
		text.setBorder(Rectangle.NO_BORDER);
		table.addCell(text);
		
		doc.add(table);
		
	}

	private void generateTitle(Document doc, Location currentLocation) throws DocumentException {
		PdfPTable table = new PdfPTable(1);
		
		PdfPCell title = new PdfPCell(new Phrase(currentLocation.getName().toUpperCase(), titleFont));
		title.setHorizontalAlignment(Element.ALIGN_CENTER);
		title.setMinimumHeight(60);
		title.setBorder(Rectangle.NO_BORDER);
		
		table.addCell(title);
		doc.add(table);
		
	}

	private void generateHeader(Document doc) throws DocumentException, URISyntaxException, IOException {
		PdfPTable table = new PdfPTable(2);
		
		Path dd5Logo = Paths.get(this.getClass().getResource("/dd5logo.jpg").toURI());
		Image dd5Image = Image.getInstance(dd5Logo.toAbsolutePath().toString());
		dd5Image.scalePercent(50);
		
		Path sclLogo = Paths.get(this.getClass().getResource("/scllogo.png").toURI());
		Image sclImage = Image.getInstance(sclLogo.toAbsolutePath().toString());
		sclImage.scalePercent(50);
		
		PdfPCell logoDD5 = new PdfPCell(dd5Image);
		logoDD5.setBorder(Rectangle.NO_BORDER);
		PdfPCell logoAjuntament = new PdfPCell(sclImage);
		logoAjuntament.setHorizontalAlignment(Element.ALIGN_RIGHT);
		logoAjuntament.setBorder(Rectangle.NO_BORDER);
		
		
		table.addCell(logoDD5);
		table.addCell(logoAjuntament);
		doc.add(table);
	}

	private Document generateDocument(ByteArrayOutputStream out) throws DocumentException {
		Document document = new Document();
		PdfWriter.getInstance(document, out);
		
		document.open();
		
		return document;
	}

}
