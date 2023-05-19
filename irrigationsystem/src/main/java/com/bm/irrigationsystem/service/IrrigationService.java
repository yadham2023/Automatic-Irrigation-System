package com.bm.irrigationsystem.service;

import com.bm.irrigationsystem.datamodel.IrrigationRequest;
import com.bm.irrigationsystem.repository.IrrigationSlotRepository;
import com.bm.irrigationsystem.repository.PlotRepository;
import com.bm.irrigationsystem.utilities.IntegrationModes;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class IrrigationService {

    private final Map<String, IntegrationMethod> integrationMethodMap;

    public IrrigationService(IrrigationSlotRepository irrigationSlotRepository, PlotRepository plotRepository, List<IntegrationMethod> integrationMethodList) {
        this.integrationMethodMap = integrationMethodList.stream().collect(Collectors.toMap(IntegrationMethod::getType, Function.identity()));
    }

    public void irrigatePlot(IrrigationRequest irrigationRequest) {
        IntegrationModes integrationMode = IntegrationModes.fromValue(irrigationRequest.getIntegrationFramework());
        IntegrationMethod integrationMethodService = integrationMethodMap.get(integrationMode.value());
        integrationMethodService.irrigate(irrigationRequest);
    }
}
