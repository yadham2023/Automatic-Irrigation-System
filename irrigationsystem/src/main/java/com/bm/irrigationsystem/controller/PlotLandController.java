package com.bm.irrigationsystem.controller;

import com.bm.irrigationsystem.datamodel.PlotLandDto;
import com.bm.irrigationsystem.datamodel.PlotResponse;
import com.bm.irrigationsystem.service.PlotLandService;
import static com.bm.irrigationsystem.utilities.CommonConstants.PaginationConstants.DEFAULT_PAGE_NUMBER;
import static com.bm.irrigationsystem.utilities.CommonConstants.PaginationConstants.DEFAULT_PAGE_SIZE;
import static com.bm.irrigationsystem.utilities.CommonConstants.PaginationConstants.DEFAULT_SORT_BY;
import static com.bm.irrigationsystem.utilities.CommonConstants.PaginationConstants.DEFAULT_SORT_DIRECTION;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/plots")
public class PlotLandController {

    private final PlotLandService plotLandService;

    public PlotLandController(PlotLandService plotLandService) {
        this.plotLandService = plotLandService;
    }

    @PostMapping
    public ResponseEntity<PlotLandDto> addPlot(@Valid @RequestBody PlotLandDto plotDto) {
        return new ResponseEntity<>(plotLandService.addPlot(plotDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlotLandDto> updatePlot(@PathVariable(value = "id") Long plotId,
                                                  @Valid @RequestBody PlotLandDto plotDto) {
        PlotLandDto updatedPlot = plotLandService.updatePlot(plotId, plotDto);
        return new ResponseEntity<>(updatedPlot, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePlot(@PathVariable(name = "id") Long id){
        plotLandService.deletePlotById(id);
        return new ResponseEntity<>("Plot deleted successfully.", HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlotLandDto> getPlotById(@PathVariable(name = "id") Long id){
        return new ResponseEntity<>(plotLandService.getPlotById(id), HttpStatus.OK);
    }

    @GetMapping
    public PlotResponse getAllPosts(
            @RequestParam(value = "pageNo", defaultValue = DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ){
        return plotLandService.getAllPosts(pageNo, pageSize, sortBy, sortDir);
    }
}
