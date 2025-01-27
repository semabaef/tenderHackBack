package ru.TagAll.tenderHackBack.application.auth.service;

import ru.TagAll.tenderHackBack.application.auth.model.AccessDto;
import ru.TagAll.tenderHackBack.application.auth.model.RegistrationDto;
import ru.TagAll.tenderHackBack.application.auth.model.TokenDto;
import ru.TagAll.tenderHackBack.application.auth.model.AuthDto;

/**
 * Сервис авторизации.
 *
 * @author Iurii Babalin.
 */
public interface AuthService {
    /**
     * Вход пользователя в приложение.
     *
     * @param authDto модель для передачи данных пользователя.
     * @return 2 токена доступа.
     */
    TokenDto auth(AuthDto authDto);

    /**
     * Регистрация в приложении.
     *
     * @param registrationDto модель паредачи данных пользователя для регистрации.
     */
    void registration(RegistrationDto registrationDto);

    /**
     * выход из приложения.
     *
     * @param accessToken токен доступа.
     */
    void logout(AccessDto accessToken);

    /**
     * Обновленеи пароля.
     *
     * @param email почта.
     * @return ссылка на страницу востановления пароля.
     */
    String updatePassword(String email);

    TokenDto updateAuthToken(AccessDto accessToken);
}
