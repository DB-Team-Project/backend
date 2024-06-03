package project.dbproject.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.dbproject.domain.Store;
import project.dbproject.repository.StoreRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;


    public Store getStoreById(final Long storeId) {
        return storeRepository.findById(storeId);
    }

    public List<Store> getAllStores() {
        return storeRepository.findAll();
    }

    public List<Store> getStoresNearbyUser(final Double lat, Double lon, final Double distance) {
        return storeRepository.findNearBy(lat, lon, distance);
    }

    public void updateAverageRating(Long storeId) {
        Optional<Store> store = Optional.ofNullable(storeRepository.findById(storeId));
        if (store.isPresent()) {
            Store st = store.get();
            st.updateAverageRating();
        }
    }
}
