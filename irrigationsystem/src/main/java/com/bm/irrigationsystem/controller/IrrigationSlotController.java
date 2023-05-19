package com.bm.irrigationsystem.controller;

import com.bm.irrigationsystem.datamodel.IrrigationSlotDto;
import com.bm.irrigationsystem.entity.IrrigationSlot;
import com.bm.irrigationsystem.service.IrrigationSlotService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class IrrigationSlotController {

    private final IrrigationSlotService irrigationSlotService;

    @PostMapping("/plots/{plotId}/irrigationSlot")
    public ResponseEntity<IrrigationSlotDto> reserveIrrigationSlot(@PathVariable(value = "plotId") Long plotId,
                                                                   @Valid @RequestBody IrrigationSlotDto irrigationSlotDto) {
        return new ResponseEntity<>(irrigationSlotService.reserveIrrigationSlot(plotId, irrigationSlotDto), HttpStatus.CREATED);
    }

    @GetMapping("/plots/{plotId}/irrigationSlots")
    public List<IrrigationSlotDto> getIrrigationSlotsByPlotId(@PathVariable(value = "plotId") Long plotId){
        return irrigationSlotService.getIrrigationSlotsByPlotId(plotId);
    }

    @GetMapping("/irrigationSlots")
    public List<IrrigationSlotDto> getAllIrrigationSlots(){
        return irrigationSlotService.getAllIrrigationSlots();
    }

    @PutMapping("/plots/{plotId}/irrigationSlot/{irrigationSlot}")
    public ResponseEntity<IrrigationSlotDto> updateIrrigationSlot(@PathVariable(value = "plotId") Long plotId,
                                                                  @PathVariable(value = "irrigationSlot") Long irrigationSlot,
                                                                  @Valid @RequestBody IrrigationSlotDto irrigationSlotDto) {
        return new ResponseEntity<>(irrigationSlotService.updateIrrigationSlot(plotId, irrigationSlot, irrigationSlotDto), HttpStatus.OK);
    }

    @PutMapping("/plots/{plotId}/irrigationSlot/{irrigationSlot}/status/{status}")
    public ResponseEntity<IrrigationSlotDto> updateIrrigationSlotStatus(@PathVariable(value = "plotId") Long plotId,
                                                                        @PathVariable(value = "irrigationSlot") Long irrigationSlot,
                                                                        @PathVariable(value = "status") String status) {
        return new ResponseEntity<>(irrigationSlotService.updateIrrigationSlotStatus(plotId, irrigationSlot, status), HttpStatus.OK);
    }


}
