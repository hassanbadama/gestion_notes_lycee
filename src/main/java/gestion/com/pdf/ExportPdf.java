package gestion.com.pdf;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import gestion.com.entite.Eleve;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class ExportPdf {
	
	public static ByteArrayInputStream eleveReport(Eleve eleve) {
		Document document=new Document();
		ByteArrayOutputStream out=new ByteArrayOutputStream();
		try {
			PdfPTable table=new PdfPTable(3);
			table.setWidths(new int[]{4,4,4,4});
			table.setWidthPercentage(80);
			Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
			PdfPCell hcell;
			hcell = new PdfPCell(new Phrase("Nom",headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);
			
			hcell = new PdfPCell(new Phrase("Prenom",headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);
			
			hcell = new PdfPCell(new Phrase("contact",headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);
			
			PdfPCell cell;
			cell = new PdfPCell(new Phrase(eleve.getNom()));
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);
			
			cell = new PdfPCell(new Phrase(eleve.getPrenom()));
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);
			
			cell = new PdfPCell(new Phrase(eleve.getNumeroParent()));
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);
			
			PdfWriter.getInstance(document,out);
			document.open();
			document.add(table);
		}
		catch(DocumentException ex) {
			
		}
		return new ByteArrayInputStream(out.toByteArray());
		
	}

}
