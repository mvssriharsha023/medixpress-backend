package com.medixpress.user_service.service;

import java.util.Optional;

public interface GeoLocationService {
    Optional<double[]> getLatLongFromAddress(String address);
}
