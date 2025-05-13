package com.soukenka.cardealer.app.api;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * This class defines constants representing API endpoints for the application.
 * Provides centralized management of REST API endpoint base URLs.
 *
 * @author Ing. Bc. Daniel Soukenka
 * @version 1.0
 * @created 08.05.2025
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Endpoints {
    public static final String ENDPOINT_V1 = "/api/v1/";

    public static final String ENDPOINT_V1_VEH = ENDPOINT_V1 + "veh/";
    public static final String ENDPOINT_V1_VEH_CAR = ENDPOINT_V1_VEH + "cars";
}
