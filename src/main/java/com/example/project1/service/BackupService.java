package com.example.project1.service;

import com.example.project1.model.BackupModel;
import com.example.project1.repository.BackupRepository;
import org.springframework.stereotype.Service;

import java.io.*;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class BackupService {

    private final BackupRepository backupRepository;

    public BackupService(BackupRepository backupRepository) {
        this.backupRepository = backupRepository;
    }


    public void createBackup() throws IOException {
        String timestamp = LocalDateTime.now().toString().replace(":", "-");
        String backupPath = "C:\\Users\\User\\IDEA\\резервная копия\\backup_" + timestamp + ".sql";

        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("C:\\Program Files\\PostgreSQL\\16\\bin\\pg_dump",
                "-U", "postgres",
                "-h", "localhost",
                "-d", "sclad",
                "-Fc",
                "--exclude-table=backups",
                "-f", backupPath);
        processBuilder.environment().put("PGPASSWORD", "1212");

        Process process = processBuilder.start();

        try (BufferedReader outputReader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line;
            while ((line = outputReader.readLine()) != null) {
                System.out.println(line);
            }
            int exitCode = process.waitFor();
            if (exitCode != 0) {
                throw new IOException("Ошибка создания резервной копии, код выхода: " + exitCode);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IOException("Процесс создания резервной копии был прерван", e);
        }

        backupRepository.save(new BackupModel(LocalDateTime.now(), backupPath));
    }


    public void restoreDatabase(String backupPath) throws IOException {
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("C:\\Program Files\\PostgreSQL\\16\\bin\\pg_restore",
                "--if-exists",
                "--clean",
                "-U", "postgres",
                "-h", "localhost",
                "-d", "sclad",
                backupPath);
        processBuilder.environment().put("PGPASSWORD", "1212");

        processBuilder.redirectErrorStream(true);
        Process process = processBuilder.start();

        try (BufferedReader outputReader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line;
            while ((line = outputReader.readLine()) != null) {
                System.out.println(line);
            }
            int exitCode = process.waitFor();
            if (exitCode != 0) {
                throw new IOException("Ошибка восстановления базы данных, код выхода: " + exitCode);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IOException("Процесс восстановления был прерван", e);
        }
    }


    public List<BackupModel> findAllBackups() {
        return backupRepository.findAll();
    }

    public void deleteBackup(Long id) {
        BackupModel backup = backupRepository.findById(id).orElse(null);
        if (backup != null) {
            File file = new File(backup.getPath());
            if (file.exists()) {
                file.delete();
            }
            backupRepository.deleteById(id);
        }
    }
}
