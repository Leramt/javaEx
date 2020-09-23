@priority1
Feature: Проблема
  @312
  Scenario: 312 Проблема. Создать новую Проблему (Из Инцидента)
    ##Предусловия теста
    Given open url
    And авторизоваться под логином "Coord"
    And create new incident with executor "Isp Фио"
    And checks status "Направлен в группу" for "Статус"



    ## шаг 1
    And авторизоваться под логином "avtotest"
    ## шаг 2 - 3 Поиск инцидента по параметрам - категория Инцидент
    And search in "Инцидент" by parameters
      |Категория|Инцидент|
    And save input data from nameOfObject "Инцидент"

    ## Шаг 5 - 6 Выбрать дополнительно - создать проблему и проверить значение полей (Блок контактное лицо, категория -проблема, Предпологаемый срок, Скопированные значения из инцидента )
    And create new problem from incident with category to manager "Isp Фио"


     ## Шаг 7 найти и открыть использованный ранее в тесте Инцидент
    And авторизоваться под логином "Coord"
    And search incident by number
     ## Шаг 8 Выбрать вкладку "Протокол"
     ## Шаг 9 Проверить добавление в протокол Инцидента записи о попытке создания Проблемы.
    Then check in the "Инцидент" the protocol tab message "Создание Проблемы из данного Инцидента"
    Then check in the "Инцидент" the protocol tab message "Попытка создания Проблемы"
    Then check in the "Инцидент" the protocol tab message "ВремяПопыткиСозданияПроблемы"
    Then check in the "Инцидент" the protocol tab message "Текущий Инцидент. avtotest Фио"