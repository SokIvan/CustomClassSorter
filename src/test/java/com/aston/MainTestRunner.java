package com.aston;

// Импортируем классы JUnit Platform для динамического запуска тестов
import org.junit.platform.engine.discovery.DiscoverySelectors;
import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;
import org.junit.platform.launcher.listeners.TestExecutionSummary;

public class MainTestRunner {
    public static void main(String[] args) {
        // Сообщение при старте выполнения
        System.out.println("🚀 Запуск всех тестов проекта в пакете com.aston ...\n");

        // Создаём запрос, который ищет и выбирает все тесты в пакете "com.aston"
        LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder.request()
                .selectors(DiscoverySelectors.selectPackage("com.aston")) // выбираем все тесты из пакета
                .build();

        // Создаём объект Launcher — движок, который будет выполнять тесты
        Launcher launcher = LauncherFactory.create();

        // Слушатель, который собирает статистику и результаты тестов
        SummaryGeneratingListener listener = new SummaryGeneratingListener();

        // Запускаем выполнение тестов
        launcher.execute(request, listener);

        // Получаем результаты после завершения всех тестов
        TestExecutionSummary summary = listener.getSummary();

        // Вывод общей статистики
        System.out.println("\n===== РЕЗУЛЬТАТЫ ТЕСТОВ =====");
        System.out.println("Успешных: " + summary.getTestsSucceededCount());
        System.out.println("Провалено: " + summary.getTestsFailedCount());
        System.out.println("Пропущено: " + summary.getTestsSkippedCount());
        System.out.println("Всего найдено: " + summary.getTestsFoundCount());

        // Если есть упавшие тесты — выводим подробности
        if (summary.getFailures().size() > 0) {
            System.out.println("\n----- Подробности провалов -----");
            summary.getFailures().forEach(failure -> {
                System.out.println("Тест: " + failure.getTestIdentifier().getDisplayName());
                System.out.println("Исключение: " + failure.getException());
                System.out.println();
            });
        }

        System.out.println("\n🏁 Завершено.");
    }
}
