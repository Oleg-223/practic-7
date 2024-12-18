package com.example.project1.controller;

import com.example.project1.model.StorageCellModel;
import com.example.project1.service.StorageCellService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/storage-cells")
public class StorageCellController {

    private final StorageCellService storageCellService;

    @Autowired
    public StorageCellController(StorageCellService storageCellService) {
        this.storageCellService = storageCellService;
    }

    @GetMapping
    public String getAllStorageCells(Model model) {
        model.addAttribute("storageCells", storageCellService.findAllStorageCell());
        return "storage-cells/index";
    }

    @GetMapping("/create")
    public String getCreateStorageCellForm(Model model) {
        model.addAttribute("storageCell", new StorageCellModel());
        return "storage-cells/create";
    }

    @PostMapping("/create")
    public String createStorageCell(@Valid @ModelAttribute("storageCell") StorageCellModel storageCell, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "storage-cells/create";
        }
        storageCellService.createStorageCell(storageCell);
        return "redirect:/storage-cells";
    }

    @GetMapping("/edit/{id}")
    public String getEditStorageCellForm(@PathVariable("id") Long id, Model model) {
        StorageCellModel storageCell = storageCellService.findStorageCellById(id);
        model.addAttribute("storageCell", storageCell);
        return "storage-cells/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateStorageCell(@PathVariable("id") Long id, @Valid @ModelAttribute("storageCell") StorageCellModel storageCell, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "storage-cells/edit";
        }

        StorageCellModel existingStorageCell = storageCellService.findStorageCellById(id);
        existingStorageCell.setCellNumber(storageCell.getCellNumber());
        existingStorageCell.setLocation(storageCell.getLocation());

        storageCellService.updateStorageCell(existingStorageCell);

        return "redirect:/storage-cells";
    }

    @GetMapping("/delete/{id}")
    public String deleteStorageCell(@PathVariable("id") Long id, Model model) {
        try {
            storageCellService.deleteStorageCell(id);
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("error", "Невозможно удалить эту ячейку хранения, так как она все еще используется.");
        }
        return "redirect:/storage-cells";
    }
}
