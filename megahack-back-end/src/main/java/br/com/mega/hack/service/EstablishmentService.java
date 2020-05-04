package br.com.mega.hack.service;

import java.awt.image.BufferedImage;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.mega.hack.model.Address;
import br.com.mega.hack.model.Establishment;
import br.com.mega.hack.model.dto.establishment.EstablishmentRequest;
import br.com.mega.hack.model.dto.establishment.EstablishmentResponse;
import br.com.mega.hack.repository.AddressRepository;
import br.com.mega.hack.repository.EstablishmentRepository;
import br.com.mega.hack.util.JWTUtil;
import br.com.mega.hack.util.S3Service;

@Service
public class EstablishmentService {

	@Autowired
	private EstablishmentRepository establishmentRepository;
	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private JWTUtil jwtUtil;
	@Autowired
	private S3Service s3Service;
	@Autowired
	private ImageService imageService;
	
	@Value("${img.profile.size}")
	private Integer size;

	public List<EstablishmentResponse> getAll() {
		return establishmentRepository.findByUserId(jwtUtil.userId()).stream().map(e -> new EstablishmentResponse(e))
				.collect(Collectors.toList());
	}
	
	public EstablishmentResponse getAllUuid(String uuid) {
		return new EstablishmentResponse(establishmentRepository.findByUserUuid(uuid));
	}


	public List<EstablishmentResponse> getAllNeighborhood(String neighborhood) {
		return establishmentRepository.findByAddressNeighborhood(neighborhood).stream()
				.map(e -> new EstablishmentResponse(e)).collect(Collectors.toList());
	}

	public EstablishmentResponse save(EstablishmentRequest establishmentRequest) {
		Address address = addressRepository.save(new Address(establishmentRequest.getAddress()));
		Establishment establishment = new Establishment(establishmentRequest, address, jwtUtil.userId());
		return new EstablishmentResponse(establishmentRepository.save(establishment));
	}

	public EstablishmentResponse getAllByUserId(Long id) {
		Establishment establishment = establishmentRepository.findOneById(id);
		return new EstablishmentResponse(establishment);
	}
	
	public EstablishmentResponse uploadPicture(MultipartFile multipartFile, Long id) {
		BufferedImage bufferedImage = imageService.getJpgImageFromFile(multipartFile);
		bufferedImage = imageService.cropSquare(bufferedImage);
		bufferedImage = imageService.resize(bufferedImage, size);

		String filename = jwtUtil.userUUID() + LocalDateTime.now() + ".jpg";
		URI imageURL = s3Service.uploadFile(imageService.getInputStream(bufferedImage, "jpg"), filename, "image");
		Establishment establishment = establishmentRepository.findOneById(id);
		establishment.setImageURL(imageURL.toString());
		return new EstablishmentResponse(establishmentRepository.save(establishment));
	}
}
