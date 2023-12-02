package com.rsn.serviceimpl;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.rsn.exception.RecordNotFoundException;
import com.rsn.model.Image;
import com.rsn.repository.EmployeeRepo;
import com.rsn.repository.ImageRepo;
import com.rsn.utils.ImageUtils;

@Service
public class ImageServiceImpl {

	@Autowired
	private ImageRepo imageRepo;

	@Autowired
	private EmployeeRepo employeeRepo;

	public Image uploadProfileFeature(MultipartFile file, Integer id) throws RecordNotFoundException, IOException {
		Optional<Image> image = Optional
				.ofNullable(imageRepo.findById(id).orElseThrow(() -> new RecordNotFoundException()));

		if (image.isPresent()) {
			Image image2 = new Image(id, ImageUtils.compressImage(file.getBytes()));
			return imageRepo.save(image2);
		} else {
			throw new RecordNotFoundException();
		}

	}

	public byte[] downloadImageFeature(Integer id) throws RecordNotFoundException {
		Optional<Image> dbImageData = Optional
				.ofNullable(imageRepo.findById(id).orElseThrow(() -> new RecordNotFoundException()));
		byte[] image = ImageUtils.decompressImage(dbImageData.get().getImageData());
		return image;
	}

}
