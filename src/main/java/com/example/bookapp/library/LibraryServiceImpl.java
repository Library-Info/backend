package com.example.bookapp.library;

import com.example.bookapp.dto.LibraryDto;
import com.example.bookapp.mapper.LibraryMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LibraryServiceImpl {

    @Value("${my.SearchRadiusMeter}")
    private  double  SEARCH_RADIUS_METER;

    private final LibraryMapper libraryMapper;
    public LibraryServiceImpl(LibraryMapper libraryMapper ) {
        this.libraryMapper = libraryMapper;

    }

    /**
     *  내위치의 위도 경도를 받으면, 시도 행정구역을 반환하는 메소드
     *  시도를 반환하는 그 시도헤 해당하는 리스트를 추출함
     *  그걸 리스트를 돌려가면서 거리를 계산함. 500m 안인 도서관 데이터를 추출함.
     * @param mylantitude
     * @param mylongitude
     * @param city
     * @param province
     */
    public List<?> getLibraryBylocation(double mylantitude, double mylongitude, String city, String province) {
        // 해당 시도의 도서관 리스트
        List<LibraryDto> libraryList =libraryMapper.findLibraryBycityandprovince(city,province);
        System.out.println("Getting library list"+libraryList);
        List<LibraryDto> libraryListIn500 = new ArrayList<>();
        // 리스트를 돌려가면서 500m 안인지 계산하는  공식 메서드
        for(LibraryDto library:libraryList){
            boolean isWithinRadius=GeoDistanceCalculator.isWithinRadius(mylantitude,mylongitude,library.getLatitude(),library.getLongitude(),SEARCH_RADIUS_METER);
            if(isWithinRadius){
                libraryListIn500.add(library);
            }
        }
        System.out.println("libraryListIn500"+libraryListIn500);

        if(libraryListIn500.isEmpty()){
            return libraryList;
        }

        return libraryListIn500;
    }


}
