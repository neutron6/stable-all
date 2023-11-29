package com.rsn.serviceimpl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.zip.Deflater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.rsn.exception.RecordNotFoundException;
import com.rsn.model.Employee;
import com.rsn.model.Profiles;
import com.rsn.repository.EmployeeRepo;
import com.rsn.repository.ImageRepo;

@Service
public class ProfilesServiceImpl {

	@Autowired
	private ImageRepo imageRepo;

	@Autowired
	private EmployeeRepo employeeRepo;

	public Profiles uploadProfile(MultipartFile file, Integer id) throws RecordNotFoundException, IOException {

		Optional<Employee> profiles = Optional
				.ofNullable(employeeRepo.findById(id).orElseThrow(() -> new RecordNotFoundException()));

		if (profiles.isPresent()) {
			Profiles profiles3 = new Profiles(id, compressBytes(file.getBytes()));
			return imageRepo.save(profiles3);
		} else {
			throw new RecordNotFoundException();
		}

	}

	public static byte[] compressBytes(byte[] data) {
		Deflater deflater = new Deflater();
		deflater.setInput(data);
		deflater.finish();

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[1024];
		while (!deflater.finished()) {
			int count = deflater.deflate(buffer);
			outputStream.write(buffer, 0, count);
		}
		try {
			outputStream.close();
		} catch (IOException e) {
		}
		System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);

		return outputStream.toByteArray();
	}

}
