-- Черняков Никита А3201

{-- Задание:
    Написать функцию hasPair :: Integer -> Bool, которая проверяет,
    есть ли в десятичной записи заданного числа две подряд идущие одинаковые цифры.
    Например, hasPair 1001 => True, а hasPair 1212 => False.
--}

{--
    Для того, чтобы найти пару подряд идущих одинаковых цифр,
    я каждый раз передаю (в функцию hasPair') предыдущую цифру из этого числа и само число на разряд меньше
    беру из текущего числа первый разряд и сравниваю его с предыдущей цифрой

    Работает и с отрицательными числами. Скорость зависит прямолинейно от длины числа
--} 
hasPair :: Integer -> Bool
hasPair n = hasPair' (n `div` 10) (n `mod` 10)  -- Запускаю изначально hasPair' от уменьшенного на один разряд числа n и его первой цифре

hasPair' n prev | n == 0 = False    -- Якорь рекурсии, если мы прошли по всему числу
                | (n `mod` 10) == prev = True   -- Если первый разряд такой же, что и prev(предыдущая цифра)
                | otherwise = hasPair' (n `div` 10) (n `mod` 10) 
test = [
    hasPair 1001,
    hasPair 1212,
    hasPair 0,
    hasPair 10,
    hasPair 11,
    hasPair 989,
    hasPair (-111)
    ]