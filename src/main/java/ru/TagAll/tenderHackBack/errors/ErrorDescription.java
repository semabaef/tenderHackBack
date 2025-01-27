package ru.TagAll.tenderHackBack.errors;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.TagAll.tenderHackBack.errors.model.ApplicationErrorDto;

/**
 * Ошибки с комментариями.
 *
 * @author Iurii Babalin.
 */
@Getter
@RequiredArgsConstructor
public enum ErrorDescription {

    OUT_SYSTEM_ERROR(ErrorType.OUT_SYSTEM, "OUT_001", "Ошибка взаимодействия с внешней системой."),
    OUT_SYSTEM_BET_ERROR(ErrorType.OUT_SYSTEM, "OUT_002", "Ваша ставка отклонена."),
    CUSTOMER_FOUND(ErrorType.APP, "AUTH_001", "Пользователь уже зарегестирован."),
    CUSTOMER_NOT_FOUND(ErrorType.APP, "AUTH_002", "Пользователь не найден."),
    CUSTOMER_PASSWORD_ERROR(ErrorType.APP, "AUTH_003", "Указан неверный пароль."),
    CUSTOMER_LOGOUT_ERROR(ErrorType.APP, "AUTH_004", "Пользователь уже вышел."),
    BOT_NOT_FOUNT(ErrorType.APP, "BOT_001", "Бот для этой сессии не найден."),

    UNAUTHORIZED_ACCESS(ErrorType.APP, "APP_001", "Неавторизованный доступ."),
    ACCESS_DENIED(ErrorType.APP, "APP_002","Недостаточно прав для доступа к ресурсу."),
    HANDLER_NOT_FOUND(ErrorType.APP, "APP_003","HANDLER_NOT_FOUND."),
    UNKNOWN_ERROR(ErrorType.APP, "APP_004","Неизвестная ошибка сервера."),
    SERVICE_UNAVAILABLE(ErrorType.APP, "APP_005","Сервис недоступен.");

    /**
     * Тип ошибки.
     */
    private final ErrorType type;

    /**
     * Код ошибки.
     */
    private final String code;

    /**
     * Сообщение ошибки.
     */
    private final String message;

    /**
     * Метод выбрасывает исключение приложения.
     *
     * @throws ApplicationException исключение приложения
     */
    public void throwException() throws ApplicationException {
        throw exception();
    }

    /**
     * Метод выбрасывает ислючение если объект == null.
     *
     * @param obj объект для проверки
     */
    public void throwIfNull(Object obj) {
        if (obj == null) {
            throw exception();
        }
    }

    /**
     * Метод выбрасывает ислючение если условие истинно.
     *
     * @param condition условие для проверки
     */
    public void throwIfTrue(Boolean condition) {
        if (condition) {
            throw exception();
        }
    }

    /**
     * Метод выбрасывает ислючение если условие ложно.
     *
     * @param condition условие для проверки
     */
    public void throwIfFalse(Boolean condition) {
        if (!condition) {
            throw exception();
        }
    }

    public ApplicationErrorDto createApplicationError() {
        return ApplicationErrorDto.of(this.type, this.code, this.message);
    }

    public ApplicationException exception() {
        return new ApplicationException(createApplicationError());
    }

}
