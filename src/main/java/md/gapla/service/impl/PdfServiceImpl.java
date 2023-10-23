package md.gapla.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import md.gapla.mapper.AppMapper;
import md.gapla.model.entity.account.AccountCheckLevelEntity;
import md.gapla.model.entity.account.AccountEntity;
import md.gapla.model.entity.courseexam.ExamEntity;
import md.gapla.repository.account.AccountCheckLevelRepository;
import md.gapla.repository.account.AccountRepository;
import md.gapla.repository.exam.ExamRepository;
import md.gapla.service.PdfService;
import md.gapla.util.PdfGenerator;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PdfServiceImpl implements PdfService {
	private final AccountRepository accountRepository;
	private final PdfGenerator pdfGenerator;
	private final AppMapper appMapper;
	private final AccountCheckLevelRepository accountCheckLevelRepository;
	private final ExamRepository examRepository;
	
	@Override
	public byte[] getPdf(Long checkLevelId) {
		AccountCheckLevelEntity accountCheckLevelEntity = accountCheckLevelRepository.findById(checkLevelId)
				.orElseThrow(() -> new EntityNotFoundException("AccountLevel with id = " + checkLevelId + " not found."));
		AccountEntity account = accountRepository.findById(accountCheckLevelEntity.getAccountId())
				.orElseThrow(() -> new EntityNotFoundException("Account with id = " + accountCheckLevelEntity.getAccountId() + " not found."));
		
		Integer result = accountCheckLevelEntity.getTotal().intValue();
		
		Long examId = accountCheckLevelEntity.getCourseExamList().get(0).getExamId();
		ExamEntity exam = examRepository.findById(examId)
				.orElseThrow(() -> new EntityNotFoundException("Exam with id = " + examId + " not found."));
		
		byte[] byteArrayPdfFromString =
				pdfGenerator.createByteArrayPdfCertificate(appMapper.map(account), result, exam.getExamName());
		return byteArrayPdfFromString;
	}
}
