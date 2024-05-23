package project.dbproject.controller.store;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import project.dbproject.domain.Store;
import project.dbproject.dto.LocationStoreDto;
import project.dbproject.dto.StoreDto;
import project.dbproject.service.StoreService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class LocationStoreController {
    private final StoreService storeService;

    //위치별 제휴업체 조회
    @PostMapping("/location")
    public Result getStoresForLocation(@RequestBody LocationRequest userLocation) {
        List<Store> storesNearByUser = storeService.getStoresNearbyUser(userLocation.latitude, userLocation.longitude, userLocation.distance);
        List<LocationStoreDto> locationStoreDtos = storesNearByUser.stream()
                .map(Store::toLocationDto)
                .collect(Collectors.toList());
        return new Result<>(locationStoreDtos);
    }

    //마커 클릭시 조회
    @GetMapping("/location/{storeId}")
    public StoreDto getStoreForLocation(@PathVariable(name="storeId") Long storeId) {
        Store storeById = storeService.getStoreById(storeId);
        return storeById.toStoreDto();
    }

    @Data
    @AllArgsConstructor
    static class Result<T>{
        private T stores;
    }

    @Data
    static class LocationRequest{
        private Double latitude;
        private Double longitude;
        private Double distance;
    }
}
