package com.rsn.test_controller;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.multipart.MultipartFile;

import com.rsn.controller.ImagesController;
import com.rsn.exception.RecordNotFoundException;
import com.rsn.model.Image;
import com.rsn.serviceimpl.ImageServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class ImageController_Test {

	@InjectMocks
	private ImagesController mockImagesController;

	@Mock
	private ImageServiceImpl mockImageServiceImpl;

	@Mock
	private MultipartFile file;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void test_uploadImageAPI() throws RecordNotFoundException, IOException {
		file = mock(MultipartFile.class);
		byte[] mockImage = "content".getBytes();
		Image image = new Image();
		image.setId(1);
		image.setImageData(mockImage);

		when(mockImageServiceImpl.uploadProfileFeature(any(), anyInt())).thenReturn(image);
		assertNotNull(mockImagesController.uploadImageAPI(file, 1));
	}

	@Test
	void test_downloadImageAPI() throws RecordNotFoundException {
		file = mock(MultipartFile.class);
		byte[] mockImage = "content".getBytes();
		Image image = new Image();
		image.setId(1);
		image.setImageData(mockImage);

		when(mockImageServiceImpl.downloadImageFeature(anyInt())).thenReturn(mockImage);
		assertNotNull(mockImagesController.downloadImageAPI(1));
	}

}
