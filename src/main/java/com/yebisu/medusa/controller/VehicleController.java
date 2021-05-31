package com.yebisu.medusa.controller;

import com.yebisu.medusa.controller.dto.Point;
import com.yebisu.medusa.service.MedusaService;
import com.yebisu.medusa.service.dto.VehicleState;
import com.yebisu.medusa.util.API;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.time.Duration;

@RestController
@RequestMapping(API.VEHICLE_BASE_API)
@RequiredArgsConstructor
@Slf4j
public class VehicleController {

    private final MedusaService medusaService;

    @GetMapping(value = "/{id}/state")
    public Mono<VehicleState> getState(@PathVariable("id") final String vehicleId) {
        log.debug("GET: Vehicle state: {}", vehicleId);
        return medusaService.getState(vehicleId)
                .timeout(Duration.ofMillis(800));
    }

    @PostMapping(value = "/{id}/move")
    public Mono<ResponseEntity<Void>> moveVehicleTo(@PathVariable("id") final String vehicleId, @RequestBody @Valid final Point point) {
        log.info("Moving the vehicle {} to coordinates: {}",vehicleId, point);
        return medusaService.moveVehicleTo(vehicleId, point)
                .timeout(Duration.ofSeconds(5))
                .log();
    }


}
