package ru.simflex.ex.services.interfaces;

/**
 * User service interface.
 */
public interface UserService {

    /**
     * Generates a PDF file with user report by the selected tariff.
     * @param tariffId Id of the selected tariff.
     * @return filename
     */
    String generateUserReportById(int tariffId);
}
