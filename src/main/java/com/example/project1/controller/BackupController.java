package com.example.project1.controller;

import com.example.project1.model.BackupModel;
import com.example.project1.service.BackupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.List;

@Controller
public class BackupController {

    @Autowired
    private BackupService backupService;

    @GetMapping("/backups")
    public String viewBackups(Model model) {
        List<BackupModel> backups = backupService.findAllBackups();
        model.addAttribute("backups", backups);
        return "backups";
    }

    @PostMapping("/backup")
    public String createBackup() {
        try {
            backupService.createBackup();
        } catch (IOException e) {
            e.printStackTrace();
            return "redirect:/backups?error=backupFailed";
        }
        return "redirect:/backups";
    }

    @GetMapping("/restore/{id}")
    public String restoreBackup(@PathVariable Long id) {
        BackupModel backup = backupService.findAllBackups().stream()
                .filter(b -> b.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid backup Id:" + id));

        try {
            backupService.restoreDatabase(backup.getPath());
        } catch (IOException e) {
            e.printStackTrace();
            return "redirect:/backups?error=restoreFailed";
        }
        return "redirect:/backups";
    }

    @GetMapping("/delete/{id}")
    public String deleteBackup(@PathVariable Long id) {
        backupService.deleteBackup(id);
        return "redirect:/backups";
    }
}
