-- Черняков Никита А3201

-- Задание:
-- Написать функцию removeBy :: (k -> Bool) -> Map k v -> Map k v,
-- которая удаляет из заданного отображения те пары, ключи которых удовлетворяют критерию,
-- заданному первым аргументом функции. Например,
-- вызов removeBy (<0) m удалит из отображения m те пары, ключи которых меньше нуля.

module Homework where

-- Представляю отображение как связанный список
data Map key value = Empty | Map key value (Map key value)
    deriving(Show,Eq)

-- Функция получает значение по его ключу
get :: Eq k => k -> Map k v -> Maybe v
get _ Empty = Nothing -- Для случая, если ключа в отображении нет
get k (Map key value next) | k == key = Just value  -- Если совпадает ключ 
                           | otherwise = get k next  -- Иначе ищу дальше

-- Функция добавления/замены элемента в отображении
-- Для замены делаю remove
-- Если это новый элемент, то просто добавляю
put :: Eq k => (k, v) -> Map k v -> Map k v
put (k, v) map' = Map k v (remove k map')

-- Функция удаления элемента по его ключу
remove :: Eq k => k -> Map k v -> Map k v
remove _ Empty = Empty
remove k (Map key value next) | k == key = next -- Если найден, затираем просто его
                              | otherwise = Map key value (remove k next)

-- Функция выдающая все ключи отображения
keys :: Map k v -> [k]
keys current = keys' current []
keys' Empty keyslist = keyslist
keys' (Map key value next) keyslist = keys' next (key:keyslist)

-- Функция выдающая все значения отображения
values :: Map k v -> [v]
values current = values' current []
values' Empty valueslist = valueslist
values' (Map key value next) valueslist = values' next (value:valueslist)

-- Функция из задачи для моего варианта
-- Удаляем так же как и с remove, только вместо сравнения с ключом
-- проверяем ключ на удовлетворение условию функции
removeBy :: (k -> Bool) -> Map k v -> Map k v
removeBy _ Empty = Empty
removeBy f (Map key value next) | f key = removeBy f next
                                | otherwise = Map key value (removeBy f next)

test1 = Map "lol" 3 (Map "haskel" 6 (Map "java" 4 (Empty)))

main = [
    removeBy (\k -> length k > 3) test1 == Map "lol" 3 Empty,
    keys (put ("paradigms", 9) (removeBy (((==)'a') . head) test1)) == ["java","haskel","lol","paradigms"]
    ]