package fr.eql.al35.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.eql.al35.iservice.PhotoIService;
import fr.eql.al35.repository.PhotoIRepository;
import fr.eql.al35.repository.ProductIRepository;


@Service
@Transactional
public class PhotoService implements PhotoIService {

	@Autowired
	PhotoIRepository photoRepo;

	@Autowired
	ProductIRepository productRepo;

	@Override
	public void upDatePhoto(Integer id, String pathPhoto, String descriptionPhoto, Integer ProductId, Integer index) {
		/* ancien code Favori(te)

		Photo photo = new Photo(id, pathPhoto, descriptionPhoto, null);
		Optional<Product> optionalProduct = productRepo.findById(ProductId);
		Product product = null;
		if(optionalProduct.isPresent()) {
			product = optionalProduct.get();
			Set<Photo> photos = product.getPhotos();
			List<Photo> listPhoto = new ArrayList<Photo>(photos);
			for(int i = 0; i < listPhoto.size(); i++) {
				if(i ==index) {
					listPhoto.set(index, photo);
				}
			}
			photos = new HashSet<Photo>(listPhoto);
			product.setPhotos(photos);
			productRepo.save(product);
		}
		 */
	}
}
