-- Черняков Никита А3201

-- Задание:
-- В заданной строке символов будем считать числом произвольную последовательность цифр,
-- слева и справа от которой не находится цифра.
-- Написать функцию maxNumber :: String -> Integer, выдающую числовое значение самого большого “числа” в строке.
-- Например, результатом вызова функции с аргументом "0xFF55 00012 -100 19" должно быть 100.
-- Если чисел в строке нет совсем, то это ошибка аргумента.

module Homework where
import Data.Function
import Data.Char
import Data.List

-- Алгоритм работы maxNumber:
-- 1) С помощью функции findAllNumbers нахожу список чисел
--  Если нет ни одного числа -> ошибка аргумента (надеюсь, я правильно понял, что нужно выводить через error)
-- 2) Нахожу максимум из этих чисел

maxNumber :: String -> Integer
maxNumber = maximum . findAllNumbers

-- Функция findAllNumbers:
-- 1) Отделяет все числа отдельно от символов с помощью функции groupBy (из Data.List)
--  groupBy берет в качестве паттерна функцию bolfAreDigits
-- 2) Отсеиваем все строки не являющиеся числами из списка с помощью filter
--  Проверяем это, беря первый элемент строки и проверяя его с помощью isDigit (Data.Char)
-- 3) Переводим каждый элемент списка из строки в число с помощью map
--  В качестве функции изменяющей, элемент используем toInteger . read 
--  toInteger нужен для того, что бы read понял, как нужно парсить строку
-- 4) Если итоговый список пуст, то это ошибка аргумента, т.е. нет ни одного числа в строке

findAllNumbers :: String -> [Integer]
findAllNumbers str | null allNumbers = error "Нет чисел в строке"
                   | otherwise = allNumbers 
    where allNumbers = map (toInteger . read) $ filter (isDigit . head) $ groupBy bolfAreDigits str

-- Функция bolfAreDigits
-- Проверяет два символа на то, что они оба цифры
-- Использую функцию on (Data.Function) аналогична была бы запись isDigit x && isDigit y
-- Так симпотичнее вроде
bolfAreDigits = on (&&) isDigit

-- Тесты
main = [
    maxNumber "0xFF55 00012 -100 19" == 100,
    maxNumber "4953 af 34fd" == 4953,
    maxNumber " -20 0 10afv 10" == 20,
    -- maxNumber "abdc adf" == 0, -- Выдаст ошибку, т.к. нет числа в строке, что и нужно
    maxNumber "oneNumber1" == 1
    ]