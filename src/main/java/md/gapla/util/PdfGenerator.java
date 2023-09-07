package md.gapla.util;

import md.gapla.model.dto.account.AccountDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class PdfGenerator {
	@Value("classpath:pdf/certificate.pdf")
	Resource resourcePdf;
	
	@Value("classpath:pdf/GilroyExtraBold.ttf")
	Resource resourceFont1;
	
	@Value("classpath:pdf/GilroyLight.ttf")
	Resource resourceFont2;
	
	public synchronized byte[] createByteArrayPdfCertificate(AccountDto account, Integer result, String resultLevel) {
		try (PDDocument pdDocument = PDDocument.load(resourcePdf.getInputStream());
		     PDPageContentStream contentStream = new PDPageContentStream(
				     pdDocument,
				     pdDocument.getPage(0),
				     PDPageContentStream.AppendMode.APPEND,
				     false,
				     false
		     );
		     ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
			
			//Set needed font
			PDFont font = PDType0Font.load(PDDocument.load(resourcePdf.getInputStream()),
					resourceFont1.getInputStream());
			
			PDFont font2 = PDType0Font.load(PDDocument.load(resourcePdf.getInputStream()),
					resourceFont2.getInputStream());
			
			//Set needed font size
			float fontFioSize = 30;
			float fontResultPointsSize = 11.5F;
			float fontResultLevelSize = 12;
			float fontDateSize = 12;
			
			
			//Set text
			String fio = account.getFam() + " " + account.getIm() + " " + account.getOt();
			String resultPoints = result + "/100";
			
			LocalDate date = LocalDate.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
			String formattedDate = date.format(formatter);
			
			// Calculate text width
			float textWidth1 = font.getStringWidth(fio) / 1000 * fontFioSize;
			float textWidth2 = font.getStringWidth(resultPoints) / 1000 * fontResultPointsSize;
			float textWidth3 = font.getStringWidth(resultLevel) / 1000 * fontResultLevelSize;
			
			// Calculate the X coordinate to center-align the text
			float pageWidth = pdDocument.getPage(0).getMediaBox().getWidth();
			float startX1 = (pageWidth - textWidth1) / 2;
			float startX2 = (pageWidth - textWidth2) / 2;
			float startX3 = (pageWidth - textWidth3) / 2;
			
			// Set text color (in RGB values)
			contentStream.setNonStrokingColor(32, 196, 243); // Light blue
			contentStream.beginText();
			contentStream.setFont(font, fontFioSize);
			contentStream.newLineAtOffset(startX1, 420);
			contentStream.showText(fio);
			contentStream.endText();
			
			// Set text color (in RGB values)
			contentStream.setNonStrokingColor(255, 255, 255); // White color
			contentStream.beginText();
			contentStream.setFont(font, fontResultPointsSize);
			contentStream.newLineAtOffset(startX2, 225);
			contentStream.showText(resultPoints);
			contentStream.endText();
			
			contentStream.beginText();
			contentStream.setFont(font, fontResultLevelSize);
			contentStream.newLineAtOffset(startX3, 212);
			contentStream.showText(resultLevel);
			contentStream.endText();
			
			// Set text color (in RGB values)
			contentStream.setNonStrokingColor(0, 0, 0); // Black color
			contentStream.beginText();
			contentStream.setFont(font2, fontDateSize);
			contentStream.newLineAtOffset(328, 100);
			contentStream.showText(formattedDate);
			contentStream.endText();
			
			contentStream.close();
			
			pdDocument.save(byteArrayOutputStream);
			
			return byteArrayOutputStream.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
