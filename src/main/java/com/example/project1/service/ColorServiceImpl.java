package com.example.project1.service;

import com.example.project1.model.ColorModel;
import com.example.project1.repository.ColorModelRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColorServiceImpl implements ColorService {
    private final ColorModelRepository colorRepository;

    public ColorServiceImpl(ColorModelRepository colorRepository) {
        this.colorRepository = colorRepository;
    }

    @Override
    public List<ColorModel> findAllColors() {
        System.out.println("Запрос на получение всех цветов.");
        List<ColorModel> colors = colorRepository.findAll();
        System.out.println("Количество найденных цветов: " + colors.size());
        return colors;
    }

    @Override
    public ColorModel findColorById(Long id) {
        System.out.println("Запрос на получение цвета с id: " + id);
        ColorModel color = colorRepository.findById(id).orElse(null);
        if (color != null) {
            System.out.println("Цвет найден: " + color);
        } else {
            System.out.println("Цвет с id " + id + " не найден.");
        }
        return color;
    }

    @Override
    public ColorModel createColor(ColorModel color) {
        System.out.println("Создание нового цвета: " + color);
        ColorModel savedColor = colorRepository.save(color);
        System.out.println("Цвет успешно создан с id: " + savedColor.getId());
        return savedColor;
    }

    @Override
    public ColorModel updateColor(ColorModel color) {
        System.out.println("Обновление цвета с id: " + color.getId());
        ColorModel updatedColor = colorRepository.save(color);
        System.out.println("Цвет успешно обновлён: " + updatedColor);
        return updatedColor;
    }

    @Override
    public void deleteColor(Long id) {
        System.out.println("Запрос на удаление цвета с id: " + id);
        colorRepository.deleteById(id);
        System.out.println("Цвет с id " + id + " успешно удалён.");
    }
}
