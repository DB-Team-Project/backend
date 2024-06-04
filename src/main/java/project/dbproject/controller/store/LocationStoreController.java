package project.dbproject.controller.store;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.dbproject.domain.Store;
import project.dbproject.dto.LocationStoreDto;
import project.dbproject.dto.StoreDto;
import project.dbproject.service.StoreService;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class LocationStoreController {
    private final StoreService storeService;

    //위치별 제휴업체 조회
    @PostMapping("/location")
    public ResponseEntity<Result> getStoresForLocation(@RequestBody LocationRequest userLocation) {
        List<Store> storesNearByUser = storeService.getStoresNearbyUser(userLocation.latitude, userLocation.longitude, userLocation.distance);
        return getLocationStoreDto(storesNearByUser);
    }

    //마커 클릭시 조회
    @GetMapping("/location/{storeId}")
    public ResponseEntity<StoreDto> getStoreForLocation(@PathVariable(name = "storeId") Long storeId) {
        Store store = storeService.getStoreById(storeId);
        return getStoreDto(store);
    }

    private ResponseEntity<Result> getLocationStoreDto(final List<Store> storesNearByUser) {
        List<LocationStoreDto> locationStoreDto = storesNearByUser.stream().map(LocationStoreDto::new).toList();
        return ResponseEntity.ok(new Result<>(locationStoreDto));
    }

    private ResponseEntity<StoreDto> getStoreDto(final Store store) {
        return ResponseEntity.ok(new StoreDto(store));
    }

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private T stores;
    }

    @Data
    static class LocationRequest {
        private Double latitude;
        private Double longitude;
        private Double distance;
    }
}
