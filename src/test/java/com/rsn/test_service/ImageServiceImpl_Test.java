package com.rsn.test_service;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import com.rsn.exception.RecordNotFoundException;
import com.rsn.model.Image;
import com.rsn.repository.EmployeeRepo;
import com.rsn.repository.ImageRepo;
import com.rsn.serviceimpl.ImageServiceImpl;
import com.rsn.utils.ImageUtils;

@RunWith(MockitoJUnitRunner.class)
public class ImageServiceImpl_Test {

	@InjectMocks
	private ImageServiceImpl imageServiceImpl;

	@Mock
	private EmployeeRepo mockEmployeeRepo;

	@Mock
	private ImageRepo mockImageRepo;

	@Mock
	private MultipartFile file;

	@Mock
	private ImageUtils mockImageUtils;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void test_uploadProfileFeature() throws IOException, RecordNotFoundException {
		Integer id = 1;
		byte[] imageData = "content".getBytes();
		file = mock(MultipartFile.class);
		when(file.getBytes()).thenReturn(imageData);

		Image image = new Image();
		image.setId(id);
		image.setImageData(imageData);

		when(mockImageRepo.findById(anyInt())).thenReturn(Optional.of(image));
		when(mockImageRepo.save(any())).thenReturn(image);
		byte[] image1 = mockImageUtils.compressImage(imageData);

		assertNotNull(imageServiceImpl.uploadProfileFeature(file, id));
	}

	@Test()
	void test_uploadProfileFeature_Exception() throws IOException, RecordNotFoundException {
		Integer id = 1;
		byte[] imageData = "content".getBytes();
		file = mock(MultipartFile.class);
		when(file.getBytes()).thenReturn(imageData);

		Image image = new Image();
		image.setId(id);

		when(mockImageRepo.findById(anyInt())).thenReturn(Optional.of(image));
		when(mockImageRepo.save(any())).thenReturn(image);
		byte[] image1 = mockImageUtils.compressImage(imageData);

		imageServiceImpl.uploadProfileFeature(file, 2);
	}

	@Test
	void test_downloadImageFeature() throws IOException, RecordNotFoundException {
		Integer id = 1;
		byte[] imageData = "content".getBytes();
		file = mock(MultipartFile.class);
		when(file.getBytes()).thenReturn(imageData);

		Image image = new Image();
		image.setId(id);
		image.setImageData(imageData);

		when(mockImageRepo.findById(anyInt())).thenReturn(Optional.of(image));
		when(mockImageRepo.save(any())).thenReturn(image);
		byte[] image1 = mockImageUtils.decompressImage(imageData);
		assertNotNull(imageServiceImpl.downloadImageFeature(id));
	}
}
