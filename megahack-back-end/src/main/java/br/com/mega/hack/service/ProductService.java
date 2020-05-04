package br.com.mega.hack.service;

import java.awt.image.BufferedImage;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.mega.hack.model.Product;
import br.com.mega.hack.model.dto.ProductRequest;
import br.com.mega.hack.model.dto.ProductResponse;
import br.com.mega.hack.repository.ProductRepository;
import br.com.mega.hack.service.exception.ObjectNotFoundException;
import br.com.mega.hack.util.JWTUtil;
import br.com.mega.hack.util.S3Service;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private JWTUtil jwtUtil;
	@Autowired
	private S3Service s3Service;
	@Autowired
	private ImageService imageService;

	@Value("${img.profile.size}")
	private Integer size;

	public ProductResponse getProduct(Long id) {
		return productRepository.findById(id).map(p -> new ProductResponse(p))
				.orElseThrow(() -> new ObjectNotFoundException());
	}

	public List<ProductResponse> getProductsByEstablishment(Long id) {
		return productRepository.getListProductByEstablishment(id).stream().map(p -> new ProductResponse(p))
				.collect(Collectors.toList());
	}

	public void EnableDisableProduct(Long id, Boolean active) {
		Optional<Product> product = productRepository.findById(id);
		if (product.isPresent()) {
			product.get().setActive(active);
			productRepository.save(product.get());
		}
	}

	public ProductResponse uploadPicture(MultipartFile file, Long id) {
		Optional<Product> product = productRepository.findById(id);
		if (product.isPresent()) {
			BufferedImage bufferedImage = imageService.getJpgImageFromFile(file);
			bufferedImage = imageService.cropSquare(bufferedImage);
			bufferedImage = imageService.resize(bufferedImage, size);

			String filename = jwtUtil.userUUID() + LocalDateTime.now() + "product.jpg";
			URI imageURL = s3Service.uploadFile(imageService.getInputStream(bufferedImage, "jpg"), filename, "image");

			product.get().setImageURL(imageURL.toString());
			productRepository.save(product.get());
		}
		return new ProductResponse(product.get());
	}

	public ProductResponse save(@Valid ProductRequest request) {
		Product product = productRepository.save(new Product(request));
		return new ProductResponse(product);
	}

}
