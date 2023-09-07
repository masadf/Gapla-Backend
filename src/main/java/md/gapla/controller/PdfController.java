package md.gapla.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import md.gapla.service.PdfService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/pdf")
@Tag(name = "Контроллер для работы с PDF.", description = "Работа с pdf.")
@RequiredArgsConstructor
public class PdfController {
	private final PdfService pdfService;
	
	@Operation(summary = "Получение pdf сертификата по username.")
	@PostMapping
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Получи",
					content = {@Content(mediaType = MediaType.APPLICATION_PDF_VALUE)}),
			@ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
					content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
	})
	public ResponseEntity<byte[]> getPdf(@RequestBody String username) {
		
		byte[] contents = pdfService.getPdf(username);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_PDF);
		String filename = username + ".pdf";
		headers.setContentDispositionFormData(filename, filename);
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		
		return new ResponseEntity<>(contents, headers, HttpStatus.OK);
	}
}
