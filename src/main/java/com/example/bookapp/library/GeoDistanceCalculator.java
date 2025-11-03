package com.example.bookapp.library;
//Haversine Formula 공식 사용해서 일정거리안에 들어가는지
public class GeoDistanceCalculator {

    // 지구의 평균 반지름 (킬로미터) - 약 6371km
    private static final double EARTH_RADIUS_KM = 6371;
    // 지구의 평균 반지름 (미터)
    private static final double EARTH_RADIUS_M = EARTH_RADIUS_KM * 1000;

    /**
     * 두 위도/경도 지점 사이의 거리를 미터 단위로 계산합니다 (Haversine 공식 사용).
     * * @param lat1 첫 번째 지점의 위도 (도)
     * @param lon1 첫 번째 지점의 경도 (도)
     * @param lat2 두 번째 지점의 위도 (도)
     * @param lon2 두 번째 지점의 경도 (도)
     * @return 두 지점 사이의 거리 (미터)
     */
    public static double calculateDistance(double lat1, double lon1, double lat2, double lon2) {

        // 각도를 라디안으로 변환
//        double lat1Rad = Math.toRadians(lat1);
//        double lat2Rad = Math.toRadians(lat2);
//        double deltaLat = Math.toRadians(lat2 - lat1);
//        double deltaLon = Math.toRadians(lon2 - lon1);
//
//        // Haversine 공식
//        double c= EARTH_RADIUS_KM*Math.acos(Math.sin(lat1)*Math.sin(lat2)+Math.cos(lat1)*Math.cos(lat2)*Math.cos(lon1-lon2));
////        double a = Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2) +
////                Math.cos(lat1Rad) * Math.cos(lat2Rad) *
////                        Math.sin(deltaLon / 2) * Math.sin(deltaLon / 2);
////
////        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
//
//        // 거리 (미터)
//        double distance = EARTH_RADIUS_M * c;
        double distance;
        double radius = 6371; // 지구 반지름(km)
        double toRadian = Math.PI / 180;

        double deltaLatitude = Math.abs(lat1 - lat2) * toRadian;
        double deltaLongitude = Math.abs(lon1 - lon2) * toRadian;

        double sinDeltaLat = Math.sin(deltaLatitude / 2);
        double sinDeltaLng = Math.sin(deltaLongitude / 2);
        double squareRoot = Math.sqrt(
                sinDeltaLat * sinDeltaLat +
                        Math.cos(lat1 * toRadian) * Math.cos(lat2 * toRadian) * sinDeltaLng * sinDeltaLng);

        distance = 2 * radius * Math.asin(squareRoot);


        return distance*1000;
    }

    /**
     * 두 지점 사이의 거리가 지정된 반경(미터) 이내인지 확인합니다.
     * * @param lat1 첫 번째 지점의 위도 (도)
     * @param lon1 첫 번째 지점의 경도 (도)
     * @param lat2 두 번째 지점의 위도 (도)
     * @param lon2 두 번째 지점의 경도 (도)
     * @param radiusMeters 반경 (미터)
     * @return 500m 이내이면 true, 아니면 false
     */
    public static boolean isWithinRadius(double lat1, double lon1, double lat2, double lon2, double radiusMeters) {
        double distance = calculateDistance(lat1, lon1, lat2, lon2);
        System.out.println("Distance between lat1 and lat2 is " + distance);
        return distance <= radiusMeters;
    }
}