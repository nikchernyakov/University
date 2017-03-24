-- zip :: [a] -> [b] -> [(a,b)]
-- zip [1,2,3] ['a','b','c'] -> [(1,'a'),(2,'b'),(3,'c')]


--Красивее
maxTwo list = maximum (map (\(x,y) -> x + y) (zip list (0 : list))) 

-- Мой гавнокод
findPair list = findPair' list (0 : list)

-- Встроенная функция maximum для поиска максимума в листе 
findPair' list list' = maximum (zipLists list list')

-- Если использовать свой поиск максимума
-- findPair' list list' = findMax (zipLists list list')

zipLists list list' = map (\p -> fst p + snd p) (zip list list')

-- Поиск максимума
findMax list = findMax' (tail list) (head list)
findMax' list m | null list = m
                | otherwise = findMax' (tail list) (max m (head list))