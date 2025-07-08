package xyz.grauberger.application.masterdata.api.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import xyz.grauberger.application.fdi.DataProvider;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;

@Tag(name = "Providers", description = "Operations related to data providers")
@RestController
@RequestMapping("/api/idmapping/providers")
public class ProviderController {

    @Operation(summary = "Get all available data providers")
    @GetMapping(produces = "application/json")
    public List<DataProvider> getMethodName() {
        return List.of(DataProvider.values());
    }
}
